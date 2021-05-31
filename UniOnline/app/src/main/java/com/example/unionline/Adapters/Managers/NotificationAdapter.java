package com.example.unionline.Adapters.Managers;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.implementproject.R;
//import com.example.implementproject.model.Notification;
import com.example.unionline.R;
import com.example.unionline.DTO.Notification;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    Context context;
    ArrayList<Notification> notifications;
    private RecyclerViewClickListener listener;

    public NotificationAdapter(Context context, ArrayList<Notification> notifications, RecyclerViewClickListener listener) {
        this.context = context;
        this.notifications = notifications;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.item_manager_notification,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notification notification =notifications.get(position);
        holder.tvIdNotify.setText(notification.getId());
        holder.tvContentNotify.setText(notification.getContent());
        holder.tvSentFromNotify.setText(notification.getSentFrom());
        holder.tvTitleNotify.setText(notification.getTitle());
        holder.tvDateCreate.setText(notification.getCreateDate());
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, View.OnTouchListener {
        TextView tvIdNotify,tvTitleNotify,tvContentNotify,tvSentToNotify,tvSentFromNotify,tvDateCreate;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ConstraintLayout itemNotification;
            tvIdNotify=itemView.findViewById(R.id.tvIdNotify);
            tvTitleNotify=itemView.findViewById(R.id.tvTitle);
            tvContentNotify=itemView.findViewById(R.id.tvContent);
            tvSentFromNotify=itemView.findViewById(R.id.tvSentFrom);
            tvDateCreate=itemView.findViewById(R.id.tvDateCreateNotify);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(itemView,getAdapterPosition());
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            listener.onCreateContextMenu(menu,getAdapterPosition());
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            listener.onTouch(itemView,getAdapterPosition());
            return false;
        }
    }
    public interface RecyclerViewClickListener{
        void onClick(View v, int position);

        void onCreateContextMenu(ContextMenu menu, int position);

        void onTouch(View v, int position);
    }
}
