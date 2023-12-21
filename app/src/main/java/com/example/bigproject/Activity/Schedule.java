package com.example.bigproject.Activity;

import static com.example.bigproject.Utils.ScheduleUtils.daysInWeekArray;
import static com.example.bigproject.Utils.ScheduleUtils.monthYearFromDate;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigproject.Adapter.EventAdapter;
import com.example.bigproject.Adapter.ScheduleAdapter;
import com.example.bigproject.Model.User;
import com.example.bigproject.Model.myClass;
import com.example.bigproject.R;
import com.example.bigproject.Utils.ScheduleUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.util.ArrayList;

public class Schedule extends AppCompatActivity implements ScheduleAdapter.OnItemListener {

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;

    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;
    ArrayList<myClass> classArrayList;  // Change the type to ArrayList<Class>

    FirebaseFirestore db;

    SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
    String mssv = preferences.getString("mssv", "");



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

    }

    private void setWeekView() {
        if (ScheduleUtils.selectedDate != null) {
            monthYearText.setText(monthYearFromDate(ScheduleUtils.selectedDate));
            ArrayList<LocalDate> days = daysInWeekArray(ScheduleUtils.selectedDate);


            ScheduleAdapter calendarAdapter = new ScheduleAdapter(days, this);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
            calendarRecyclerView.setLayoutManager(layoutManager);
            calendarRecyclerView.setAdapter(calendarAdapter);
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
        db = FirebaseFirestore.getInstance();
        classArrayList = new ArrayList<>();  // Change the type to ArrayList<Class>
        eventAdapter = new EventAdapter(Schedule.this, classArrayList);

        recyclerView.setAdapter(eventAdapter);  // Set the adapter to the RecyclerView

        EventChangeListener(mssv);
    }
    private void EventChangeListener(String mssv) {
        db.collection("User")
                .document(mssv)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                // Lấy thông tin từ Firestore và chuyển thành đối tượng User
                                User user = document.toObject(User.class);

                                myClass MyClass = document.toObject(myClass.class);

                                if (user != null && user.getEnrolledClass() != null && !user.getEnrolledClass().isEmpty()) {
                                    // Duyệt qua danh sách ID lớp học và truy vấn thông tin từ bảng Class
                                    for (String classId : user.getEnrolledClass()) {
//                                        for(String date)
                                        // Log ID lớp học
                                        db.collection("Class")
                                                .document(classId)
                                                .get()
                                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                        if (task.isSuccessful()) {
                                                            DocumentSnapshot classDocument = task.getResult();
                                                            if (classDocument.exists()) {
                                                                // Lấy thông tin lớp học từ Firestore và chuyển thành đối tượng Class
                                                                myClass enrolledClass = classDocument.toObject(myClass.class);
                                                            }
                                                        }
                                                    }
                                                });
                                    }
                                }

                            }
                        }
                    }
                });
    }













}

