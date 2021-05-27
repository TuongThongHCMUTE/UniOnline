package com.example.unionline.Views.Students;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.service.quickaccesswallet.GetWalletCardsCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.unionline.R;
import com.example.unionline.Views.Teachers.TeacherListClassesActivity;


public class StudentInteractionFragment extends Fragment {

    CardView cvSchedule;
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

    }

    private View.OnClickListener onClickListener = (View v) -> {
        Intent intent;
        switch (v.getId()) {
            case R.id.cvStudentSchedule:
                intent = new Intent(this.getActivity(), TeacherListClassesActivity.class);
                startActivity(intent);
                break;
        }
    };
}