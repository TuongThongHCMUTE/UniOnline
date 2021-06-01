package com.example.unionline.Views.Teachers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unionline.R;

public class AccountActivity extends AppCompatActivity {

    Button btnUpdateProfile, btnChangePassword, btnLogOut;

    Dialog dialogUpdateProfile, dialogChangePassword;
    EditText edName, edPhone, edEmail, edCurrentPass, edNewPass, edReNewPass;
    Button btnUpdate, btnChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        setToolbar();

        btnUpdateProfile = findViewById(R.id.btnUpdateProfile);
        btnChangePassword = findViewById(R.id.btnChangePassword);
        btnLogOut = findViewById(R.id.btnLogOut);

        btnUpdateProfile.setOnClickListener((View v) -> {
            openUpdateProfileDialog();
        });

        btnChangePassword.setOnClickListener((View v) -> {
            openChangePassworDialog();
        });

        btnLogOut.setOnClickListener((View v) -> {

        });
    }

    private void openUpdateProfileDialog() {

    }

    private void openChangePassworDialog() {

    }

    private void setToolbar() {
        ImageView backIcon = findViewById(R.id.left_icon);
        backIcon.setOnClickListener((View v) -> {
            this.finish();
        });

        TextView txtToolbarName = findViewById(R.id.activity_name);
        txtToolbarName.setText("Cá nhân");
    }
}