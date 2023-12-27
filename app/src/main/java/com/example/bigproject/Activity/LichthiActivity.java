package com.example.bigproject.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigproject.Adapter.LichthiAdapter;
import com.example.bigproject.Model.Lichthi;
import com.example.bigproject.Model.User;
import com.example.bigproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class LichthiActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LichthiAdapter adapter;
    private List<Lichthi> lichthiList;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lichthi);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        db = FirebaseFirestore.getInstance();
        lichthiList = new ArrayList<>(); // Khởi tạo danh sách lịch thi

        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String mssv = preferences.getString("mssv", "");
        // Tìm ConstraintLayout theo ID
        ConstraintLayout backBtnLayout = findViewById(R.id.back_btn);

        // Đặt sự kiện onClick cho ConstraintLayout
        backBtnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LichthiActivity.this, com.example.bigproject.Activity.Home.class);
                startActivity(intent);
                finish();
            }
        });
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
                                        db.collection("Lichthi")
                                                .document(classId)
                                                .get()
                                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                        if (task.isSuccessful()) {
                                                            DocumentSnapshot classDocument = task.getResult();
                                                            if (classDocument.exists()) {
                                                                // Lấy thông tin từ Firestore và chuyển thành đối tượng Lichthi
                                                                Lichthi lichthi = classDocument.toObject(Lichthi.class);

                                                                if (lichthi != null) {
                                                                    lichthiList.add(lichthi); // Thêm lịch thi vào danh sách
                                                                    // Cập nhật dữ liệu cho adapter
                                                                    if (adapter == null) {
                                                                        adapter = new LichthiAdapter(lichthiList);
                                                                        recyclerView.setAdapter(adapter);
                                                                    } else {
                                                                        adapter.notifyDataSetChanged();
                                                                    }
                                                                }
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