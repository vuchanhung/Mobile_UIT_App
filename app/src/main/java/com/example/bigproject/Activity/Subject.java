package com.example.bigproject.Activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigproject.Adapter.SubjectAdapter;
import com.example.bigproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Subject extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SubjectAdapter adapter;
    ArrayList<String> classArrayList;  // Change the type to ArrayList<String>

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subject_main);

        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String mssv = preferences.getString("mssv", "");

        recyclerView = findViewById(R.id.recyclerView);
        Button filterButton = findViewById(R.id.filterButton);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        db = FirebaseFirestore.getInstance();
        classArrayList = new ArrayList<>();  // Change the type to ArrayList<String>
        adapter = new SubjectAdapter(Subject.this, classArrayList);

        recyclerView.setAdapter(adapter);  // Set the adapter to the RecyclerView

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
                                // Get the enrolledClass array from the User document
                                Object enrolledClassesObj = document.get("enrolledClass");

                                if (enrolledClassesObj instanceof ArrayList) {
                                    ArrayList<?> enrolledClassIds = (ArrayList<?>) enrolledClassesObj;

                                    // Log the contents of enrolledClass
                                    Log.d(TAG, "Enrolled Class Ids: " + enrolledClassIds.toString());

                                    // Assuming enrolledClassIds contains strings (ids)
                                    for (Object enrolledClassId : enrolledClassIds) {
                                        if (enrolledClassId instanceof String) {
                                            String classId = (String) enrolledClassId;

                                            // Query the "Class" document using the classId
                                            db.collection("Class")
                                                    .document(classId)
                                                    .get()
                                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                            if (task.isSuccessful()) {
                                                                DocumentSnapshot classDocument = task.getResult();
                                                                if (classDocument.exists()) {
                                                                    // Get the 'name' field from the Class document
                                                                    String className = classDocument.getString("name");

                                                                    // Log or process className as needed
                                                                    Log.d(TAG, "Class Name: " + className);

                                                                    // Add className to the adapter's data
                                                                    classArrayList.add(className);

                                                                    // Notify the adapter that the data set has changed
                                                                    adapter.notifyDataSetChanged();
                                                                }
                                                            } else {
                                                                // Handle errors in querying the Class document
                                                                Log.d(TAG, "Error getting Class document: ", task.getException());
                                                            }
                                                        }
                                                    });
                                        }
                                    }
                                }
                            }
                        } else {
                            // Handle errors here
                            Log.d(TAG, "Error getting User document: ", task.getException());
                        }
                    }
                });
    }

}

