package com.example.bigproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigproject.Activity.Schedule;
import com.example.bigproject.Model.Event;
import com.example.bigproject.R;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.EventViewHolder>{
    private Context context;
    private List<Event> eventList;

    public ScheduleAdapter(Context context, List<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder_event_list, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.eventClassTitle.setText(event.getSubjectTitle());
        holder.eventLecturerName.setText(event.getLecturerName());
        holder.eventClassNumber.setText(event.getClassNumber());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Schedule.class);
//                intent.putExtra("event", event);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView eventClassTitle;
        TextView eventLecturerName;
        TextView eventClassNumber;


        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventClassTitle = itemView.findViewById(R.id.classTitle);
            eventLecturerName = itemView.findViewById(R.id.lecturerName);
            eventClassNumber = itemView.findViewById(R.id.classNumber);
        }
    }
}
