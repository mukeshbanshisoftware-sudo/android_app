package com.example.bossly.ui.auth.verify;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.bossly.data.model.request.VerifyEmailRequest;
import com.example.bossly.data.model.response.AuthResponse;
import com.example.bossly.data.repository.AuthRepository;
import com.example.bossly.utils.Resource;

public class VerifyEmailViewModel extends ViewModel {
    private final AuthRepository authRepository;

    public VerifyEmailViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public LiveData<Resource<AuthResponse>> verifyEmail(String userId, String tenantSlug, String code) {
        VerifyEmailRequest request = new VerifyEmailRequest(userId, tenantSlug, code);
        return authRepository.verifyEmail(request);
    }
}
