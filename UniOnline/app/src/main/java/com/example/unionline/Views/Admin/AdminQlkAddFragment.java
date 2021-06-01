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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminQlkAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminQlkAddFragment extends DialogFragment {

    EditText et_username_qlk, et_name_qlk, et_phone_qlk, et_mail_qlk;
    Button btn_add_qlk;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public static AdminQlkAddFragment newInstance(String param1, String param2) {
        AdminQlkAddFragment fragment = new AdminQlkAddFragment();
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

        View view = inflater.inflate(R.layout.fragment_admin_qlk_add, container, false);

        et_username_qlk = view.findViewById(R.id.et_admin_qlkhoa_add_username);
        et_name_qlk = view.findViewById(R.id.et_admin_qlkhoa_add_fullname);
        et_phone_qlk = view.findViewById(R.id.et_admin_qlkhoa_add_phone);
        et_mail_qlk = view.findViewById(R.id.et_admin_qlkhoa_add_email);
        btn_add_qlk = view.findViewById(R.id.btn_admin_add_qlk);

        btn_add_qlk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_mail_qlk.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Please fill in al the field!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(et_username_qlk.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Please fill in al the field!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(et_name_qlk.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Please fill in al the field!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(et_phone_qlk.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Please fill in al the field!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (emailCheck() == false){
                    Toast.makeText(getActivity(), "Invalid Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    User qlkAdd = new User("1", "123", et_name_qlk.getText().toString(), et_mail_qlk.getText().toString(), et_phone_qlk.getText().toString(), "CNTT", true, 3, null);
                    AdminDAO.getInstance().setQlkhoaValue(qlkAdd);
                    Toast.makeText(getActivity(), "Added Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
    boolean emailCheck (){
        if (et_mail_qlk.getText().toString().trim().matches(emailPattern)) {
            return true;
        }
        else
        {
            return false;
        }
    }
}