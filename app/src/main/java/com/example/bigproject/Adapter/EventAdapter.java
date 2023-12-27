package com.example.bigproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bigproject.Model.Event;
import com.example.bigproject.R;
import com.example.bigproject.Utils.CalendarUtils;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {
    public EventAdapter(@NonNull Context context, List<Event> events)
    {
        super(context, 0, events);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Event event = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);

        TextView eventCellTV = convertView.findViewById(R.id.eventCellTV);

        String eventTitle = event.getClassTitle() +"\n"+
                            event.getLecturerName() +"\n"+
                            event.getClassNumber()+"\n"+
                            CalendarUtils.formattedTime(event.getTime());
        eventCellTV.setText(eventTitle);
        return convertView;
    }
}
