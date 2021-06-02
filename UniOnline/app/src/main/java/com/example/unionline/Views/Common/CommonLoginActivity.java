package com.example.unionline.Views.Common;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unionline.Common;
import com.example.unionline.DTO.User;
import com.example.unionline.R;
import com.example.unionline.Views.Admin.AdminMainActivity;
import com.example.unionline.Views.Manager.MainActivity;
import com.example.unionline.Views.Students.StudentMainActivity;
import com.example.unionline.Views.Teachers.TeacherMainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CommonLoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etEmail, etPassword;
    TextView tvForgotPassword;
    Spinner spRoles;
    Button btLogin;

    private FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_login);
        
        mapAndSetView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btLogin:
                // Do login if press button login
                doLogin();
                break;
            case R.id.tvForgotPassword:
                // Open reset password activity
                startActivity(new Intent(this, CommonForgotPasswordActivity.class));
                break;
        }
    }

    // Map and set view
    private void mapAndSetView(){
        Common common = new Common();

        btLogin = findViewById(R.id.btLogin);
        btLogin.setOnClickListener(this);

        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        tvForgotPassword.setOnClickListener(this);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        // Set list Roles for Spinner
        spRoles = findViewById(R.id.spRoles);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Common.userRoles);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRoles.setAdapter(arrayAdapter);
    }

    /**
     * Login with email and password
     */
    private void doLogin() {
        // Get text from textview
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Validate input
        if (!doValidate(email, password)){
            return;
        }

        //Add email to auth
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    // Validate Email, if email does not verified send an email
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()){
                        chooseRole(user.getUid());
                    }
                    else{
                        // Send email to validate
                        user.sendEmailVerification();
                        Toast.makeText(CommonLoginActivity.this, "Kiểm tra Email để xác thực tài khoản!", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    // Login unsuccessfully, fail login
                    Toast.makeText(CommonLoginActivity.this, "Sai thông tin đăng nhập!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    /**
     * Check role have permission to role
     * @param userId
     */
    public void chooseRole(String userId){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Users").child(userId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                }
                else {
                    // Get user from realtime database to check role
                    Common.user = task.getResult().getValue(User.class);

                    // Check is active account
                    if(!Common.user.isActive()){
                        Toast.makeText(CommonLoginActivity.this, "Tài khoản của bạn đang tạm khóa!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    // Check role with permission
                    if(Common.user.getRole() == Common.roleAdmin
                            && spRoles.getSelectedItem().toString().equals(Common.userRoles.get(Common.roleAdmin))){
                        startActivity(new Intent(CommonLoginActivity.this, AdminMainActivity.class));
                        return;
                    } else if(Common.user.getRole() == Common.roleManager
                            && spRoles.getSelectedItem().toString().equals(Common.userRoles.get(Common.roleManager))){
                        startActivity(new Intent(CommonLoginActivity.this, MainActivity.class));
                        return;
                    } else if(Common.user.getRole() == Common.roleTeacher
                            && spRoles.getSelectedItem().toString().equals(Common.userRoles.get(Common.roleTeacher))){
                        startActivity(new Intent(CommonLoginActivity.this, TeacherMainActivity.class));
                        return;
                    } else if(Common.user.getRole() == Common.roleStudent
                            && spRoles.getSelectedItem().toString().equals(Common.userRoles.get(Common.roleStudent))){
                        startActivity(new Intent(CommonLoginActivity.this, StudentMainActivity.class));
                        return;
                    }

                    Toast.makeText(CommonLoginActivity.this, "Đăng nhập sai vai trò!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * Validate email and password
     * @param email
     * @param password
     * @return
     */
    private boolean doValidate(String email, String password) {
        if (email.isEmpty()){
            etEmail.setError("Chưa nhập Email!");
            etEmail.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Email không đúng!");
            etEmail.requestFocus();
            return false;
        }

        if(password.isEmpty()) {
            etPassword.setError("Chưa nhập mật khẩu!");
            etPassword.requestFocus();
            return false;
        }

        if(password.length() < 8){
            etPassword.setError("Mật khẩu ít nhất 8 ký tự!");
            etPassword.requestFocus();
            return false;
        }
        return true;
    }
}