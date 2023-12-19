package com.example.bigproject.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bigproject.Model.Event;
import com.example.bigproject.R;
import com.example.bigproject.Utils.ScheduleUtils;

import java.time.LocalTime;

public class EventEditActivity extends AppCompatActivity {
    private EditText eventNameET,eventLecturerET;
    private TextView eventDateTV, eventTimeTV;

    private LocalTime time;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        initWidgets();
        time = LocalTime.now();
        eventDateTV.setText("Date: " + ScheduleUtils.formattedDate(ScheduleUtils.selectedDate));
        eventTimeTV.setText("Time: " + ScheduleUtils.formattedTime(time));
    }

    private void initWidgets()
    {
        eventNameET = findViewById(R.id.eventNameET);
        eventLecturerET = findViewById(R.id.eventLecturerET);
        eventDateTV = findViewById(R.id.eventDateTV);
        eventTimeTV = findViewById(R.id.eventTimeTV);
    }

    public void saveEventAction(View view)
    {
        String eventName = eventNameET.getText().toString();
        String eventLecturerName = eventLecturerET.getText().toString();
        Event newEvent = new Event(eventName, eventLecturerName, ScheduleUtils.selectedDate, time);
        Event.eventsList.add(newEvent);
        finish();
    }
}
