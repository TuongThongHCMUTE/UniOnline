package com.example.unionline.Adapters.Students;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.unionline.DTO.Class;
import com.example.unionline.DTO.Enrollment;
import com.example.unionline.Views.Students.StudentClassInfoFragment;
import com.example.unionline.Views.Students.StudentLessonFragment;
import com.example.unionline.Views.Teachers.Fragments.TeacherClassInfoFragment;
import com.example.unionline.Views.Teachers.Fragments.TeacherMarkFragment;
import com.example.unionline.Views.Teachers.Fragments.TeacherUpdateProcessFragment;

import java.io.Serializable;

public class PageAdapter extends FragmentPagerAdapter {

    private int numberOfTabs;
    private Enrollment enrollment;

    public PageAdapter(@NonNull FragmentManager fm, int numberOfTabs, Enrollment enrollment) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
        this.enrollment = enrollment;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("enrollment", (Serializable) enrollment);

        switch (position) {
            case 0:
                StudentLessonFragment studentLessonFragment = new StudentLessonFragment();
                studentLessonFragment.setArguments(bundle);
                return studentLessonFragment;
            case 1:
                StudentClassInfoFragment studentClassInfoFragment = new StudentClassInfoFragment();
                studentClassInfoFragment.setArguments(bundle);
                return studentClassInfoFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
