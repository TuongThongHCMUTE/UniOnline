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

public class AdminParentAddFragment extends DialogFragment {

    EditText et_username_parent, et_name_parent, et_phone_parent, et_mail_parent, et_address_parent;
    Button btn_add_parent;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public static AdminParentAddFragment newInstance(String param1, String param2) {
        AdminParentAddFragment fragment = new AdminParentAddFragment();
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

        et_username_parent = view.findViewById(R.id.et_admin_parent_add_username);
        et_name_parent = view.findViewById(R.id.et_admin_parent_add_fullname);
        et_phone_parent = view.findViewById(R.id.et_admin_parent_add_phone);
        et_mail_parent = view.findViewById(R.id.et_admin_parent_add_email);
        et_address_parent = view.findViewById(R.id.et_admin_parent_add_address);
        btn_add_parent = view.findViewById(R.id.ib_add_quanly_phuhuynh);

        btn_add_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_mail_parent.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Please fill in al the field!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(et_username_parent.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Please fill in al the field!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(et_name_parent.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Please fill in al the field!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(et_phone_parent.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Please fill in al the field!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (emailCheck() == false){
                    Toast.makeText(getActivity(), "Invalid Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    User parentAdd = new User("1", "123", et_name_parent.getText().toString(), et_mail_parent.getText().toString(), Long.valueOf(et_phone_parent.getText().toString()), null, true, 4, et_address_parent.getText().toString());
                    AdminDAO.getInstance().setParentValue(parentAdd);
                    Toast.makeText(getActivity(), "Added Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
    boolean emailCheck (){
        if (et_mail_parent.getText().toString().trim().matches(emailPattern)) {
            return true;
        }
        else
        {
            return false;
        }
    }
}