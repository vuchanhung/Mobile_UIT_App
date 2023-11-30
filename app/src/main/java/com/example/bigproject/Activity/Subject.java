package com.example.bigproject.Activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigproject.Adapter.SubjectAdapter;
import com.example.bigproject.Domain.SubjectDomain;
import com.example.bigproject.R;

import java.util.ArrayList;
import java.util.List;

public class Subject extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SubjectAdapter adapter;
    private List<String> filterOptions = new ArrayList<>();

    private List<SubjectDomain> subjectList = new ArrayList<SubjectDomain>(); // Khởi tạo danh sách môn học
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subject_main);

        recyclerView = findViewById(R.id.recyclerView);
        Button filterButton = findViewById(R.id.filterButton);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add filter options
        filterOptions.add("Option 1");
        filterOptions.add("Option 2");
        filterOptions.add("Option 3");
        // ...

        subjectList.add(new SubjectDomain("Học kỳ 1 - 2023", "Phát triển ứng dụng trên thiết bị di động", "Phan Xuân Thiện"));

// ... (Thêm các môn học khác nếu cần)
        SubjectAdapter adapter = new SubjectAdapter(subjectList, this);

        // Initialize RecyclerView
        adapter = new SubjectAdapter(subjectList, this);
        recyclerView.setAdapter(adapter);

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFilterOptions(view);
            }
        });
    }

    public void showFilterOptions(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        for (String option : filterOptions) {
            popupMenu.getMenu().add(option);
        }
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                String selectedOption = menuItem.getTitle().toString();
                handleFilterSelection(selectedOption);
                return true;
            }
        });
        popupMenu.show();
    }

    private void handleFilterSelection(String selectedOption) {
        // Handle filtering based on the selected option
        // Update your RecyclerView data accordingly
    }

    // Your RecyclerView Adapter class (YourAdapter) should be defined here or imported.
    // Make sure to pass data to the adapter as needed.
}
