package com.example.unionline.Views.Teachers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.unionline.Adapters.Teachers.ClassAdapter;
import com.example.unionline.DTO.Class;
import com.example.unionline.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TeacherListClassesActivity extends AppCompatActivity {

    DatabaseReference mData;
    List<Class> classes;
    GridLayoutManager gridLayoutManager;
    RecyclerView recyclerView;
    ClassAdapter adapter;
    ClassAdapter.RecyclerViewClickListener listener;
    ImageView backIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list_classes);

        setOnClickListener();
        setRecyclerView();

        backIcon = (ImageView) findViewById(R.id.left_icon);
        backIcon.setOnClickListener((View v) -> {
            this.finish();
        });
    }

    private void setOnClickListener() {
        listener = new ClassAdapter.RecyclerViewClickListener() {
            @Override
            public void onCLick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), TeacherMainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onTouch(View v, int position) {
                Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void setRecyclerView() {
        // Initialize
        classes  = new ArrayList<>();
        adapter = new ClassAdapter(this, (ArrayList<Class>) classes, listener);
        gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);

        // Set adapter for recycler view
        recyclerView = findViewById(R.id.rvListClasses);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        // Fill data from Firebase
        mData = FirebaseDatabase.getInstance().getReference("Class");
        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                classes.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Class aClass = dataSnapshot.getValue(Class.class);
                    classes.add(aClass);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}