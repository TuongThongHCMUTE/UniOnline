package com.example.unionline.Views.Teachers.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unionline.Adapters.Teachers.ClassProcessAdapter;
import com.example.unionline.Common;
import com.example.unionline.DAO.LessonDAO;
import com.example.unionline.DTO.Lesson;
import com.example.unionline.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TeacherUpdateProcessFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Lesson> lessons;
    ClassProcessAdapter classProcessAdapter;
    GridLayoutManager gridLayoutManager;
    DatabaseReference mData;

    private ClassProcessAdapter.RecyclerViewClickListener listener;

    public TeacherUpdateProcessFragment() {
        // Required empty public constructor
    }

    public static TeacherUpdateProcessFragment newInstance(String param1, String param2) {
        TeacherUpdateProcessFragment fragment = new TeacherUpdateProcessFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_teacher_update_process, container, false);

        setRecyclerView(root);
        setOnClickListener();

        return root;
    }

    private void setOnClickListener() {
        listener = new ClassProcessAdapter.RecyclerViewClickListener() {

            @Override
            public void onTouch(View v, int adapterPosition) {
                if(adapterPosition >= 0) {
                    Lesson lesson = lessons.get(adapterPosition);
                    LessonDAO.getInstance().changeStatusLesson(lesson, "2020_2021_HK1");
                }
            }

            @Override
            public void onCLick(View itemView, int adapterPosition) {
                // do nothing
            }
        };
    }

    private void setRecyclerView(View root) {

        // Initialize
        lessons = new ArrayList<>();
        classProcessAdapter = new ClassProcessAdapter(getContext(), (ArrayList<Lesson>) lessons, listener);
        gridLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);

        // Set adapter for recycler view
        recyclerView = root.findViewById(R.id.rvListLessonProcess);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(classProcessAdapter);

        // Fill data from Firebase
        mData = FirebaseDatabase.getInstance().getReference("Lessons").child("2020_2021_HK1");
        Query query = mData.orderByChild("classId").equalTo("-Mah1bXNZ1gVfLAtjT7w");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                lessons.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Lesson lesson = dataSnapshot.getValue(Lesson.class);
                    lessons.add(lesson);
                }
                classProcessAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}