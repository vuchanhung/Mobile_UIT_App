package com.example.bigproject.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

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
    private SubjectAdapter subjectAdapter;
    private List<SubjectDomain> subjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subject_main); // Replace with your XML layout file name

        recyclerView = findViewById(R.id.recyclerView);
        subjects = new ArrayList<>(); // Replace with your list of subjects

        // Initialize and set adapter for RecyclerView
        subjectAdapter = new SubjectAdapter(subjects, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(subjectAdapter);

        Spinner yearSpinner = findViewById(R.id.yearSpinner);
        Button filterButton = findViewById(R.id.filterButton);

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedYear = yearSpinner.getSelectedItem().toString();
                subjectAdapter.filterByYear(selectedYear);
            }
        });
    }
}
