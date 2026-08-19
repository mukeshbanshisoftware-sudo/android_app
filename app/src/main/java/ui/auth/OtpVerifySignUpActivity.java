package ui.auth;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.food_design.MainActivity;
import com.example.food_design.R;
import com.matrix.otpview.OtpView;
import com.matrix.otpview.interfaces.OTPCompletionHandler;

public class OtpVerifySignUpActivity extends AppCompatActivity {
    private OtpView otpView;
    private Button otpVerify;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_verify_activity);

        LinearLayout layoutBack = findViewById(R.id.layoutBack);
        layoutBack.setOnClickListener(view -> {
            finish();
        });

        otpView = findViewById(R.id.otpView);
        otpVerify = findViewById(R.id.btnContinueOtp);

        disableOtpButton();
        addOtpTextWatcher();

        otpVerify.setOnClickListener(view -> {
            completeRegistration();
        });

        otpView.setOnCompleteListener(new OTPCompletionHandler() {
            @Override
            public void onComplete(@NonNull String otp) {
                Toast.makeText(OtpVerifySignUpActivity.this, "OTP Entered: " + otp, Toast.LENGTH_SHORT).show();
                completeRegistration();
            }
        });
    }

    private void completeRegistration() {
        Toast.makeText(OtpVerifySignUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
        
        Intent intent = new Intent(OtpVerifySignUpActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
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
