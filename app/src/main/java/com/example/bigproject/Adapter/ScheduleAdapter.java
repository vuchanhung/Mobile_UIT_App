package com.example.bigproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigproject.Domain.ScheduleDomain;
import com.example.bigproject.R;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {

    private ArrayList<ScheduleDomain> scheduleItems;
    private Context context;

    public ScheduleAdapter(Context context, ArrayList<ScheduleDomain> scheduleItems) {
        this.context = context;
        this.scheduleItems = scheduleItems;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_schedule_list, parent, false);
        return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        ScheduleDomain currentItem = scheduleItems.get(position);

        holder.textViewCourseCode.setText(currentItem.getCourseCode());
        holder.textViewSession.setText(currentItem.getSession());
        holder.textViewLocation.setText(currentItem.getLocation());
        holder.textViewTeacher.setText(currentItem.getTeacher());
    }

    @Override
    public int getItemCount() {
        return scheduleItems.size();
    }

    public static class ScheduleViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewCourseCode;
        public TextView textViewSession;
        public TextView textViewLocation;
        public TextView textViewTeacher;

        public ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCourseCode = itemView.findViewById(R.id.textView28);
            textViewSession = itemView.findViewById(R.id.textView30);
            textViewLocation = itemView.findViewById(R.id.textView31);
            textViewTeacher = itemView.findViewById(R.id.textView32);
        }
    }
}

