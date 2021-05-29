package com.example.unionline.Views.Teachers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.unionline.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TeacherListNotificationsActivity extends AppCompatActivity {

    private FloatingActionButton fabAddNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list_notifications);

        fabAddNotification = (FloatingActionButton) findViewById(R.id.fbAddNotification);
        fabAddNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherListNotificationsActivity.this, TeacherAddNotificationActivity.class);
                startActivity(intent);
            }
        });
    }
}