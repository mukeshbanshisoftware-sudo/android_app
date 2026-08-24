package com.example.food_design;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.bossly.base.BaseActivity;
import com.example.bossly.utils.WindowInsetsManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import ui.screen.cart.CardFragment;
import ui.screen.explore.ExploreFragment;
import ui.screen.favorite.FavoriteFragment;
import ui.screen.home.HomeFragment;
import ui.screen.profile.ProfileFragment;

public class MainActivity extends BaseActivity {


    private static final String TAG = "MAIN_ACTIVITY";

    /// / Notification
    private static final int NOTIFICATION_PERMISSION_REQUEST_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /// Firebase Notification
       requestNotificationPermission();
       ///  FCM Token Ganaret
       getFCMToken();




        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        
        // Apply bottom inset to BottomNavigationView for edge-to-edge
        WindowInsetsManager.applyBottomInset(bottomNavigationView);
        
        if (savedInstanceState == null) {
            replaceFragment(new HomeFragment());
        }

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                replaceFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.explore) {
                replaceFragment(new ExploreFragment());
            } else if (item.getItemId() == R.id.favorite) {
                replaceFragment(new FavoriteFragment());
            } else if (item.getItemId() == R.id.cart) {
                replaceFragment(new CardFragment());
            } else if (item.getItemId() == R.id.profile) {
                replaceFragment(new ProfileFragment());
            }
            return true;
        });
    }





    /// Firebase Notification Permission Handle Method
    private void requestNotificationPermission() {

        // Android 13 / API 33+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(
                        this,
                        new String[]{
                                Manifest.permission.POST_NOTIFICATIONS
                        },
                        NOTIFICATION_PERMISSION_REQUEST_CODE
                );

            } else {

                Log.d(
                        TAG,
                        "Notification permission already granted"
                );
            }

        } else {

            Log.d(
                    TAG,
                    "Notification permission not required"
            );
        }
    }


    // =========================================================
    // Get Firebase FCM Token
    // =========================================================
///  Firebase Get FCM Token Method
    private void getFCMToken() {

        FirebaseMessaging
                .getInstance()
                .getToken()
                .addOnCompleteListener(task -> {

                    if (!task.isSuccessful()) {

                        Log.e(
                                TAG,
                                "Fetching FCM token failed",
                                task.getException()
                        );

                        return;
                    }

                    String token = task.getResult();

                    Log.d(
                            "FCM_TOKEN",
                            "================================"
                    );

                    Log.d(
                            "FCM_TOKEN",
                            "FCM TOKEN:"
                    );

                    Log.d(
                            "FCM_TOKEN",
                            token
                    );

                    Log.d(
                            "FCM_TOKEN",
                            "================================"
                    );

                    // =================================================
                    // TODO:
                    // Later send this token to your backend using
                    // Retrofit + Repository.
                    // =================================================
                });
    }


    // =========================================================
    // Permission Result
    // =========================================================

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults
    ) {

        super.onRequestPermissionsResult(
                requestCode,
                permissions,
                grantResults
        );

        if (requestCode ==
                NOTIFICATION_PERMISSION_REQUEST_CODE) {

            if (grantResults.length > 0
                    && grantResults[0]
                    == PackageManager.PERMISSION_GRANTED) {

                Log.d(
                        TAG,
                        "Notification permission GRANTED"
                );

            } else {

                Log.d(
                        TAG,
                        "Notification permission DENIED"
                );
            }
        }
    }


    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit();
    }



}
