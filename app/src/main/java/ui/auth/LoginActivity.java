package ui.auth;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.food_design.MainActivity;
import com.example.food_design.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText etEmail, etPassword;
    private Button btnLogin;
    private TextView txtForgot, txtSignUp;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        LinearLayout layoutBack = findViewById(R.id.layoutBack);
        layoutBack.setOnClickListener(view -> finish());

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtForgot = findViewById(R.id.txtForgot);
        txtSignUp = findViewById(R.id.btnSignUp);
        progressBar = findViewById(R.id.progressBar);

        btnLogin.setEnabled(false);
        btnLogin.setBackgroundTintList(getColorStateList(R.color.gray));

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkFormFields();
            }
            @Override
            public void afterTextChanged(Editable s) {}
        };

        etEmail.addTextChangedListener(textWatcher);
        etPassword.addTextChangedListener(textWatcher);

        txtForgot.setOnClickListener(view -> Toast.makeText(this, "Forgot Password", Toast.LENGTH_SHORT).show());

        txtSignUp.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });

        btnLogin.setOnClickListener(view -> {
            String email = Objects.requireNonNull(etEmail.getText()).toString().trim();
            String password = Objects.requireNonNull(etPassword.getText()).toString().trim();

            setLoading(true);

            // Mocking login process
            btnLogin.postDelayed(() -> {
                setLoading(false);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
//                if (email.equals("test@gmail.com") && password.equals("Password@123")) {
//                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
//                    finish();
//                } else {
//                    Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
//                }
            }, 1000);
        });
    }

    private void setLoading(boolean isLoading) {
        if (progressBar != null) {
            progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        }
        btnLogin.setEnabled(!isLoading);
        btnLogin.setText(isLoading ? "Logging in..." : getString(R.string.log_in));
        etEmail.setEnabled(!isLoading);
        etPassword.setEnabled(!isLoading);
    }

    private void checkFormFields() {
        String email = Objects.requireNonNull(etEmail.getText()).toString().trim();
        String password = Objects.requireNonNull(etPassword.getText()).toString().trim();

        if (!email.isEmpty() && !password.isEmpty()) {
            btnLogin.setEnabled(true);
            btnLogin.setBackgroundTintList(getColorStateList(R.color.green));
            btnLogin.setTextColor(Color.WHITE);
        } else {
            btnLogin.setEnabled(false);
            btnLogin.setBackgroundTintList(getColorStateList(R.color.gray));
            btnLogin.setTextColor(Color.parseColor("#999999"));
        }
    }
}
