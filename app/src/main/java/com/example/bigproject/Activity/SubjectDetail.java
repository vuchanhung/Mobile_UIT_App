package com.example.bigproject.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bigproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SubjectDetail extends AppCompatActivity {

// Thay ArrayList<String> bằng kiểu dữ liệu của bạn
    private FirebaseFirestore db;
    private TextView textViewQT, textViewGK, textViewCK, textViewTH, textViewTB;
    private CollectionReference messagesCollection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subject_detail);

        db = FirebaseFirestore.getInstance();

        // Ánh xạ các TextView
        textViewQT = findViewById(R.id.textViewQT);
        textViewGK = findViewById(R.id.textViewGK);
        textViewCK = findViewById(R.id.textViewCK);
        textViewTH = findViewById(R.id.textViewTH);
        textViewTB = findViewById(R.id.textViewTB);

        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String mssv = preferences.getString("mssv", "");
        String idGroupchat = getIntent().getStringExtra("idGroupchat");
        messagesCollection = db.collection("Score").document(idGroupchat).collection("Detail");
        checkExist(idGroupchat);
        hienThiDiem(mssv);
        LinearLayout bottomLayout = findViewById(R.id.bottomLayout);
        BottomNavigationView bottomNavigationView = bottomLayout.findViewById(R.id.bottomnav);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nut_home) {
                // Xử lý khi nhấn vào Trang chủ
                if (!isMainActivity()) {
                    openMainActivity();
                    return true;
                }
            } else if (itemId == R.id.TKB) {
                openmenu();
                // Xử lý khi nhấn vào Thực đơn
            } else if (itemId == R.id.qr_button) {
                // Xử lý khi nhấn vào Giỏ hàng
                opengiohang();
            } else if (itemId == R.id.subject_button) {
                // Xử lý khi nhấn vào Giỏ hàng
                opendonhang();
            } else if (itemId == R.id.individual_button) {
                // Xử lý khi nhấn vào Khác
                openbagach();
            }
            return true;
        });

    }
    private boolean isMainActivity() {
        // Kiểm tra xem đang ở MainActivity hay không
        return getClass().getSimpleName().equals(Home.class.getSimpleName());
    }
    private void openMainActivity() {
        // Khởi tạo lại MainActivity
        Intent intent = new Intent(this, Home.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
    private void openmenu() {
        Intent intent = new Intent(this, Schedule.class);
        startActivity(intent);
    }
    private void opengiohang() {
        Intent intent = new Intent(this, Diemdanh.class);
        startActivity(intent);
    }
    private void opendonhang() {
        Intent intent = new Intent(this, Subject.class);
        startActivity(intent);
    }

    private void openbagach() {
        Intent intent = new Intent(this, Setting_Activity.class);
        startActivity(intent);
    }
    private void hienThiDiem(String mssv) {
        // Tham chiếu đến document của sinh viên trong subcollection "detail"
        DocumentReference sinhVienDocRef = messagesCollection.document(mssv);
        Log.d("Firestore", "Document id: " + mssv);
        // Đọc dữ liệu từ Firestore
        sinhVienDocRef.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot sinhVienSnapshot = task.getResult();
                            if (sinhVienSnapshot.exists()) {
                                // Lấy dữ liệu từ document của sinh viên
                                Long diemQT = sinhVienSnapshot.getLong("QT");
                                Long diemGK = sinhVienSnapshot.getLong("GK");
                                Long diemCK = sinhVienSnapshot.getLong("CK");
                                Long diemTB = sinhVienSnapshot.getLong("TB");
                                Long diemTH = sinhVienSnapshot.getLong("TH");


                                // Hiển thị dữ liệu lên TextView
                                textViewQT.setText(String.valueOf(diemQT != null ? diemQT : "N/A"));
                                textViewGK.setText(String.valueOf(diemGK != null ? diemGK : "N/A"));
                                textViewCK.setText(String.valueOf(diemCK != null ? diemCK : "N/A"));
                                textViewTH.setText(String.valueOf(diemTH != null ? diemTH : "N/A"));
                                textViewTB.setText(String.valueOf(diemTB != null ? diemTB : "N/A"));
                            } else {
                                Toast.makeText(SubjectDetail.this, "Không tìm thấy thông tin điểm của sinh viên", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Xử lý khi đọc dữ liệu thất bại
                            Toast.makeText(SubjectDetail.this, "Lỗi khi đọc dữ liệu từ Firestore", Toast.LENGTH_SHORT).show();
                            Log.e("Firestore", "Lỗi khi đọc dữ liệu từ Firestore", task.getException());
                        }
                    }
                });
    }

    private void checkExist(String idGroupchat) {
        db.collection("Score").document(idGroupchat).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document != null && document.exists()) {
                                // Kiểm tra xem subcollection "messages" đã tồn tại chưa
                                if (!document.contains("detail")) {
                                    // Nếu chưa tồn tại, tạo mới subcollection "messages"
                                    createDetailCollection(idGroupchat);
                                }
                            }
                        } else {
                            Log.e("ChatActivity", "Error checking messages collection: " + task.getException());
                        }
                    }
                });
    }
    private void createDetailCollection(String idGroupchat) {
        Map<String, Object> data = new HashMap<>();
        data.put("detail", "");  // Thêm một trường tạm để tạo subcollection

        db.collection("Score").document(idGroupchat).set(data)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("SubjectDetail", "Successfully");
                        } else {
                            Log.e("SubjectDetail", "Error creating collection: " + task.getException());
                        }
                    }
                });
    }
}
