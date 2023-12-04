package com.example.bigproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

        //Navigation
        LinearLayout profileBtn;

        profileBtn = findViewById(R.id.profileBtn);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Semester.this, com.example.bigproject.Activity.Setting_Activity.class);
                startActivity(intent);
                finish();
            }

        });

        LinearLayout courseBtn;

        courseBtn = findViewById(R.id.courseBtn);
        courseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Semester.this, com.example.bigproject.Activity.Subject.class);
                startActivity(intent);
                finish();
            }

        });
        //Nút back
        ImageView backButton = findViewById(R.id.imageView7);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xử lý sự kiện khi ImageView được nhấn
                back();
            }
        });
    }
    public void back() {
        // Xử lý khi nút back được nhấn
        // Ví dụ: quay lại trang trước đó
        startActivity(new Intent(Semester.this, Setting_Activity.class));
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

