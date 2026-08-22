package com.example.bossly.ui;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.bossly.data.repository.AuthRepository;
import com.example.bossly.network.ApiClient;
import com.example.bossly.ui.auth.login.LoginViewModel;
import com.example.bossly.ui.auth.register.RegisterViewModel;
import com.example.bossly.ui.auth.verify.VerifyEmailViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final AuthRepository repository;

    public ViewModelFactory(AuthRepository repository) {
        this.repository = repository;
    }

    public static ViewModelFactory getInstance(Context context) {
        AuthRepository repository = new AuthRepository(ApiClient.getApiService(context));
        return new ViewModelFactory(repository);
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RegisterViewModel.class)) {
            return (T) new RegisterViewModel(repository);
        } else if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(repository);
        } else if (modelClass.isAssignableFrom(VerifyEmailViewModel.class)) {
            return (T) new VerifyEmailViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
