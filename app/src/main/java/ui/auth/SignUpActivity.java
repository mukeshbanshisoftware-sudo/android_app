package ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.food_design.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {
    private TextInputEditText etUserName, etEmailSignUp, etPasswordSignUp;
    private Button createAccount;
    private TextView signInButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
        
        LinearLayout layoutBack = findViewById(R.id.layoutBack);
        layoutBack.setOnClickListener(view -> finish());
        
        etUserName = findViewById(R.id.etUserName);
        etEmailSignUp = findViewById(R.id.etEmailSignUp);
        etPasswordSignUp = findViewById(R.id.etPasswordSignUp);
        createAccount = findViewById(R.id.btnCreateAccount);
        signInButton = findViewById(R.id.signIn);
        progressBar = findViewById(R.id.progressBar);

        CheckBox cbTerms = findViewById(R.id.cbTerms);
        createAccount.setEnabled(false);
        createAccount.setBackgroundTintList(getColorStateList(R.color.gray));
        createAccount.setTextColor(getColor(R.color.gray_light));

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkListData();
            }
            @Override
            public void afterTextChanged(Editable s) {}
        };

        etUserName.addTextChangedListener(textWatcher);
        etEmailSignUp.addTextChangedListener(textWatcher);
        etPasswordSignUp.addTextChangedListener(textWatcher);
        cbTerms.setOnClickListener(view -> checkListData());

        createAccount.setOnClickListener(view -> {
            setLoading(true);
            
            // Mocking registration process
            createAccount.postDelayed(() -> {
                setLoading(false);
                Toast.makeText(SignUpActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                
                Intent intent = new Intent(SignUpActivity.this, OtpVerifySignUpActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }, 1000);
        });

        signInButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void setLoading(boolean isLoading) {
        if (progressBar != null) {
            progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        }
        createAccount.setEnabled(!isLoading);
        createAccount.setText(isLoading ? "Registering..." : getString(R.string.create_account));
        etUserName.setEnabled(!isLoading);
        etEmailSignUp.setEnabled(!isLoading);
        etPasswordSignUp.setEnabled(!isLoading);
    }

    private void checkListData() {
        String userName = Objects.requireNonNull(etUserName.getText()).toString().trim();
        String email = Objects.requireNonNull(etEmailSignUp.getText()).toString().trim();
        String password = Objects.requireNonNull(etPasswordSignUp.getText()).toString().trim();
        CheckBox cbTerms = findViewById(R.id.cbTerms);

        if (!userName.isEmpty() && !email.isEmpty() && !password.isEmpty() && cbTerms.isChecked()) {
            createAccount.setEnabled(true);
            createAccount.setBackgroundTintList(getColorStateList(R.color.green));
            createAccount.setTextColor(getColor(R.color.white));
        } else {
            createAccount.setEnabled(false);
            createAccount.setBackgroundTintList(getColorStateList(R.color.gray));
            createAccount.setTextColor(getColor(R.color.gray_light));
        }
    }
}
