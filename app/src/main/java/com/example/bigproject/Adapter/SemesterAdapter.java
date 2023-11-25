package com.example.bigproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigproject.Domain.SemesterDomain;
import com.example.bigproject.R;

import java.util.List;

public class SemesterAdapter extends RecyclerView.Adapter<SemesterAdapter.SemesterViewHolder> {

    private Context context;
    private List<SemesterDomain> semesterList;

    public SemesterAdapter(Context context, List<SemesterDomain> semesterList) {
        this.context = context;
        this.semesterList = semesterList;
    }

    @NonNull
    @Override
    public SemesterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewholer_semester_result_list, parent, false);
        return new SemesterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SemesterViewHolder holder, int position) {
        SemesterDomain semester = semesterList.get(position);

        holder.classCodeTextView.setText(semester.getClass_code());
        holder.class1TextView.setText(semester.getClass1());
        holder.creditTextView.setText(String.valueOf(semester.getCredit()));
        holder.progressTextView.setText(String.valueOf(semester.getProgress()));
        holder.practiceTextView.setText(String.valueOf(semester.getPractice()));
        holder.midTermTextView.setText(String.valueOf(semester.getMid_term()));
        holder.endTermTextView.setText(String.valueOf(semester.getEnd_term()));
        holder.averageScoreTextView.setText(String.valueOf(semester.getAverage_score()));
    }

    @Override
    public int getItemCount() {
        return semesterList.size();
    }

    public class SemesterViewHolder extends RecyclerView.ViewHolder {
        TextView classCodeTextView, class1TextView, creditTextView, progressTextView,
                practiceTextView, midTermTextView, endTermTextView, averageScoreTextView;

        public SemesterViewHolder(@NonNull View itemView) {
            super(itemView);

            classCodeTextView = itemView.findViewById(R.id.class_code);
            class1TextView = itemView.findViewById(R.id.class1);
            creditTextView = itemView.findViewById(R.id.credit);
            progressTextView = itemView.findViewById(R.id.progress);
            practiceTextView = itemView.findViewById(R.id.practice);
            midTermTextView = itemView.findViewById(R.id.mid_term);
            endTermTextView = itemView.findViewById(R.id.end_term);
            averageScoreTextView = itemView.findViewById(R.id.average_score);
        }
    }
}

