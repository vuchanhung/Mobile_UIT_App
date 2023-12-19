package com.example.bigproject.Activity;

import static com.example.bigproject.Utils.ScheduleUtils.daysInWeekArray;
import static com.example.bigproject.Utils.ScheduleUtils.monthYearFromDate;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigproject.Adapter.EventAdapter;
import com.example.bigproject.Adapter.ScheduleAdapter;
import com.example.bigproject.Model.Event;
import com.example.bigproject.R;
import com.example.bigproject.Utils.ScheduleUtils;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.firebase.client.DataSnapshot;

import java.time.LocalDate;
import java.util.ArrayList;

public class Schedule extends AppCompatActivity implements ScheduleAdapter.OnItemListener {

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private ListView eventListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_main);
        initWidgets();
        setWeekView();
    }

    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
        eventListView = findViewById(R.id.eventListView);

    }

    private void setWeekView() {
        if (ScheduleUtils.selectedDate != null) {
            monthYearText.setText(monthYearFromDate(ScheduleUtils.selectedDate));
            ArrayList<LocalDate> days = daysInWeekArray(ScheduleUtils.selectedDate);


            ScheduleAdapter calendarAdapter = new ScheduleAdapter(days, this);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
            calendarRecyclerView.setLayoutManager(layoutManager);
            calendarRecyclerView.setAdapter(calendarAdapter);
            setEventAdpater();
        }

    }


    public void previousWeekAction(View view) {
        ScheduleUtils.selectedDate = ScheduleUtils.selectedDate.minusWeeks(1);
        setWeekView();
    }

    public void nextWeekAction(View view) {
        ScheduleUtils.selectedDate = ScheduleUtils.selectedDate.plusWeeks(1);
        setWeekView();
    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        ScheduleUtils.selectedDate = date;
        setWeekView();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setEventAdpater();
    }

    private void setEventAdpater()
    {
        ArrayList<Event> dailyEvents = Event.eventsForDate(ScheduleUtils.selectedDate);
        EventAdapter eventAdapter = new EventAdapter(getApplicationContext(), dailyEvents);
        eventListView.setAdapter(eventAdapter);
    }

    public void newEventAction(View view)
    {
        startActivity(new Intent(this, EventEditActivity.class));
    }







}

