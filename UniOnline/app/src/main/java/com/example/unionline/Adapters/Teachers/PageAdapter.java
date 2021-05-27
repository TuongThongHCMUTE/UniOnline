package com.example.unionline.Adapters.Teachers;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.unionline.DTO.Class;
import com.example.unionline.Views.Teachers.Fragments.TeacherClassInfoFragment;
import com.example.unionline.Views.Teachers.Fragments.TeacherMarkFragment;
import com.example.unionline.Views.Teachers.Fragments.TeacherUpdateProcessFragment;

import java.io.Serializable;

public class PageAdapter extends FragmentPagerAdapter {

    private int numberOfTabs;
    private Class aClass;

    public PageAdapter(@NonNull FragmentManager fm, int numberOfTabs, Class aClass) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
        this.aClass = aClass;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("class", (Serializable) aClass);

        switch (position) {
            case 0:
                TeacherClassInfoFragment fragment = new TeacherClassInfoFragment();
                fragment.setArguments(bundle);
                return fragment;
            case 1:
                return new TeacherUpdateProcessFragment();
            case 2:
                return new TeacherMarkFragment();
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
