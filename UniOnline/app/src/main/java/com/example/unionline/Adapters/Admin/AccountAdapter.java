package com.example.unionline.Adapters.Admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unionline.DTO.User;
import com.example.unionline.R;
import com.example.unionline.Views.Admin.AdminParentAccountActivity;
import com.example.unionline.Views.Admin.AdminQlkAccountActivity;
import com.example.unionline.Views.Admin.AdminStudentAccountActivity;
import com.example.unionline.Views.Admin.AdminTeacherAccountActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {

    private ArrayList<User> userList;

    public AccountAdapter(AdminQlkAccountActivity adminQlkAccountActivity, ArrayList<User> userList) {
        this.userList = userList;
    }

    public AccountAdapter(AdminParentAccountActivity adminParentAccountActivity, ArrayList<User> userList) {
        this.userList = userList;
    }

    public AccountAdapter(AdminStudentAccountActivity adminStudentAccountActivity, ArrayList<User> userList) {
        this.userList = userList;
    }

    public AccountAdapter(AdminTeacherAccountActivity adminTeacherAccountActivity, ArrayList<User> userList) {
        this.userList = userList;
    }

    public static void filterList(ArrayList<User> userList) {
    }

    @NonNull
    @NotNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admin_manage_qlkhoaaccount, parent, false);
        return new AccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AccountViewHolder holder, int position) {
        User user = userList.get(position);
        if (user == null) {
            return;
        }
        holder.tvQlkName.setText(user.getName());
        holder.tvQlkEmail.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        if (userList != null)
            return userList.size();
        return 0;
    }

    public class AccountViewHolder extends RecyclerView.ViewHolder {

        TextView tvQlkName, tvQlkEmail;
        ImageView btnQlkEdit;
        RelativeLayout layoutForegroundQlk;

        public AccountViewHolder(@NonNull @NotNull View itemView) {

            super(itemView);

            layoutForegroundQlk = itemView.findViewById(R.id.layout_foreground_qlk);
            tvQlkName = itemView.findViewById(R.id.tv_admin_qlk_name);
            tvQlkEmail = itemView.findViewById(R.id.tv_admin_qlk_email);
            btnQlkEdit = itemView.findViewById(R.id.btn_edit_qlk);

        }
    }

    public void removeItem (int index) {
        userList.remove(index);

        notifyItemRemoved(index);
    }

    public void undoItem (User user, int index) {
        userList.add(index, user);

        notifyItemInserted(index);
    }

}
