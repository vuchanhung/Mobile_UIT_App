package com.example.bigproject.Adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigproject.R;
import com.example.bigproject.Utils.ScheduleUtils;
import com.example.bigproject.ViewHolder.ScheduleViewHolder;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleViewHolder>{
    private  ArrayList<LocalDate> days;
    private  OnItemListener onItemListener;

    private FirebaseFirestore db;

    public ScheduleAdapter(ArrayList<LocalDate> days, OnItemListener onItemListener)
    {
        this.days = days;
        this.onItemListener = onItemListener;
        this.db = FirebaseFirestore.getInstance();
    }

    public void updateData(ArrayList<LocalDate> newData) {
        this.days = newData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if(days.size() > 15) //month view
            layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        else // week view
            layoutParams.height = parent.getHeight();

        return new ScheduleViewHolder(view, onItemListener, days);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
        final LocalDate date = days.get(position);
        if(date == null)
            holder.dayOfMonth.setText("");
        else
        {
            holder.dayOfMonth.setText(String.valueOf(date.getDayOfMonth()));
            if(date.equals(ScheduleUtils.selectedDate))
            {
                holder.parentView.setBackgroundResource(R.drawable.rounded_corner);
            }
        }

        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemListener != null) {
                    onItemListener.onItemClick(position, date);
                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return days.size();
    }

    public interface  OnItemListener
    {
        void onItemClick(int position, LocalDate date);
    }
}
