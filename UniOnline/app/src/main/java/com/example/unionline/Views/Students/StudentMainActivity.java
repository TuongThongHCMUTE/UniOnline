package com.example.unionline.Views.Students;

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
<<<<<<< HEAD
                .replace(R.id.student_fragment_container, new StudentHomeFragment()).commit();
=======
                .replace(R.id.student_fragment_container, new StudentInteractionFragment()).commit();
>>>>>>> 566e9d52f4baa1de2b519f0a6b8372415023667c

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
<<<<<<< HEAD
                            selectedFragment = new StudentHomeFragment();
=======
                            selectedFragment = new StudentInteractionFragment();
>>>>>>> 566e9d52f4baa1de2b519f0a6b8372415023667c
                            break;
                        case R.id.nav_interaction:
                            selectedFragment = new StudentInteractionFragment();
                            break;
                        case R.id.nav_notification:
<<<<<<< HEAD
                            selectedFragment = new StudentNotificationFragment();
                            break;
                        case R.id.nav_admin_account:
                            selectedFragment = new StudentNotificationFragment();
=======
                            selectedFragment = new StudentInteractionFragment();
                            break;
                        case R.id.nav_admin_account:
                            selectedFragment = new StudentInteractionFragment();
>>>>>>> 566e9d52f4baa1de2b519f0a6b8372415023667c
                            break;
                    }
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.student_fragment_container, selectedFragment).commit();
                    return true;
                }
            };
}