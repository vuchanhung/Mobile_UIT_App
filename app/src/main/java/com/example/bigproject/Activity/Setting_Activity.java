package com.example.bigproject.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bigproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class Setting_Activity extends AppCompatActivity {
    private TextView userNameTextView;
    private TextView mssvTextView;
    private TextView mailTextView;
    private TextView birthTextView;
    private TextView nganhTextView;
    private TextView classTextView;
    private TextView phoneTextView;
    private TextView addressTexView;
    private Button dangXuatButton;
    private FirebaseFirestore db;
    private ImageView imageView;
//    BottomNavigationView bottomNavigationView;
    private ImageButton semester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_main);

        // Lấy thông tin người dùng từ SharedPreferences
        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String mssv = preferences.getString("mssv", "");
        db = FirebaseFirestore.getInstance();
        initializeViews();
        fetchDataFromFirestore(mssv);
        // Kiểm tra xem có MSSV trong SharedPreferences hay không
//        if (!TextUtils.isEmpty(mssv)) {
//            // Nếu có, hiển thị thông tin người dùng
//            initializeViews();
//            fetchDataFromFirestore(mssv);
//        } else {
//            // Nếu không có, chuyển về màn hình đăng nhập
//            Toast.makeText(this, "Vui lòng đăng nhập", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(Setting_Activity.this, com.example.bigproject.Activity.login.class);
//            startActivity(intent);
//            finish();
//        }

        dangXuatButton = findViewById(R.id.dangnhap_btn);
        dangXuatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Setting_Activity.this, com.example.bigproject.Activity.setting_logout.class);
                startActivity(intent);
                finish();
            }
        });

        semester = findViewById(R.id.imageButton2);
        semester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Setting_Activity.this, com.example.bigproject.Activity.Semester.class);
                startActivity(intent);
                finish();
            }

        });


        //Navigation
        LinearLayout profileBtn;

        profileBtn = findViewById(R.id.profileBtn);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Setting_Activity.this, com.example.bigproject.Activity.Setting_Activity.class);
                startActivity(intent);
                finish();
            }

        });

        LinearLayout courseBtn;

        courseBtn = findViewById(R.id.courseBtn);
        courseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Setting_Activity.this, com.example.bigproject.Activity.Subject.class);
                startActivity(intent);
                finish();
            }

        });

        LinearLayout tkbBtn;

        tkbBtn = findViewById(R.id.tkbBtn);
        tkbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Setting_Activity.this, com.example.bigproject.Activity.Schedule.class);
                startActivity(intent);
                finish();
            }

        });

        LinearLayout diemdanhbtn;

        diemdanhbtn = findViewById(R.id.diemdanh);
        diemdanhbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Setting_Activity.this, com.example.bigproject.Activity.Diemdanh.class);
                startActivity(intent);
                finish();
            }

        });
//        LinearLayout bottomLayout = findViewById(R.id.bottomLayout);
//        BottomNavigationView bottomNavigationView = bottomLayout.findViewById(R.id.bottomnav);
//        bottomNavigationView.setOnItemSelectedListener(item -> {
//            int itemId = item.getItemId();
//            if (itemId == R.id.nut_home) {
//                // Xử lý khi nhấn vào Trang chủ
//                openMainActivity();
//
//                if (!isMainActivity()) {
//                    openMainActivity();
//                    return true;
//                }
//            } else if (itemId == R.id.TKB) {
//                openmenu();
//                // Xử lý khi nhấn vào Thực đơn
//            } else if (itemId == R.id.qr_button) {
//                // Xử lý khi nhấn vào Giỏ hàng
//                opengiohang();
//            } else if (itemId == R.id.subject_button) {
//                // Xử lý khi nhấn vào Giỏ hàng
//                opendonhang();
//            } else if (itemId == R.id.individual_button) {
//                // Xử lý khi nhấn vào Khác
//                openbagach();
//            }
//            return true;
//        });
    }
//    private boolean isMainActivity() {
//        // Kiểm tra xem đang ở MainActivity hay không
//        return getClass().getSimpleName().equals(Home.class.getSimpleName());
//    }
//    private void openMainActivity() {
//        // Khởi tạo lại MainActivity
//        Intent intent = new Intent(this, Home.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//        finish();
//    }
////    private void openMainActivity() {
////        Intent intent = new Intent(this, Home.class);
////        startActivity(intent);
////    }
//    private void openmenu() {
//        Intent intent = new Intent(this, Schedule.class);
//        startActivity(intent);
//    }
//    private void opengiohang() {
//        Intent intent = new Intent(this, Diemdanh.class);
//        startActivity(intent);
//    }
//    private void opendonhang() {
//        Intent intent = new Intent(this, Subject.class);
//        startActivity(intent);
//    }
//
//    private void openbagach() {
//        Intent intent = new Intent(this, Setting_Activity.class);
//        startActivity(intent);
//    }
    private void initializeViews() {
        userNameTextView = findViewById(R.id.textView2);
        mssvTextView = findViewById(R.id.textView3);
        mailTextView = findViewById(R.id.mail_text);
        birthTextView = findViewById(R.id.birthday_text);
        nganhTextView = findViewById(R.id.domain_text);
        classTextView = findViewById(R.id.class_text);
        phoneTextView = findViewById(R.id.phone_text);
        imageView = findViewById(R.id.imageView6);
        addressTexView = findViewById(R.id.location_text);
    }

    private void fetchDataFromFirestore(String mssv) {
        db.collection("User")
                .document(mssv)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                String userName = document.getString("name");
                                String mail = document.getString("email");
                                String birthday = document.getString("Birthday");
                                String nganh = document.getString("nganh");
                                String lop = document.getString("class");
                                String phone_number = document.getString("phone");
                                String imageUrl = document.getString("picture");
                                Picasso.get().load(imageUrl).transform(new CircleTransform()).into(imageView);
                                String address = document.getString("address");
                                // Hiển thị thông tin trong TextView
                                userNameTextView.setText(userName);
                                mssvTextView.setText(mssv);
                                mailTextView.setText(mail);
                                birthTextView.setText(birthday);
                                nganhTextView.setText(nganh);
                                classTextView.setText(lop);
                                phoneTextView.setText(phone_number);
                                addressTexView.setText(address);
                            } else {
                                // Không tìm thấy dữ liệu, xử lý theo ý bạn
                            }
                        } else {
                            Toast.makeText(Setting_Activity.this, "Lỗi khi truy cập Firebase", Toast.LENGTH_SHORT).show();
                            Log.e("FirestoreConnection", "Error connecting to Firestore", task.getException());
                        }
                    }
                });
    }
}
