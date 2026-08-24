package onbording;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.bossly.base.BaseActivity;
import com.example.bossly.data.local.SessionManager;
import com.example.food_design.MainActivity;
import com.example.food_design.R;

import ui.auth.LoginActivity;

public class SplashScreenActivity extends BaseActivity {

    private static final int SPLASH_SCREEN_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SessionManager sessionManager = new SessionManager(this);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent;
            if (sessionManager.isLoggedIn()) {
                intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            } else {
                intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
            }
            startActivity(intent);
            finish();
        }, SPLASH_SCREEN_TIME_OUT);
    }
}
