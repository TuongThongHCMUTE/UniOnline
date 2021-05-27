package com.example.unionline.Views.Teachers.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unionline.DAO.ClassDAO;
import com.example.unionline.DTO.Class;
import com.example.unionline.R;

import java.io.Serializable;

public class TeacherClassInfoFragment extends Fragment {

    private static final String ARG_CLASS = "class";

    private Class aClass;
    private TextView tvClassName, tvClassId, tvStartDate, tvEndDate, tvStatus, tvRoom, tvTime, tvProcess;

    public TeacherClassInfoFragment() {
        // Required empty public constructor
    }

    public static TeacherClassInfoFragment newInstance(Class aClass) {
        TeacherClassInfoFragment fragment = new TeacherClassInfoFragment();
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
        View root = inflater.inflate(R.layout.fragment_teacher_class_info, container, false);

        mapView(root);
        setValueForView();

        return root;
    }

    private void mapView(View root) {
        // Mapping variables with view
        tvClassName = (TextView) root.findViewById(R.id.txtClassName);
        tvClassId   = (TextView) root.findViewById(R.id.txtClassId);
        tvStartDate = (TextView) root.findViewById(R.id.txtStartDateValue);
        tvEndDate   = (TextView) root.findViewById(R.id.txtEndDateValue);
        tvStatus    = (TextView) root.findViewById(R.id.txtStatusValue);
        tvRoom      = (TextView) root.findViewById(R.id.txtRoomValue);
        tvTime      = (TextView) root.findViewById(R.id.txtTimeValue);
        tvProcess   = (TextView) root.findViewById(R.id.txtProcessValue);
    }

    private void setValueForView() {
        tvClassName.setText(aClass.getClassName());
        tvClassId.setText(aClass.getClassId());
        tvStartDate.setText(aClass.getStartDate());
        tvEndDate.setText(aClass.getEndDate());
        tvRoom.setText(aClass.getRoom());
        tvStatus.setText(aClass.getState());
        tvTime.setText("Từ " + aClass.getStartTime() + " đến " + aClass.getEndTime());
    }
}