package com.example.unionline.Views.Admin;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.unionline.DTO.User;
import com.example.unionline.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminQlkEditFragment extends DialogFragment {

    EditText et_edit_qlk_username, et_edit_qlk_fullname, et_edit_qlk_phone, et_edit_qlk_mail;
    Button btnQlkUpdate;
    User user;
    DatabaseReference databaseReference;

    public AdminQlkEditFragment() {
        // Required empty public constructor
    }

    public static AdminQlkEditFragment newInstance(String param1, String param2) {
        AdminQlkEditFragment fragment = new AdminQlkEditFragment();
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
        View view = inflater.inflate(R.layout.fragment_admin_qlk_edit, container, false);

        et_edit_qlk_username = view.findViewById(R.id.et_admin_qlkhoa_edit_username);
        et_edit_qlk_fullname = view.findViewById(R.id.et_admin_qlkhoa_edit_fullname);
        et_edit_qlk_phone = view.findViewById(R.id.et_admin_qlkhoa_edit_phone);
        et_edit_qlk_mail = view.findViewById(R.id.et_admin_qlkhoa_edit_email);

        return view;
    }
}