package com.example.unionline.Views.Teachers.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.unionline.Adapters.Teachers.ClassMarkAdapter;
import com.example.unionline.Adapters.Teachers.ClassProcessAdapter;
import com.example.unionline.Common;
import com.example.unionline.DAO.LessonDAO;
import com.example.unionline.DTO.Class;
import com.example.unionline.DTO.Lesson;
import com.example.unionline.DTO.Score;
import com.example.unionline.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TeacherMarkFragment extends Fragment {

    private static final String ARG_CLASS = "class";
    private Class aClass;

    RecyclerView recyclerView;
    List<Score> scores;
    ClassMarkAdapter classMarkAdapter;
    GridLayoutManager gridLayoutManager;
    DatabaseReference mData;

    private ClassMarkAdapter.RecyclerViewClickListener listener;

    public TeacherMarkFragment() {
        // Required empty public constructor
    }


    public static TeacherMarkFragment newInstance(Class aClass) {
        TeacherMarkFragment fragment = new TeacherMarkFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CLASS, (Serializable) aClass);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            aClass = (Class) getArguments().getSerializable(ARG_CLASS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_teacher_mark, container, false);

        setRecyclerView(root);
        setOnClickListener();

        return root;
    }

    private void setOnClickListener() {
        listener = new ClassMarkAdapter.RecyclerViewClickListener() {

            @Override
            public void onTouch(View v, int position) {

            }

            @Override
            public void onCLick(View itemView, int adapterPosition) {

            }
        };
    }

    private void setRecyclerView(View root) {

        // Initialize
        scores = new ArrayList<>();

        Score score1 = new Score(aClass.getClassId(), "18110207", "Đinh Bách Thông", "9.5", "9.5");
        Score score2 = new Score(aClass.getClassId(), "18110207", "Đinh Bách Thông", "9.5", "9.5");
        Score score3 = new Score(aClass.getClassId(), "18110207", "Đinh Bách Thông", "9.5", "9.5");
        scores.add(score1);
        scores.add(score2);
        scores.add(score3);

        classMarkAdapter = new ClassMarkAdapter(getContext(), (ArrayList<Score>) scores, listener);
        gridLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);

        // Set adapter for recycler view
        recyclerView = root.findViewById(R.id.rvListScore);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(classMarkAdapter);
    }
}