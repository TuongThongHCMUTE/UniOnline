package com.example.unionline.Views.Teachers.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.unionline.Common;
import com.example.unionline.DAO.NotificationDAO;
import com.example.unionline.DTO.Class;
import com.example.unionline.DTO.Notification;
import com.example.unionline.R;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TeacherAddNotificationFragment extends Fragment {

    private static final String ARG_IDS = "ListIDs";
    private String classIds;
    private EditText edTittle, edContent;
    private Button btnSend;

    public TeacherAddNotificationFragment() {
        // Required empty public constructor
    }

    public static TeacherAddNotificationFragment newInstance(String classIds) {
        TeacherAddNotificationFragment fragment = new TeacherAddNotificationFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_IDS, classIds);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            classIds = getArguments().getString(ARG_IDS);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_teacher_add_notification, container, false);

        edTittle    = (EditText) root.findViewById(R.id.edTittle);
        edContent   = (EditText) root.findViewById(R.id.edContent);
        btnSend     = (Button) root.findViewById(R.id.btnSendNotification);
        btnSend.setOnClickListener((View v) -> {
            sendNotification();
        });

        return root;
    }

    private void sendNotification() {
        Notification notification = new Notification();
        notification.setTitle(edTittle.getText().toString());
        notification.setContent(edContent.getText().toString());
        notification.setSentFrom(Common.user.getUserId());
        notification.setSentTo(classIds);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date createDate = new Date();
        notification.setCreateDate(formatter.format(createDate));

        NotificationDAO.getInstance().insertNotification(notification);
    }
}
