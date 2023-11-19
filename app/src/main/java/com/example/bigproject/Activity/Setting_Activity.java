package com.example.bigproject.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bigproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Setting_Activity extends AppCompatActivity {
    private TextView userNameTextView;
    private TextView mssvTextView;
    private TextView mailTextView;

    private TextView birthTextView;
    private TextView nganhTextView;

    private TextView classTextView;

    private TextView phoneTextView;

    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_main);
        // Lấy thông tin người dùng từ SharedPreferences
        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String mssv = preferences.getString("mssv", ""); // Lấy giá trị MSSV
        userNameTextView = findViewById(R.id.textView2); // Đảm bảo ID phản ánh TextView trong layout của bạn
        mssvTextView = findViewById(R.id.textView3); // Đảm bảo ID phản ánh TextView trong layout của bạn
        mailTextView = findViewById(R.id.mail_text);
        birthTextView = findViewById(R.id.birthday_text);
        nganhTextView = findViewById(R.id.domain_text);
        classTextView = findViewById(R.id.class_text);
        phoneTextView = findViewById(R.id.phone_text);
        db = FirebaseFirestore.getInstance();
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
                                        // Hiển thị thông tin trong TextView
                                userNameTextView.setText(userName);
                                mssvTextView.setText(mssv);
                                mailTextView.setText(mail);
                                birthTextView.setText(birthday);
                                nganhTextView.setText(nganh);
                                classTextView.setText(lop);
                                phoneTextView.setText(phone_number);
                            } else {
                                // Không tìm thấy dữ liệu, xử lý theo ý bạn
                            }
                        } else {
                            // Xử lý khi có lỗi xảy ra trong quá trình đọc dữ liệu
                        }
                    }
                });
    }
}
