package com.example.bigproject.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigproject.Adapter.SubjectDetailAdapter;
import com.example.bigproject.R;

import java.util.ArrayList;

public class SubjectDetail extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SubjectDetailAdapter adapter;
    private ArrayList<String> dataList; // Thay ArrayList<String> bằng kiểu dữ liệu của bạn

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subject_detail);

        dataList = new ArrayList<>(); // Khởi tạo dữ liệu của bạn ở đây

        recyclerView = findViewById(R.id.practice_recyclerview); // Thay yourRecyclerViewId bằng ID của RecyclerView trong subject_detail.xml
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new SubjectDetailAdapter(dataList);
        recyclerView.setAdapter(adapter);
    }
}
