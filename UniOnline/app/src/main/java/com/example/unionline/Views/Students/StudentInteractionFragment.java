package com.example.unionline.Views.Students;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.unionline.R;


public class StudentInteractionFragment extends Fragment {

    CardView cvSchedule, cvClass;
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
        }
    };
}