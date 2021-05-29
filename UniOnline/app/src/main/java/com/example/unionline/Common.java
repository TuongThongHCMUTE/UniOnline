package com.example.unionline;

import com.example.unionline.DTO.Class;
import com.example.unionline.DTO.Semester;
import com.example.unionline.DTO.User;

public class Common {
    public static User user;
    public static Semester semester;
    public static Class class_;

    public static String roleAdmin = "Admin";
    public static String roleManager = "Quản lý khoa";
    public static String roleTeacher = "Giảng viên";
    public static String roleStudent = "Sinh viên";
    public static String roleParent = "Phụ huynh";

    // Attendance states
    public static final int ATTENDANCE_NOT_YET = 0;
    public static final int ATTENDANCE_ON_TIME = 1;
    public static final int ATTENDANCE_LATE = 2;
    public static final int ATTENDANCE_WITH_PERMISSION = 3;
    public static final int ATTENDANCE_WITHOUT_PERMISSION = 4;
}