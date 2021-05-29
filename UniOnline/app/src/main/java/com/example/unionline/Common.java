package com.example.unionline;

import com.example.unionline.DTO.Class;
import com.example.unionline.DTO.Semester;
import com.example.unionline.DTO.User;

import java.util.ArrayList;
import java.util.List;

public class Common {
    public static User user;
    public static Semester semester;
    public static Class class_;

    public static final String roleAdmin = "Admin";
    public static final String roleManager = "Quản lý khoa";
    public static final String roleTeacher = "Giảng viên";
    public static final String roleStudent = "Sinh viên";
    public static final String roleParent = "Phụ huynh";

    // Attendance states
    public static final int ATTENDANCE_NOT_YET = 0;
    public static final int ATTENDANCE_ON_TIME = 1;
    public static final int ATTENDANCE_LATE = 2;
    public static final int ATTENDANCE_WITH_PERMISSION = 3;
    public static final int ATTENDANCE_WITHOUT_PERMISSION = 4;
    public static ArrayList<String> attendanceNames;

    //Set state for mark
    public static final int MARK_NOT_ENTER = 0; //Mark entered
    public static final int MARK_ENTER = 1; // Mark not yet entered

    //Set state for absence application
    public static final int AA_WAIT_FOR_APPROVAL = 0; //Mark entered
    public static final int AA_APPROVED = 1; // Mark not yet entered
    public static final int AA_NOT_APPROVED = 2; // Mark not yet entered
    public static ArrayList<String> aaNames;

    public Common(){
        //Set attendance name
        attendanceNames = new ArrayList<>();
        attendanceNames.add("Chưa điểm danh");
        attendanceNames.add("Đúng giờ");
        attendanceNames.add("Trễ");
        attendanceNames.add("Vắng có phép");
        attendanceNames.add("Vắng không phép");

        //Set  absence application name
        aaNames = new ArrayList<String>();
        aaNames.add("Đang chờ phê duyệt");
        aaNames.add("Đã được phê duyệt");
        aaNames.add("Không phê duyệt");
    }

}