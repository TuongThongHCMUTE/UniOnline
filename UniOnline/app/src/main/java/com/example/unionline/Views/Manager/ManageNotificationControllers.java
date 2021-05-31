package com.example.unionline.Views.Manager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.implementproject.DAO.NotificationDAO;
//import com.example.implementproject.R;
//import com.example.implementproject.adapter.NotificationAdapter;
//import com.example.implementproject.model.Notification;
import com.example.unionline.Adapters.Managers.NotificationAdapter;
import com.example.unionline.DAO.NotificationDAO;
import com.example.unionline.DTO.Notification;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import com.example.unionline.R;
public class ManageNotificationControllers extends Fragment implements View.OnClickListener {
    RecyclerView recyclerView;
    ArrayList<Notification> notifications;
    NotificationAdapter notificationAdapter;
    TextView tvIdNotify,tvTitleNotify,tvSentToNotify,tvSentFromNotify,tvDateCreate;
    ImageButton btAdd,imageButtonBack;
    Button btAddNotify,btClose;
    Spinner spinnerSentTo;
    EditText tvContentNotify;
    private  NotificationAdapter.RecyclerViewClickListener listener;
    DatabaseReference mDatabase;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
    View root=inflater.inflate(R.layout.fragment_manager_notification,container,false);


    setRecyclerView(root);
    imageButtonBack=root.findViewById(R.id.imageButtonBack);
    imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

    btAdd=root.findViewById(R.id.imageButton10);
    btAdd.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            OpenNotificationDialog(true,-1);
        }
    });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //Notification notification=notifications.get(direction);
                AlertDialog.Builder builder = new AlertDialog.Builder(viewHolder.itemView.getContext());
                builder.setMessage("Do you want delete notification !!!")
                        // positiveButton là nút thuận : đặt là OK
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Notification notification=notifications.get(viewHolder.getAdapterPosition());

                                boolean status= NotificationDAO.getInstance().deleteNotification(notification.getId());
                                if(status)
                                {
                                    Toast.makeText(getContext(), "Delete notification completed", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(getContext(), "Delete notification failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        // ngược lại negative là nút nghịch : đặt là cancel
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                                notificationAdapter.notifyDataSetChanged();
                            }
                        });
                // tạo dialog và hiển thị
                builder.create().show();

                notificationAdapter.notifyDataSetChanged();
            }
        }).attachToRecyclerView(recyclerView);

        return root;
    }


    private void setRecyclerView(View root) {
        recyclerView=root.findViewById(R.id.recyclerViewThongBao);
        notifications=new ArrayList<>();
        notificationAdapter=new NotificationAdapter(getContext(),notifications,listener);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(notificationAdapter);
        mDatabase = FirebaseDatabase.getInstance().getReference("Notifications");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                notifications.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                   Notification notification= dataSnapshot.getValue(Notification.class);
                   System.out.println(notification.getContent());
                    notifications.add(notification);

                }
                System.out.println("Value is"+notifications.size());
                notificationAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void OpenNotificationDialog(boolean isAddNew,@NonNull int position)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.fragment_manager_addnotification, null);
        AlertDialog alertDialog = builder.create();
        alertDialog.setView(view);
        alertDialog.show();
        spinnerSentTo=view.findViewById(R.id.spSendTo);
        tvTitleNotify=view.findViewById(R.id.textTieuDe);
        tvContentNotify=view.findViewById(R.id.editContent);
        if(isAddNew==false)
        {

        }
        else
        {
            ChooseSentTo(view,"Gửi đến giáo viên");
        }
        btClose=view.findViewById(R.id.button_huy);
        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        btAddNotify=view.findViewById(R.id.button_addthongbao);
        btAddNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = mDatabase.push().getKey();
                String notifyTitle=tvTitleNotify.getText().toString();
                String notifyContent=tvContentNotify.getText().toString();
                String notifySentTo=spinnerSentTo.getSelectedItem().toString();
                Date dateNow=java.util.Calendar.getInstance().getTime();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String dateCreate=simpleDateFormat.format(dateNow);
                String notifySentFrom="Quan ly khoa";
                if(isAddNew)
                {
                    Notification notification=new Notification(key,notifyTitle,notifyContent,0,notifySentFrom,notifySentTo,dateCreate);
                    //NotificationDAO notificationDAO=new NotificationDAO();
                    NotificationDAO.getInstance().setValude(notification);
                }
                alertDialog.cancel();
            }
        });
    }


    @Override
    public void onClick(View v) {

    }
    public void ChooseSentTo(View view, String sendTo)
    {
        spinnerSentTo=(Spinner) view.findViewById(R.id.spSendTo);
        String[] listTeacher = {"Gửi đến giáo viên","Gửi đến sinh viên"};

        List<String> listTeachers = Arrays.asList(listTeacher);
        Object[] objecTeacher = listTeachers.toArray();

        ArrayAdapter adapterDate = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item, objecTeacher);
        adapterDate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerSentTo.setAdapter(adapterDate);
        spinnerSentTo.setSelection(adapterDate.getPosition(sendTo));
    }
}
