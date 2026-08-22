package com.example.bossly.ui.auth.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.bossly.data.model.request.LoginRequest;
import com.example.bossly.data.model.response.AuthResponse;
import com.example.bossly.data.repository.AuthRepository;
import com.example.bossly.utils.Resource;

public class LoginViewModel extends ViewModel {
    private final AuthRepository authRepository;

    public LoginViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public LiveData<Resource<AuthResponse>> login(String email, String password, String tenantSlug, boolean remember) {
        LoginRequest request = new LoginRequest(email, password, tenantSlug, remember);
        return authRepository.login(request);
    }
}
