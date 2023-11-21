package com.example.bigproject.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigproject.Adapter.SemesterAdapter;
import com.example.bigproject.Domain.SemesterDomain;
import com.example.bigproject.R;

import java.util.ArrayList;
import java.util.List;

public class Semester extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SemesterAdapter semesterAdapter;
    private List<SemesterDomain> semesterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.semester_result_main);

        // Khởi tạo danh sách mẫu SemesterDomains
        createSampleData();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        semesterAdapter = new SemesterAdapter(this, semesterList);
        recyclerView.setAdapter(semesterAdapter);

        // Hiển thị thông tin chung về học kỳ, lớp học, điểm trung bình, vv...
        displayGeneralInformation();
    }

    // Tạo dữ liệu mẫu
    private void createSampleData() {
        semesterList = new ArrayList<>();
        // Thêm các đối tượng SemesterDomain vào danh sách
        // Ví dụ:
        semesterList.add(new SemesterDomain("ABC123", "Class A", 4, 75.5f, 80.0f, 85.0f, 90.0f, 82.5f));
        semesterList.add(new SemesterDomain("DEF456", "Class B", 3, 80.0f, 85.0f, 90.0f, 95.0f, 88.75f));
        // Thêm các đối tượng khác nếu cần thiết
    }

    // Hiển thị thông tin chung
    private void displayGeneralInformation() {
        TextView classNameTextView = findViewById(R.id.textView11);
        // Set thông tin lớp học, điểm trung bình, vv...
        // Ví dụ:
        classNameTextView.setText("PHAN PHI THUYỀN");

        // Tương tự, bạn có thể set thông tin khác như điểm trung bình, tín chỉ tích lũy, vv...
    }
}
}
