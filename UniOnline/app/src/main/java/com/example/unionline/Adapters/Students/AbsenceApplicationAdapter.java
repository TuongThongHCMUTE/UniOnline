package com.example.unionline.Adapters.Students;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unionline.Common;
import com.example.unionline.DTO.AbsenceApplication;
import com.example.unionline.DTO.Enrollment;
import com.example.unionline.R;

import java.util.ArrayList;

public class AbsenceApplicationAdapter extends RecyclerView.Adapter<AbsenceApplicationAdapter.ViewHolder> {

    Context context;
    ArrayList<AbsenceApplication> absenceApplications;
    RecyclerViewClickListener listener;

    public AbsenceApplicationAdapter(Context context, ArrayList<AbsenceApplication> absenceApplications, RecyclerViewClickListener listener) {
        this.context = context;
        this.absenceApplications = absenceApplications;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_student_absence_application, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AbsenceApplication absenceApplication = absenceApplications.get(position);

        holder.tvStudentName.setText(absenceApplication.getStudentName());
        holder.tvCreateDate.setText(absenceApplication.getDateCreate());
        holder.tvReasonAbsent.setText(absenceApplication.getReason());
        holder.tvClassName.setText(absenceApplication.getClassName());
        holder.tvClassTime.setText(absenceApplication.getClassTime());
        holder.tvAbsentState.setText(Common.aaNames.get(absenceApplication.getState()));
    }

    @Override
    public int getItemCount() {
        return absenceApplications.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvStudentName, tvCreateDate, tvReasonAbsent, tvClassName, tvClassTime, tvAbsentState;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStudentName = itemView.findViewById(R.id.tvStudentName);
            tvCreateDate = itemView.findViewById(R.id.tvCreateDate);
            tvReasonAbsent = itemView.findViewById(R.id.tvReasonAbsent);
            tvClassName = itemView.findViewById(R.id.tvClassName);
            tvClassTime = itemView.findViewById(R.id.tvClassTime);
            tvAbsentState = itemView.findViewById(R.id.tvAbsentState);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onCLick(itemView, getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener {
        void onCLick(View v, int position);
    }
}
