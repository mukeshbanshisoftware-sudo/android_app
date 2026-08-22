package ui.auth;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.bossly.data.local.SessionManager;
import com.example.bossly.ui.ViewModelFactory;
import com.example.bossly.ui.auth.verify.VerifyEmailViewModel;
import com.example.bossly.utils.Resource;
import com.example.food_design.MainActivity;
import com.example.food_design.R;
import com.matrix.otpview.OtpView;
import com.matrix.otpview.interfaces.OTPCompletionHandler;

public class OtpVerifySignUpActivity extends AppCompatActivity {
    private OtpView otpView;
    private Button otpVerify;
    private ProgressBar progressBar;
    private VerifyEmailViewModel viewModel;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_verify_activity);

        sessionManager = new SessionManager(this);
        ViewModelFactory factory = ViewModelFactory.getInstance(this);
        viewModel = new ViewModelProvider(this, factory).get(VerifyEmailViewModel.class);

        LinearLayout layoutBack = findViewById(R.id.layoutBack);
        layoutBack.setOnClickListener(view -> finish());

        otpView = findViewById(R.id.otpView);
        otpVerify = findViewById(R.id.btnContinueOtp);
        progressBar = findViewById(R.id.progressBar);

        disableOtpButton();
        addOtpTextWatcher();

        otpVerify.setOnClickListener(view -> {
            String code = getOtpValue();
            verifyOtp(code);
        });

        otpView.setOnCompleteListener(new OTPCompletionHandler() {
            @Override
            public void onComplete(@NonNull String otp) {
                verifyOtp(otp);
            }
        });
    }

    private String getOtpValue() {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < otpView.getChildCount(); i++) {
            if (otpView.getChildAt(i) instanceof LinearLayout) {
                LinearLayout row = (LinearLayout) otpView.getChildAt(i);
                for (int j = 0; j < row.getChildCount(); j++) {
                    if (row.getChildAt(j) instanceof EditText) {
                        EditText editText = (EditText) row.getChildAt(j);
                        otp.append(editText.getText().toString());
                    }
                }
            }
        }
        return otp.toString();
    }

    private void verifyOtp(String code) {
        String userId = sessionManager.getUserId();
        String tenantSlug = sessionManager.getTenantSlug();

        if (userId == null || tenantSlug == null) {
            Toast.makeText(this, "Session error. Please register again.", Toast.LENGTH_SHORT).show();
            return;
        }

        viewModel.verifyEmail(userId, tenantSlug, code).observe(this, resource -> {
            if (resource.status == Resource.Status.LOADING) {
                setLoading(true);
            } else if (resource.status == Resource.Status.SUCCESS) {
                setLoading(false);
                if (resource.data != null) {
                    sessionManager.saveAuthSession(
                            resource.data.getAccessToken(),
                            resource.data.getRefreshToken(),
                            resource.data.getUser()
                    );
                    Toast.makeText(this, "Verification successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(OtpVerifySignUpActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            } else if (resource.status == Resource.Status.ERROR) {
                setLoading(false);
                Toast.makeText(this, resource.message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setLoading(boolean isLoading) {
        if (progressBar != null) {
            progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        }
        otpVerify.setEnabled(!isLoading);
        otpView.setEnabled(!isLoading);
    }

    private void addOtpTextWatcher() {
        for (int i = 0; i < otpView.getChildCount(); i++) {
            if (otpView.getChildAt(i) instanceof LinearLayout) {
                LinearLayout row = (LinearLayout) otpView.getChildAt(i);
                for (int j = 0; j < row.getChildCount(); j++) {
                    if (row.getChildAt(j) instanceof EditText) {
                        EditText editText = (EditText) row.getChildAt(j);
                        editText.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                checkOtp();
                            }
                            @Override
                            public void afterTextChanged(Editable s) {}
                        });
                    }
                }
            }
        }
    }

    private void checkOtp() {
        int filledCount = 0;
        for (int i = 0; i < otpView.getChildCount(); i++) {
            if (otpView.getChildAt(i) instanceof LinearLayout) {
                LinearLayout row = (LinearLayout) otpView.getChildAt(i);
                for (int j = 0; j < row.getChildCount(); j++) {
                    if (row.getChildAt(j) instanceof EditText) {
                        EditText editText = (EditText) row.getChildAt(j);
                        if (!editText.getText().toString().isEmpty()) {
                            filledCount++;
                        }
                    }
                }
            }
        }
        if (filledCount == 4) {
            enableOtpButton();
        } else {
            disableOtpButton();
        }
    }

    private void enableOtpButton() {
        otpVerify.setEnabled(true);
        otpVerify.setBackgroundTintList(getColorStateList(R.color.green));
        otpVerify.setTextColor(Color.WHITE);
    }

    private void disableOtpButton() {
        otpVerify.setEnabled(false);
        otpVerify.setBackgroundTintList(getColorStateList(R.color.gray));
        otpVerify.setTextColor(Color.GRAY);
    }
}
