package com.example.bossly.network;

import android.content.Context;
import com.example.bossly.data.local.SessionManager;
import com.example.bossly.utils.Constants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static ApiService apiService;

    public static ApiService getApiService(Context context) {
        if (apiService == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            SessionManager sessionManager = new SessionManager(context);
            AuthInterceptor authInterceptor = new AuthInterceptor(sessionManager);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .addInterceptor(authInterceptor)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

            apiService = retrofit.create(ApiService.class);
            authInterceptor.setApiService(apiService);
        }
        return apiService;
    }
}
