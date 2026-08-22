package com.example.bossly.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bossly.data.model.request.*;
import com.example.bossly.data.model.response.*;
import com.example.bossly.network.ApiService;
import com.example.bossly.utils.Resource;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private final ApiService apiService;

    public AuthRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public LiveData<Resource<RegisterResponse>> register(RegisterRequest request) {
        MutableLiveData<Resource<RegisterResponse>> data = new MutableLiveData<>();
        data.setValue(Resource.loading(null));

        apiService.register(request).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    data.setValue(Resource.success(response.body()));
                } else {
                    data.setValue(Resource.error(getErrorMessage(response), null));
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                data.setValue(Resource.error(t.getMessage(), null));
            }
        });
        return data;
    }

    public LiveData<Resource<AuthResponse>> login(LoginRequest request) {
        MutableLiveData<Resource<AuthResponse>> data = new MutableLiveData<>();
        data.setValue(Resource.loading(null));

        apiService.login(request).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    data.setValue(Resource.success(response.body()));
                } else {
                    data.setValue(Resource.error(getErrorMessage(response), null));
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                data.setValue(Resource.error(t.getMessage(), null));
            }
        });
        return data;
    }

    public LiveData<Resource<AuthResponse>> verifyEmail(VerifyEmailRequest request) {
        MutableLiveData<Resource<AuthResponse>> data = new MutableLiveData<>();
        data.setValue(Resource.loading(null));

        apiService.verifyEmail(request).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    data.setValue(Resource.success(response.body()));
                } else {
                    data.setValue(Resource.error(getErrorMessage(response), null));
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                data.setValue(Resource.error(t.getMessage(), null));
            }
        });
        return data;
    }

    public LiveData<Resource<Void>> logout(LogoutRequest request) {
        MutableLiveData<Resource<Void>> data = new MutableLiveData<>();
        data.setValue(Resource.loading(null));

        apiService.logout(request).enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                if (response.isSuccessful()) {
                    data.setValue(Resource.success(null));
                } else {
                    data.setValue(Resource.error(getErrorMessage(response), null));
                }
            }

            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable t) {
                data.setValue(Resource.error(t.getMessage(), null));
            }
        });
        return data;
    }

    private String getErrorMessage(Response<?> response) {
        try {
            if (response.errorBody() != null) {
                JSONObject jObjError = new JSONObject(response.errorBody().string());
                return jObjError.getString("message");
            }
        } catch (Exception e) {
            return response.message();
        }
        return "An unknown error occurred";
    }
}
