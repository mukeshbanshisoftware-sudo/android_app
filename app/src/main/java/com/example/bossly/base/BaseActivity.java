package com.example.bossly.base;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Base class for all activities to support global safe-area / edge-to-edge system.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // Enable Edge-to-Edge behavior
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
    }
}
