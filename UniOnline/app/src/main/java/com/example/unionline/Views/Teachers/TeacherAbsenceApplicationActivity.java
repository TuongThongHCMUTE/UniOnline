package com.example.unionline.Views.Teachers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.unionline.Adapters.Teachers.ApplicationPagerAdapter;
import com.example.unionline.Common;
import com.example.unionline.DTO.Class;
import com.example.unionline.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TeacherAbsenceApplicationActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem tabPending, tabDone;
    private ApplicationPagerAdapter pagerAdapter;

    private ArrayList<String> classIds;
    private DatabaseReference mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_absence_application);

        getClassIdsFromFirebase();

        tabLayout   = (TabLayout) findViewById(R.id.teacher_absenceApplication_tabLayout);
        tabPending  = (TabItem) findViewById(R.id.tab_pending);
        tabDone     = (TabItem) findViewById(R.id.tab_done);
        viewPager   = (ViewPager) findViewById(R.id.teacher_absenceApplication_viewPager);

        pagerAdapter = new ApplicationPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), classIds);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0) {
                    pagerAdapter.notifyDataSetChanged();
                } else if (tab.getPosition() == 1) {
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void getClassIdsFromFirebase() {
        classIds = new ArrayList<>();
        // Fill data from Firebase
        mDataBase = FirebaseDatabase.getInstance().getReference("Classes").child(Common.semester.getSemesterId());
        Query query = mDataBase.orderByChild("teacherId").equalTo(Common.user.getUserId());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                classIds.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Class aClass = dataSnapshot.getValue(Class.class);
                    classIds.add(aClass.getClassId());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}