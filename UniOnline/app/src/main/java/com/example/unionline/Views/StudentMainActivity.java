package com.example.unionline.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.unionline.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StudentMainActivity extends AppCompatActivity {

    public BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.student_fragment_container, new StudentInteractFragment()).commit();

        bottomNav = findViewById(R.id.student_bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new StudentInteractFragment();
                            break;
                        case R.id.nav_interaction:
                            selectedFragment = new StudentInteractFragment();
                            break;
                        case R.id.nav_notification:
                            selectedFragment = new StudentInteractFragment();
                            break;
                        case R.id.nav_admin_account:
                            selectedFragment = new StudentInteractFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.student_fragment_container, selectedFragment).commit();
                    return true;
                }
            };
}