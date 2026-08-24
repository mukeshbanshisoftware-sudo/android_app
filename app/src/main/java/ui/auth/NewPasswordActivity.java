package ui.auth;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.bossly.base.BaseActivity;
import com.example.bossly.utils.WindowInsetsManager;
import com.example.food_design.R;

public class NewPasswordActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_password_activity);

        // Apply Insets
        View layoutBack = findViewById(R.id.layoutBack);
        if (layoutBack != null) {
            WindowInsetsManager.applyTopInset(layoutBack);
            layoutBack.setOnClickListener(v -> finish());
        }
    }
}
