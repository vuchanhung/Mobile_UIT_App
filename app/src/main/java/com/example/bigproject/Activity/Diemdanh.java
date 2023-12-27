package com.example.bigproject.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bigproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

public class Diemdanh extends AppCompatActivity {
//    private EditText editTextMaLop;
    private Spinner spinnerMaLop;
    private Button buttonGui;

    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diemdanh);

//        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
//        String mssv = preferences.getString("mssv", "");

        db = FirebaseFirestore.getInstance();

//        editTextMaLop = findViewById(R.id.editTextMaLop);
        spinnerMaLop = findViewById(R.id.spinnerMaLop);
        buttonGui = findViewById(R.id.buttonGui);
        // Lấy mã sinh viên từ SharedPreferences
        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String mssv = preferences.getString("mssv", "");
        loadEnrolledClasses(mssv);
        buttonGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xử lý khi nút được nhấn
                guiThongTin();
            }
        });
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setSelectedItemId(R.id.nut_qr);



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
                } else if(item.getItemId() == R.id.nut_subject)
                {
                    startActivity(new Intent(getApplicationContext(), Subject.class));
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
    private void loadEnrolledClasses(String mssv) {
        // Truy cập vào bảng User để lấy danh sách mã lớp
        db.collection("User").document(mssv).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                // Nếu document tồn tại, lấy danh sách mã lớp từ enrolledClass
                                ArrayList<String> enrolledClasses = (ArrayList<String>) document.get("enrolledClass");

                                // Hiển thị danh sách mã lớp trong Spinner
                                ArrayAdapter<String> adapter = new ArrayAdapter<>(Diemdanh.this, android.R.layout.simple_spinner_item, enrolledClasses);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinnerMaLop.setAdapter(adapter);

                                // Thiết lập sự kiện khi chọn mã lớp từ Spinner
                                spinnerMaLop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                                        String selectedMaLop = adapterView.getItemAtPosition(position).toString();
                                        // Log ra mã lớp đã chọn (có thể thay bằng việc hiển thị thông tin khác)
                                        Toast.makeText(Diemdanh.this, "Mã lớp đã chọn: " + selectedMaLop, Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                        // Nếu không có gì được chọn
                                    }
                                });
                            } else {
                                Toast.makeText(Diemdanh.this, "Không tìm thấy thông tin sinh viên", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Diemdanh.this, "Lỗi khi truy cập Firebase", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void guiThongTin() {
        // Lấy mã lớp từ EditText
        String maLop = spinnerMaLop.getSelectedItem().toString();


        // Kiểm tra xem mã lớp có rỗng hay không
        if (maLop.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập mã lớp", Toast.LENGTH_SHORT).show();
        } else {
            // Truy cập vào bảng Diemdanh
            Log.d("Diemdanh", "Mã lớp: " + maLop);
            db.collection("Diemdanh").document(maLop).get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    // Nếu document tồn tại, tiến hành lấy dữ liệu
                                    SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
                                    String mssv = preferences.getString("mssv", "");
                                    layDuLieuHocSinh(mssv, maLop);
                                } else {
                                    Toast.makeText(Diemdanh.this, "Không tìm thấy mã lớp", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(Diemdanh.this, "Lỗi khi truy cập Firebase", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void layDuLieuHocSinh(final String mssv, final String maLop) {
        // Truy cập vào bảng Diemdanh
        db.collection("Diemdanh").document(maLop).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                // Nếu document tồn tại, lấy dữ liệu mảng hocsinh
                                ArrayList<Map<String, Object>> hocsinh = (ArrayList<Map<String, Object>>) document.get("hocsinh");

                                // Xử lý dữ liệu học sinh tại đây
                                for (Map<String, Object> hocSinh : hocsinh) {
                                    String mssvHocSinh = (String) hocSinh.get("mssv");
                                    if (mssvHocSinh.equals(mssv)) {
                                        // Tìm thấy học sinh có mssv trùng khớp
                                        int solanDiemDanh = Integer.parseInt(String.valueOf(hocSinh.get("solandiemdanh")));
                                        solanDiemDanh++; // Tăng giá trị lên 1
                                        hocSinh.put("solandiemdanh", solanDiemDanh); // Cập nhật giá trị trong Map

                                        // Cập nhật dữ liệu trong Firebase Firestore
                                        updateDiemDanhData(maLop, hocsinh);
                                    }
                                }
                            } else {
                                Toast.makeText(Diemdanh.this, "Không tìm thấy mã lớp", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Diemdanh.this, "Lỗi khi truy cập Firebase", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updateDiemDanhData(String maLop, ArrayList<Map<String, Object>> hocsinh) {
        // Cập nhật dữ liệu trong Firebase Firestore
        db.collection("Diemdanh").document(maLop)
                .update("hocsinh", hocsinh)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Diemdanh.this, "Đã cập nhật số lần điểm danh", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Diemdanh.this, "Lỗi khi cập nhật số lần điểm danh", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
