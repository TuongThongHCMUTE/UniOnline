package com.example.unionline.Views.Admin;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.unionline.DAO.AdminDAO;
import com.example.unionline.DTO.User;
import com.example.unionline.R;

public class AdminStudentAddFragment extends DialogFragment {

    EditText et_username_Student, et_name_Student, et_phone_Student, et_mail_Student, et_address_Student, et_major_student;
    Button btn_add_Student;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public static AdminStudentAddFragment newInstance(String param1, String param2) {
        AdminStudentAddFragment fragment = new AdminStudentAddFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_admin_parent_add, container, false);

        et_username_Student = view.findViewById(R.id.et_admin_parent_add_username);
        et_name_Student = view.findViewById(R.id.et_admin_parent_add_fullname);
        et_phone_Student = view.findViewById(R.id.et_admin_parent_add_phone);
        et_mail_Student = view.findViewById(R.id.et_admin_parent_add_email);
        et_address_Student = view.findViewById(R.id.et_admin_parent_add_address);
        et_major_student = view.findViewById(R.id.et_admin_student_edit_major);
        btn_add_Student = view.findViewById(R.id.iv_add_quanly_sinhvien);

        btn_add_Student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_mail_Student.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Please fill in al the field!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(et_username_Student.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Please fill in al the field!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(et_name_Student.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Please fill in al the field!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(et_phone_Student.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Please fill in al the field!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (emailCheck() == false){
                    Toast.makeText(getActivity(), "Invalid Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    User studentAdd = new User("1", "123", et_name_Student.getText().toString(), et_mail_Student.getText().toString(), Long.valueOf(et_phone_Student.getText().toString()), "CNTT", true, 4, et_address_Student.getText().toString());
                    AdminDAO.getInstance().setStudentValue(studentAdd);
                    Toast.makeText(getActivity(), "Added Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
    boolean emailCheck (){
        if (et_mail_Student.getText().toString().trim().matches(emailPattern)) {
            return true;
        }
        else
        {
            return false;
        }
    }
}