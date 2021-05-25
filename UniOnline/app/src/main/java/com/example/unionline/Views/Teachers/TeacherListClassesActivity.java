package com.example.unionline.Views.Teachers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.unionline.Adapters.Teachers.ClassAdapter;
import com.example.unionline.DAO.ClassDAO;
import com.example.unionline.DAO.Dao;
import com.example.unionline.DTO.Class;
import com.example.unionline.R;

import java.util.ArrayList;
import java.util.List;

public class TeacherListClassesActivity extends AppCompatActivity {

    RecyclerView dataList;
    ArrayList<Class> classList;
    ClassAdapter adapter;
    Dao<Class> classDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list_classes);

        dataList = findViewById(R.id.rvListClasses);

        classDAO = new ClassDAO();
        classList = (ArrayList<Class>) classDAO.getAll();

        adapter = new ClassAdapter(this, classList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        dataList.setLayoutManager(gridLayoutManager);
        dataList.setAdapter(adapter);
    }
}