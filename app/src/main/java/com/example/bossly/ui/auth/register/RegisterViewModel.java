package com.example.bossly.ui.auth.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.bossly.data.model.request.RegisterRequest;
import com.example.bossly.data.model.response.RegisterResponse;
import com.example.bossly.data.repository.AuthRepository;
import com.example.bossly.utils.Resource;

public class RegisterViewModel extends ViewModel {
    private final AuthRepository authRepository;

    public RegisterViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public LiveData<Resource<RegisterResponse>> register(
            String email, String password, String firstName, String lastName,
            String phone, String planCode, boolean acceptTerms, boolean marketingOptIn
    ) {
        RegisterRequest request = new RegisterRequest(
                email, password, firstName, lastName, phone, planCode, acceptTerms, marketingOptIn
        );
        return authRepository.register(request);
    }
}
