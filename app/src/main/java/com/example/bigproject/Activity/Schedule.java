package com.example.bigproject.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigproject.Adapter.ScheduleAdapter;
import com.example.bigproject.Domain.ScheduleDomain;
import com.example.bigproject.R;

import java.util.ArrayList;

public class Schedule extends AppCompatActivity {

    private ArrayList<ScheduleDomain> scheduleItems;
    private RecyclerView recyclerView;
    private ScheduleAdapter scheduleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_main);

        // Khởi tạo dữ liệu mẫu
        scheduleItems = new ArrayList<>();
        scheduleItems.add(new ScheduleDomain("ECxxx.xxx.TMCL", "Thứ 4, tiết 6-9, P.B310", "Số Buổi Diễn Ra", "Trần Văn Hải Triều"));

        recyclerView = findViewById(R.id.schedule_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        scheduleAdapter = new ScheduleAdapter(this, scheduleItems);
        recyclerView.setAdapter(scheduleAdapter);
    }
}

