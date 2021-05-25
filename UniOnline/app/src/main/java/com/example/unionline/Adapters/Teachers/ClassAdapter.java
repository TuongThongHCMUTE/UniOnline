package com.example.unionline.Adapters.Teachers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unionline.DTO.Class;
import com.example.unionline.R;

import java.util.ArrayList;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {

    Context context;
    ArrayList<Class> classes;

    public ClassAdapter(Context context, ArrayList<Class> classes) {
        this.context = context;
        this.classes = classes;
    }

    @NonNull
    @Override
    public ClassAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_class, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassAdapter.ViewHolder holder, int position) {
        Class aClass = classes.get(position);

        String className = aClass.getName();
        String classDate = "Từ ngày " + aClass.getEndDate().toString() + "dến ngày" + aClass.getEndDate().toString();

        holder.txtClassName.setText(className);
        holder.txtClassDate.setText(classDate);
    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtClassName, txtClassDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtClassName = itemView.findViewById(R.id.txtClassName);
            txtClassDate = itemView.findViewById(R.id.txtClassDate);

            itemView.setOnClickListener((View v) -> {

            });
        }
    }
}
