package onbording;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.food_design.R;

import ui.auth.LoginActivity;

public class OpeningScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);

        Button btnContinue = findViewById(R.id.btnContinue);
        
        if (btnContinue != null) {
            btnContinue.setOnClickListener(v -> {
                Intent intent = new Intent(OpeningScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            });
        }
    }
}
