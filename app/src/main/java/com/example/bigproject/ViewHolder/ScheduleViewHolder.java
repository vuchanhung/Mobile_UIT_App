package com.example.bigproject.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bigproject.Adapter.ScheduleAdapter;
import com.example.bigproject.R;
import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;

public class ScheduleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private final ArrayList<LocalDate> days;
    public final View parentView;
    public final TextView dayOfMonth;
    private final ScheduleAdapter.OnItemListener onItemListener;
    public ScheduleViewHolder(@NonNull View itemView, ScheduleAdapter.OnItemListener onItemListener, ArrayList<LocalDate> days)
    {
        super(itemView);
        parentView = itemView.findViewById(R.id.parentView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
        this.days = days;
    }

    @Override
    public void onClick(View view)
    {
        int position = getBindingAdapterPosition();
        if (position != RecyclerView.NO_POSITION) {
            LocalDate date = days.get(position);
            if (date != null) {
                onItemListener.onItemClick(position, date);
            }
        }
    }
}

