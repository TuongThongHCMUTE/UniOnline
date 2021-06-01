package com.example.unionline.Views.Teachers.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.unionline.Adapters.Teachers.ApplicationAdapter;
import com.example.unionline.Adapters.Teachers.ApplicationPagerAdapter;
import com.example.unionline.Adapters.Teachers.ClassMarkAdapter;
import com.example.unionline.Common;
import com.example.unionline.DTO.AbsenceApplication;
import com.example.unionline.DTO.Class;
import com.example.unionline.DTO.Enrollment;
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

public class TeacherDoneApplicationFragment extends Fragment {

    private static final String ARG_CLASS_IDS = "classIds";
    private ArrayList<String> classIds;

    RecyclerView recyclerView;
    List<AbsenceApplication> applications;
    ApplicationAdapter adapter;
    GridLayoutManager gridLayoutManager;
    DatabaseReference mData;

    private ApplicationAdapter.RecyclerViewClickListener listener;

    public TeacherDoneApplicationFragment() {
        // Required empty public constructor
    }

    public static TeacherDoneApplicationFragment newInstance(ArrayList<String> classIds) {
        TeacherDoneApplicationFragment fragment = new TeacherDoneApplicationFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CLASS_IDS, (Serializable) classIds);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            classIds = (ArrayList<String>) getArguments().getSerializable(ARG_CLASS_IDS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_teacher_application, container, false);

        setOnClickListener();
        setRecyclerView(root);

        return root;
    }

    private void setOnClickListener() {
        listener = new ApplicationAdapter.RecyclerViewClickListener() {

            @Override
            public void onTouch(View v, int position) {

            }

            @Override
            public void onCLick(View itemView, int adapterPosition) {
                AbsenceApplication application = applications.get(adapterPosition);
            }
        };
    }

    private void setRecyclerView(View root) {

        // Initialize
        applications = new ArrayList<>();

        adapter = new ApplicationAdapter(getContext(), (ArrayList<AbsenceApplication>) applications, listener);
        gridLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);

        // Set adapter for recycler view
        recyclerView = root.findViewById(R.id.rvAbsenceApplication);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        // Fill data from Firebase
        mData = FirebaseDatabase.getInstance().getReference("AbsenceApplications").child(Common.semester.getSemesterId());
        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                applications.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    AbsenceApplication application = dataSnapshot.getValue(AbsenceApplication.class);
                    if((classIds.contains(application.getClassId())) && (application.getState() != Common.AA_WAIT_FOR_APPROVAL)) {
                        applications.add(application);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

}