package com.example.unionline.Views.Teachers.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.unionline.R;
import com.example.unionline.Views.Teachers.TeacherAddNotificationActivity;
import com.example.unionline.Views.Teachers.TeacherListClassesActivity;
import com.example.unionline.Views.Teachers.TeacherListNotificationsActivity;

public class TeacherInteractionFragment extends Fragment {

    View view;
    CardView cvSchedule, cvClass, cvMark, cvApplication, cvNotification, cvProfile;


    public TeacherInteractionFragment() {
        // Required empty public constructor
    }

    public static TeacherInteractionFragment newInstance(String param1, String param2) {
        TeacherInteractionFragment fragment = new TeacherInteractionFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_teacher_interaction, container, false);

        mapView(view);

        cvClass.setOnClickListener(onClickListener);
        cvNotification.setOnClickListener(onClickListener);

        return view;
    }

    /**
     * Mapping variables with view in layout
     * @param view: fragment_teacher_interaction
     */
    private void mapView(View view) {
        cvSchedule      = (CardView) view.findViewById(R.id.cvTeacherSchedule);
        cvClass         = (CardView) view.findViewById(R.id.cvTeacherClass);
        cvMark          = (CardView) view.findViewById(R.id.cvTeacherMark);
        cvApplication   = (CardView) view.findViewById(R.id.cvTeacherApplication);
        cvNotification  = (CardView) view.findViewById(R.id.cvTeacherNotification);
        cvProfile       = (CardView) view.findViewById(R.id.cvTeacherAccount);
    }

    private View.OnClickListener onClickListener = (View v) -> {
        Intent intent;

        switch (v.getId()) {
            case R.id.cvTeacherSchedule:
                intent = new Intent(this.getActivity(), TeacherListClassesActivity.class);
                startActivity(intent);
                break;
            case R.id.cvTeacherClass:
                intent = new Intent(this.getActivity(), TeacherListClassesActivity.class);
                startActivity(intent);
                break;
            case R.id.cvTeacherMark:
                intent = new Intent(this.getActivity(), TeacherListClassesActivity.class);
                startActivity(intent);
                break;
            case R.id.cvTeacherApplication:
                intent = new Intent(this.getActivity(), TeacherListClassesActivity.class);
                startActivity(intent);
                break;
            case R.id.cvTeacherNotification:
                intent = new Intent(this.getActivity(), TeacherListNotificationsActivity.class);
                startActivity(intent);
                break;
            case R.id.cvTeacherAccount:
                intent = new Intent(this.getActivity(), TeacherListClassesActivity.class);
                startActivity(intent);
                break;
        }
    };
}