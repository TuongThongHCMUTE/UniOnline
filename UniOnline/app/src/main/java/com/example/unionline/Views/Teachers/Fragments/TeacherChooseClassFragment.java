package com.example.unionline.Views.Teachers.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unionline.Adapters.Teachers.ClassCheckAdapter;
import com.example.unionline.Common;
import com.example.unionline.DTO.Class;
import com.example.unionline.R;
import com.example.unionline.Views.Teachers.TeacherAddNotificationActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TeacherChooseClassFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Class> classes;
    ClassCheckAdapter adapter;
    GridLayoutManager gridLayoutManager;
    DatabaseReference mDataBase;

    private ClassCheckAdapter.RecyclerViewClickListener listener;
    private String classesIds = "";

    Button btnAdd;

    public TeacherChooseClassFragment() {
        // Required empty public constructor
    }

    public static TeacherChooseClassFragment newInstance(Class aClass) {
        TeacherChooseClassFragment fragment = new TeacherChooseClassFragment();

        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_teacher_choose_class, container, false);

        setOnClickListener();
        setRecyclerView(root);

        btnAdd = (Button) root.findViewById(R.id.btnAddNotification);
        btnAdd.setOnClickListener((View v) -> {
            changeFragment();
        });

        return root;
    }

    private void setRecyclerView(View root) {

        // Initialize
        classes = new ArrayList<>();
        adapter = new ClassCheckAdapter(getContext(), classes, listener);
        gridLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);

        // Set adapter for recycler view
        recyclerView = root.findViewById(R.id.rvListClassesCheck);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        // Fill data from Firebase
        mDataBase = FirebaseDatabase.getInstance().getReference("Classes").child("2020_2021_HK1");
        Query query = mDataBase.orderByChild("teacherId").equalTo(Common.user.getUserId());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                classes.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
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

    private void setOnClickListener() {
        listener = (itemView, adapterPosition) -> {
            CheckBox cb = itemView.findViewById(R.id.cbIsChecked);

            if(cb.isChecked()) {
                classesIds += classes.get(adapterPosition).getClassId() + ";";
            } else {
                classesIds = classesIds.replace(classes.get(adapterPosition).getClassId() + ";", "");
            }

            Toast.makeText(getContext(), classesIds, Toast.LENGTH_SHORT).show();
        };
    }

    private void changeFragment() {
        TeacherAddNotificationFragment fragment = new TeacherAddNotificationFragment();
        Bundle bundle = new Bundle();
        bundle.putString("ListIDs", classesIds);
        fragment.setArguments(bundle);

        getFragmentManager().beginTransaction().replace(R.id.main, fragment)
                .addToBackStack(null)
                .commit();
    }
}
