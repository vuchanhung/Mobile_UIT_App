    package com.example.bigproject.Activity;

    import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.MenuItem;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import com.example.bigproject.Adapter.SubjectAdapter;
    import com.example.bigproject.Model.User;
    import com.example.bigproject.Model.myClass;
    import com.example.bigproject.R;
    import com.google.android.gms.tasks.OnCompleteListener;
    import com.google.android.gms.tasks.OnFailureListener;
    import com.google.android.gms.tasks.OnSuccessListener;
    import com.google.android.gms.tasks.Task;
    import com.google.android.material.bottomnavigation.BottomNavigationView;
    import com.google.android.material.navigation.NavigationBarView;
    import com.google.firebase.firestore.DocumentSnapshot;
    import com.google.firebase.firestore.FirebaseFirestore;

    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.Comparator;

    public class Subject extends AppCompatActivity {

        private RecyclerView recyclerView;
        private SubjectAdapter adapter;
        ArrayList<myClass> classArrayList;  // Change the type to ArrayList<Class>

        FirebaseFirestore db;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.subject_main);

            SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
            String mssv = preferences.getString("mssv", "");
            recyclerView = findViewById(R.id.recyclerView);

//            Button filterButton = findViewById(R.id.filterButton);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);

            db = FirebaseFirestore.getInstance();
            classArrayList = new ArrayList<>();  // Change the type to ArrayList<Class>
            adapter = new SubjectAdapter(Subject.this, classArrayList);

            recyclerView.setAdapter(adapter);  // Set the adapter to the RecyclerView

            EventChangeListener(mssv);
//            bottom navigation bar
            BottomNavigationView bottomNavigationView;
            bottomNavigationView = findViewById(R.id.bottomnav);
            bottomNavigationView.setSelectedItemId(R.id.nut_subject);

            bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if(item.getItemId()==R.id.nut_TKB){
                        startActivity(new Intent(getApplicationContext(), Schedule.class));
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

                                    if (user != null && user.getEnrolledClass() != null && !user.getEnrolledClass().isEmpty()) {
                                        // Duyệt qua danh sách ID lớp học và truy vấn thông tin từ bảng Class
                                        for (String classId : user.getEnrolledClass()) {
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
                                                                    // Kiểm tra xem trường 'chat' có giá trị không
                                                                    if (enrolledClass != null && enrolledClass.getChat() == null) {
                                                                        // Nếu không có giá trị, thêm giá trị bằng với classID
                                                                        db.collection("Class")
                                                                                .document(classId)
                                                                                .update("chat", classId)
                                                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                    @Override
                                                                                    public void onSuccess(Void aVoid) {
                                                                                        Log.d(TAG, "Chat value updated successfully.");
                                                                                        // Thêm vào danh sách và cập nhật RecyclerView
                                                                                        classArrayList.add(enrolledClass);
                                                                                        Collections.sort(classArrayList, new Comparator<myClass>() {
                                                                                            @Override
                                                                                            public int compare(myClass class1, myClass class2) {
                                                                                                // So sánh năm giảm dần
                                                                                                int yearCompare = Integer.compare(class2.getYear(), class1.getYear());

                                                                                                // Nếu năm giống nhau, so sánh học kì (semester) theo thứ tự
                                                                                                if (yearCompare == 0) {
                                                                                                    return class2.getSemester().compareTo(class1.getSemester());
                                                                                                }

                                                                                                return yearCompare;
                                                                                            }
                                                                                        });
                                                                                        adapter.notifyDataSetChanged();
                                                                                    }
                                                                                })
                                                                                .addOnFailureListener(new OnFailureListener() {
                                                                                    @Override
                                                                                    public void onFailure(@NonNull Exception e) {
                                                                                        Log.e(TAG, "Error updating chat value.", e);
                                                                                    }
                                                                                });
                                                                    } else {
                                                                        // Trường 'chat' đã có giá trị, không cần thêm
                                                                        // Thêm vào danh sách và cập nhật RecyclerView
                                                                        enrolledClass.toString();
                                                                        classArrayList.add(enrolledClass);
                                                                        Collections.sort(classArrayList, new Comparator<myClass>() {
                                                                            @Override
                                                                            public int compare(myClass class1, myClass class2) {
                                                                                // So sánh năm giảm dần
                                                                                int yearCompare = Integer.compare(class2.getYear(), class1.getYear());

                                                                                // Nếu năm giống nhau, so sánh học kì (semester) theo thứ tự
                                                                                if (yearCompare == 0) {
                                                                                    return class2.getSemester().compareTo(class1.getSemester());
                                                                                }

                                                                                return yearCompare;
                                                                            }
                                                                        });
                                                                        adapter.notifyDataSetChanged();
                                                                    }
                                                                }
                                                            } else {
                                                                // Xử lý lỗi khi truy vấn thông tin lớp học
                                                                Log.d(TAG, "Error getting Class document: ", task.getException());
                                                            }
                                                        }
                                                    });
                                        }
                                    }
                                }
                            } else {
                                // Xử lý lỗi khi truy vấn thông tin sinh viên
                                Log.d(TAG, "Error getting User document: ", task.getException());
                            }
                        }
                    });
        }



    }

