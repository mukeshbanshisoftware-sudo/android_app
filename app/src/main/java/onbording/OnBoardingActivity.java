package onbording;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.food_design.R;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingActivity extends AppCompatActivity {

    private ViewPager2 viewPagerOnboarding;
    private ImageView btnNext;
    private TextView tvSkip;
    private LinearLayout layoutDots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        initViews();
        setupViewPager();
        setupClickListeners();
        setupDots(0);
    }

    private void initViews() {
        viewPagerOnboarding = findViewById(R.id.viewPagerOnboarding);
        btnNext = findViewById(R.id.btnNext);
        tvSkip = findViewById(R.id.tvSkip);
        layoutDots = findViewById(R.id.layoutDots);
    }

    private void setupViewPager() {
        // Using simplified mock data strings


        viewPagerOnboarding.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setupDots(position);
            }
        });
    }

    private void setupDots(int position) {
        layoutDots.removeAllViews();
        int count = 3; // Fixed count for mock

        for (int i = 0; i < count; i++) {
            ImageView dot = new ImageView(this);
            if (i == position) {
                dot.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dot_active));
            } else {
                dot.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dot_inactive));
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(8, 0, 8, 0);
            layoutDots.addView(dot, params);
        }
    }

    private void setupClickListeners() {
        btnNext.setOnClickListener(view -> {
            int currentPosition = viewPagerOnboarding.getCurrentItem();
            if (currentPosition < 2) {
                viewPagerOnboarding.setCurrentItem(currentPosition + 1);
            } else {
                openOpeningActivity();
            }
        });

        tvSkip.setOnClickListener(view -> openOpeningActivity());
    }

    private void openOpeningActivity() {
        Intent intent = new Intent(OnBoardingActivity.this, OpeningScreenActivity.class);
        startActivity(intent);
        finish();
    }
}
