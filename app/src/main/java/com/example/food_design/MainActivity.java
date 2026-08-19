package com.example.food_design;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ui.screen.cart.CardFragment;
import ui.screen.explore.ExploreFragment;
import ui.screen.favorite.FavoriteFragment;
import ui.screen.home.HomeFragment;
import ui.screen.profile.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        replaceFragment(new HomeFragment());


        bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.home) {
                replaceFragment(new HomeFragment());

            } else if (item.getItemId() == R.id.explore) {
                replaceFragment(new ExploreFragment());
            }
            else if (item.getItemId() == R.id.favorite) {
                replaceFragment(new FavoriteFragment());

                }
            else if (item.getItemId() == R.id.cart) {
                replaceFragment(new CardFragment());
            }
            else if (item.getItemId() == R.id.profile) {
                replaceFragment(new ProfileFragment());
            }
            return true;
        });
        }


    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit();
    }





}



