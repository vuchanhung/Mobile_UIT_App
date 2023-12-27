package com.example.bigproject.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bigproject.Model.Event;
import com.example.bigproject.R;
import com.example.bigproject.Utils.CalendarUtils;

import java.time.LocalTime;

public class EventEditActivity extends AppCompatActivity {
    private EditText eventClassET,eventLecturerET,eventClassNumberET;
    private TextView eventDateTV;

    private LocalTime time;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        initWidgets();
        time = LocalTime.now();
        eventDateTV.setText("Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));
    }

    private void initWidgets()
    {
        eventClassET = findViewById(R.id.eventClassET);
        eventLecturerET = findViewById(R.id.eventLecturerET);
        eventClassNumberET = findViewById(R.id.eventClassNumberET);
        eventDateTV = findViewById(R.id.eventDateTV);
    }

    public void saveEventAction(View view)
    {
        String eventClassTitle = eventClassET.getText().toString();
        String eventLecturerName = eventLecturerET.getText().toString();
        String eventClassNumber = eventClassNumberET.getText().toString();
        Event newEvent = new Event(eventClassNumber, eventLecturerName,eventClassTitle,CalendarUtils.selectedDate, time);
        Event.eventsList.add(newEvent);
        finish();
    }
}
