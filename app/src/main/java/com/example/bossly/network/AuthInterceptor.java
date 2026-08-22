package com.example.bossly.network;

import com.example.bossly.data.local.SessionManager;
import com.example.bossly.data.model.request.RefreshTokenRequest;
import com.example.bossly.data.model.response.AuthResponse;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;

public class AuthInterceptor implements Interceptor {
    private final SessionManager sessionManager;
    private ApiService apiService;

    public AuthInterceptor(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void setApiService(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        String path = originalRequest.url().encodedPath();

        // Do not add Authorization header for non-authenticated endpoints
        if (path.contains("/auth/register") || path.contains("/auth/login") || path.contains("/auth/verify-email")) {
            return chain.proceed(originalRequest);
        }

        String token = sessionManager.getAccessToken();
        Request.Builder builder = originalRequest.newBuilder();
        if (token != null) {
            builder.header("Authorization", "Bearer " + token);
        }

        Response response = chain.proceed(builder.build());

        // Handle 401 Unauthorized
        if (response.code() == 401) {
            synchronized (this) {
                String currentToken = sessionManager.getAccessToken();
                // Check if the token was already refreshed by another thread
                if (currentToken != null && currentToken.equals(token)) {
                    String refreshToken = sessionManager.getRefreshToken();
                    if (refreshToken != null && apiService != null) {
                        // Synchronously call refresh token API
                        
                        Call<AuthResponse> call = apiService.refresh(new RefreshTokenRequest(refreshToken));
                        retrofit2.Response<AuthResponse> refreshResponse = call.execute();

                        if (refreshResponse.isSuccessful() && refreshResponse.body() != null) {
                            AuthResponse auth = refreshResponse.body();
                            sessionManager.saveAuthSession(auth.getAccessToken(), auth.getRefreshToken(), auth.getUser());
                            
                            // Retry the original request with the new token
                            response.close();
                            return chain.proceed(originalRequest.newBuilder()
                                    .header("Authorization", "Bearer " + auth.getAccessToken())
                                    .build());
                        } else {
                            // Refresh failed, clear session and return 401
                            sessionManager.clearSession();
                        }
                    }
                } else if (currentToken != null) {
                    // Token was already refreshed, retry with new token
                    response.close();
                    return chain.proceed(originalRequest.newBuilder()
                            .header("Authorization", "Bearer " + currentToken)
                            .build());
                }
            }
        }

        return response;
    }
}
