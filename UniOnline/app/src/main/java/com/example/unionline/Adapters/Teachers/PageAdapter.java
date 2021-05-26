package com.example.unionline.Adapters.Teachers;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.unionline.Views.Teachers.Fragments.TeacherClassInfoFragment;
import com.example.unionline.Views.Teachers.Fragments.TeacherMarkFragment;
import com.example.unionline.Views.Teachers.Fragments.TeacherUpdateProcessFragment;

public class PageAdapter extends FragmentPagerAdapter {

    private int numberOfTabs;


    public PageAdapter(@NonNull FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TeacherClassInfoFragment();
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
