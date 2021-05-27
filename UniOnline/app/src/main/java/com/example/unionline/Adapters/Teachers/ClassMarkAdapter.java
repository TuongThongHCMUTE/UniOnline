package com.example.unionline.Adapters.Teachers;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unionline.DTO.Score;
import com.example.unionline.R;

import java.util.ArrayList;

public class ClassMarkAdapter extends RecyclerView.Adapter<ClassMarkAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Score> scoreList;
    private ClassMarkAdapter.RecyclerViewClickListener listener;

    public ClassMarkAdapter(Context context, ArrayList<Score> scoreList, ClassMarkAdapter.RecyclerViewClickListener listener) {
        this.context = context;
        this.scoreList = scoreList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_teacher_mark, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Score score = scoreList.get(position);

        holder.tvStudentId.setText(score.getStudentId());
        holder.tvStudentName.setText(score.getStudentName());
        holder.edMidTermScore.setText(score.getMidScore());
        holder.edFinalScore.setText(score.getFinalScore());
    }

    @Override
    public int getItemCount() {
        return scoreList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener, View.OnClickListener {

        TextView tvStudentId, tvStudentName;
        EditText edMidTermScore, edFinalScore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStudentId     = (TextView) itemView.findViewById(R.id.txtId);
            tvStudentName   = (TextView) itemView.findViewById(R.id.txtName);
            edMidTermScore  = (EditText) itemView.findViewById(R.id.edMidTerm);
            edFinalScore    = (EditText) itemView.findViewById(R.id.edFinal);

            edMidTermScore.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String midTermScore = edMidTermScore.getText().toString();
                    scoreList.get(getAdapterPosition()).setMidScore(midTermScore);
                }
            });

            edFinalScore.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String finalScore = edFinalScore.getText().toString();
                    scoreList.get(getAdapterPosition()).setFinalScore(finalScore);
                }
            });

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onCLick(itemView, getAdapterPosition());
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            listener.onTouch(itemView, getAdapterPosition());
            return true;
        }
    }

    public interface RecyclerViewClickListener {

        void onTouch(View v, int position);

        void onCLick(View itemView, int adapterPosition);
    }

}