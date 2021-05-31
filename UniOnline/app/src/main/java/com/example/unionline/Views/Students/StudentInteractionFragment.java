package com.example.unionline.Views.Students;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.unionline.R;

import java.util.Calendar;


public class StudentInteractionFragment extends Fragment {

    CardView cvSchedule, cvClass, cvMark, cvStudentApplication;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_student_interaction, container, false);

        mapView(view);

        return view;
    }

    private void mapView(View view){
        cvSchedule = view.findViewById(R.id.cvStudentSchedule);
        cvSchedule.setOnClickListener(onClickListener);

        cvClass = view.findViewById(R.id.cvStudentClass);
        cvClass.setOnClickListener(onClickListener);

        cvMark = view.findViewById(R.id.cvStudentMark);
        cvMark.setOnClickListener(onClickListener);

        cvStudentApplication = view.findViewById(R.id.cvStudentApplication);
        cvStudentApplication.setOnClickListener(onClickListener);

    }

    private View.OnClickListener onClickListener = (View v) -> {
        Intent intent;
        switch (v.getId()) {
            case R.id.cvStudentSchedule:
                intent = new Intent(this.getActivity(), StudentScheduleActivity.class);
                startActivity(intent);
                break;
            case R.id.cvStudentClass:
                intent = new Intent(this.getActivity(), StudentEnrollmentActivity.class);
                startActivity(intent);
                break;
            case R.id.cvStudentMark:
                intent = new Intent(this.getActivity(), StudentMarkActivity.class);
                startActivity(intent);
                break;
            case R.id.cvStudentApplication:
                intent = new Intent(this.getActivity(), StudentAbsenceApplicationActivity.class);
                startActivity(intent);

        }
    };
}