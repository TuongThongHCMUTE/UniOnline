package com.example.unionline.Views.Students;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unionline.Adapters.Students.AttendanceAdapter;
import com.example.unionline.Common;
import com.example.unionline.DTO.Attendance;
import com.example.unionline.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.naishadhparmar.zcustomcalendar.CustomCalendar;
import org.naishadhparmar.zcustomcalendar.OnDateSelectedListener;
import org.naishadhparmar.zcustomcalendar.OnNavigationButtonClickedListener;
import org.naishadhparmar.zcustomcalendar.Property;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class StudentScheduleActivity extends AppCompatActivity implements OnNavigationButtonClickedListener {

    ImageView backIcon, ivBackDate, ivNextDate;
    TextView tvActivityName, tvDate;
    Date date, calendarDate;
    String strDate;

    RecyclerView recyclerView;
    ArrayList<Attendance> attendances, attendanceCalendars;
    AttendanceAdapter adapter;
    DatabaseReference mDatabase;
    Query query;

    CustomCalendar customCalendar;

    private AttendanceAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_schedule);

        date = new Date();
        tvDate = findViewById(R.id.tvDate);
        tvDate.setOnClickListener((View v) -> {
            openDialogCalendar();
        });
        setDate();

        setOnClickListener();
        setRecyclerView();

        // Set activity name on toolbar
        tvActivityName = (TextView) findViewById(R.id.activity_name);
        tvActivityName.setText("Thời khóa biểu");

        //Set event click for back icon
        backIcon = (ImageView) findViewById(R.id.left_icon);
        backIcon.setOnClickListener((View v) -> {
            this.finish();
        });

        ivBackDate = (ImageView)findViewById(R.id.ivBackDate);
        ivBackDate.setOnClickListener((View v) -> {
            subOneDay();
        });

        ivNextDate = (ImageView)findViewById(R.id.ivNextDate);
        ivNextDate.setOnClickListener((View v) -> {
            addOneDay();
        });
    }

    private void setDate(){
        strDate = android.text.format.DateFormat.format("dd/MM/yyyy", date).toString();
        tvDate.setText(android.text.format.DateFormat.format("EEEE dd/MM/yyyy", date).toString());
        loadListAttendance();
    }

    private void addOneDay(){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        date = c.getTime();

        setDate();
    }

    private void subOneDay(){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -1);
        date = c.getTime();

        setDate();
    }

    private void setOnClickListener() {
        listener = new AttendanceAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(StudentScheduleActivity.this, StudentAttendanceActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("attendance", (Serializable) attendances.get(position));
                intent.putExtras(bundle);

                startActivity(intent);
            }
        };
    }

    private void setRecyclerView(){

        recyclerView = findViewById(R.id.rvAttendance);

        attendances = new ArrayList<>();
        attendanceCalendars = new ArrayList<>();

        loadListAttendance();

        adapter = new AttendanceAdapter(this, attendances, listener);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(adapter);
    }

    private void loadListAttendance(){
        // Get data from firebase
        mDatabase = FirebaseDatabase.getInstance().getReference("Attendances").child(Common.semester.getSemesterId());
        query = mDatabase.orderByChild("studentId").equalTo(Common.user.getUserId());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                attendances.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Attendance attendance = dataSnapshot.getValue(Attendance.class);

                    assert attendance != null;
                    if(attendance.getFullDate()!=null){
                        if(attendance.getFullDate().equals(strDate)){
                            attendances.add(attendance);
                        }

                        attendanceCalendars.add(attendance);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void openDialogCalendar() {
        calendarDate = date;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.calendar_dialog, null);
        AlertDialog alertDialog = builder.create();
        alertDialog.setView(view);

        createCustomCalendar(view, alertDialog);

        Button btClose = view.findViewById(R.id.btClose);
        btClose.setOnClickListener((View v) -> {
            alertDialog.cancel();
        });

        alertDialog.show();
    }

    private void createCustomCalendar(View v, AlertDialog alertDialog){
        //Assign variable
        customCalendar = v.findViewById(R.id.custom_calendar);

        //Initialize description hash map
        HashMap<Object, Property> desHashMap = new HashMap<>();

        //Initialize default property
        Property defaultProperty = new Property();
        //Initialize default resource
        defaultProperty.layoutResource = R.layout.calendar_default_view;
        //Initialize and assign variable
        defaultProperty.dateTextViewResource = R.id.text_view;
        desHashMap.put("default", defaultProperty);

        //For current date
        Property currentProperty = new Property();
        currentProperty.layoutResource = R.layout.calendar_current_view;
        currentProperty.dateTextViewResource = R.id.text_view;
        desHashMap.put("current", currentProperty);

        //For present date
        Property presentProperty = new Property();
        presentProperty.layoutResource = R.layout.calendar_present_view;
        presentProperty.dateTextViewResource = R.id.text_view;
        desHashMap.put("present", presentProperty);

        //Set desc hash map on custom calender
        customCalendar.setMapDescToProp(desHashMap);

        //Initialize date hash map
        HashMap<Integer, Object> dateHashMap = new HashMap<>();

        //Initialize calender
        Calendar calender = Calendar.getInstance();

        //Put values
        for(int i = 0; i< attendanceCalendars.size();i++){
            String month = (calender.get(Calendar.MONTH) + 1) + "/" + calender.get(Calendar.YEAR);
            if(attendanceCalendars.get(i).getFullDate().contains(month)){

                try {
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                    cal.setTime(sdf.parse(attendanceCalendars.get(i).getFullDate()));// all done

                    dateHashMap.put(cal.get(Calendar.DAY_OF_MONTH), "present");
                } catch (ParseException ex){
                }

            }
        }

        //Set date
        dateHashMap.put(calender.get(Calendar.DAY_OF_MONTH), "current");
        customCalendar.setDate(calender, dateHashMap);

        //Set event click
        customCalendar.setOnNavigationButtonClickedListener(CustomCalendar.NEXT, this);
        customCalendar.setOnNavigationButtonClickedListener(CustomCalendar.PREVIOUS,this);

        customCalendar.setOnDateSelectedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(View view, Calendar selectedDate, Object desc) {
                //Get string date
                date = selectedDate.getTime();
                setDate();
                alertDialog.cancel();
            }
        });        
    }

    @Override
    public Map<Integer, Object>[] onNavigationButtonClicked(int whichButton, Calendar newMonth) {
        Map<Integer, Object>[] arr = new Map[2];
        arr[0] = new HashMap<>();
        //Put values
        for(int i = 0; i< attendanceCalendars.size();i++){
            String month = (newMonth.get(Calendar.MONTH) + 1) + "/" + newMonth.get(Calendar.YEAR);
            if(attendanceCalendars.get(i).getFullDate().contains(month)){
                try {
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                    cal.setTime(sdf.parse(attendanceCalendars.get(i).getFullDate()));// all done

                    arr[0].put(cal.get(Calendar.DAY_OF_MONTH), "present");
                } catch (ParseException ex){ }
            }

            Calendar calender = Calendar.getInstance();
            if(newMonth.get(Calendar.MONTH) == calender.get(Calendar.MONTH) && newMonth.get(Calendar.YEAR) == calender.get(Calendar.YEAR)){
                arr[0].put(calender.get(Calendar.DAY_OF_MONTH), "current");
            }
        }
        return arr;
    }
}