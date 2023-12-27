package com.example.bigproject.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigproject.Adapter.ScheduleAdapter;
import com.example.bigproject.Model.Event;
import com.example.bigproject.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Schedule extends AppCompatActivity  {
    private CalendarView calendarView;
    private String dateSelected;
    private DatabaseReference dbReference;

    private List<Event> eventList;
    private RecyclerView recyclerView;
    private ScheduleAdapter scheduleAdapter;
    private FirebaseFirestore firestore;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_main);

        calendarView = findViewById(R.id.calendarView);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.eventRecyclerView);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        eventList = new ArrayList<>();

//        scheduleAdapter = new ScheduleAdapter(eventList);

        recyclerView.setAdapter(scheduleAdapter);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                dateSelected = Integer.toString(year)+Integer.toString(month)+Integer.toString(dayOfMonth);
                calendarClicked();

            }
        });
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setSelectedItemId(R.id.nut_TKB);



        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nut_subject){
                    startActivity(new Intent(getApplicationContext(), Subject.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                } else if(item.getItemId() == R.id.nut_home)
                {
                    startActivity(new Intent(getApplicationContext(), Home.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                } else if(item.getItemId() == R.id.nut_qr)
                {
                    startActivity(new Intent(getApplicationContext(), Diemdanh.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                }else if(item.getItemId() == R.id.nut_individual)
                {
                    startActivity(new Intent(getApplicationContext(), Setting_Activity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                }

                return false;
            }
        });
    }

    private void calendarClicked(){
        eventList.clear();

        // Get the current user's ID
        String userId = auth.getCurrentUser().getUid();

        // Create a reference to the user's events collection in Firestore
        String collectionPath = "users/" + userId + "/events";
        firestore.collection(collectionPath).whereEqualTo("eventDate", dateSelected)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            // Convert each document to an Event object
                            Event event = documentSnapshot.toObject(Event.class);
                            eventList.add(event);
                        }

                        // Notify the adapter that the data has changed
                        scheduleAdapter.notifyDataSetChanged();
                    }
                });
    }
}



