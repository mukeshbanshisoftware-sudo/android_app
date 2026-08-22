package com.example.bossly.network;

import com.example.bossly.data.model.request.*;
import com.example.bossly.data.model.response.*;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("auth/register")
    Call<RegisterResponse> register(@Body RegisterRequest request);

    @POST("auth/login")
    Call<AuthResponse> login(@Body LoginRequest request);

    @POST("auth/verify-email")
    Call<AuthResponse> verifyEmail(@Body VerifyEmailRequest request);

    @POST("auth/refresh")
    Call<AuthResponse> refresh(@Body RefreshTokenRequest request);

    @POST("auth/logout")
    Call<LogoutResponse> logout(@Body LogoutRequest request);
}
