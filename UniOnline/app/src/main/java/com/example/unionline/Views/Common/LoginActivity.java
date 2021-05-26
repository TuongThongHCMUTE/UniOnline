package com.example.unionline.Views.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.unionline.Common;
import com.example.unionline.R;
import com.example.unionline.Views.Students.StudentMainActivity;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    Spinner spRoles;
    Button btLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setSpinnerItems();

        btLogin = findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spRoles.getSelectedItem().toString() == Common.roleStudent){
                    startActivity(new Intent(LoginActivity.this, StudentMainActivity.class));
                    return;
                }
            }
        });
    }

    /**
     * Set list Roles for Spinner
     */
    private void setSpinnerItems(){
        spRoles = findViewById(R.id.spRoles);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(Common.roleAdmin);
        arrayList.add(Common.roleManager);
        arrayList.add(Common.roleTeacher);
        arrayList.add(Common.roleStudent);
        arrayList.add(Common.roleParent);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRoles.setAdapter(arrayAdapter);
    }
}