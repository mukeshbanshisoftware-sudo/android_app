package onbording;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.bossly.base.BaseActivity;
import com.example.bossly.utils.WindowInsetsManager;
import com.example.food_design.R;

import ui.auth.LoginActivity;

public class OpeningScreenActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);

        Button btnContinue = findViewById(R.id.btnContinue);
        
        // Apply Safe Area Insets to the continue button if it's at the bottom
        WindowInsetsManager.applyBottomInset(btnContinue);
        
        if (btnContinue != null) {
            btnContinue.setOnClickListener(v -> {
                Intent intent = new Intent(OpeningScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            });
        }
    }
}
