package com.example.bigproject.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bigproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    private EditText mssvEditText;
    private EditText passwordEditText;
    private Button loginButton;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        mssvEditText = findViewById(R.id.mssv);
        passwordEditText = findViewById(R.id.mat_khau);
        loginButton = findViewById(R.id.button);
        db = FirebaseFirestore.getInstance();

        loginButton.setOnClickListener(v -> {
            String mssv = mssvEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (TextUtils.isEmpty(mssv) || TextUtils.isEmpty(password)) {
                Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ MSSV và mật khẩu", Toast.LENGTH_SHORT).show();
                return;
            }

            // Log mssv và password
            Log.d("Login", "mssv: " + mssv + ", password: " + password);

            // Thực hiện truy vấn để lấy dữ liệu dựa trên mssv
            db.collection("User")
                    .document(mssv)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    String dbPassword = document.getString("password");

                                    // Kiểm tra mật khẩu
                                    if (password.equals(dbPassword)) {
                                        // Mật khẩu đúng, đăng nhập thành công
                                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                    } else {
                                        // Mật khẩu không đúng
                                        Toast.makeText(LoginActivity.this, "Mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    // Không tìm thấy dữ liệu, có thể là mssv không tồn tại
                                    Toast.makeText(LoginActivity.this, "MSSV không tồn tại", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                // Xử lý khi có lỗi xảy ra trong quá trình đọc dữ liệu
                                Toast.makeText(LoginActivity.this, "Đã xảy ra lỗi", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
    }
}