package com.example.bigproject.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bigproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    private EditText mssvEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        mssvEditText = findViewById(R.id.mssv);
        passwordEditText = findViewById(R.id.mat_khau);
        loginButton = findViewById(R.id.button);
        userRef = FirebaseDatabase.getInstance().getReference().child("User");

        loginButton.setOnClickListener(v -> {
            String mssv = mssvEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (TextUtils.isEmpty(mssv) || TextUtils.isEmpty(password)) {
                Toast.makeText(login.this, "Vui lòng nhập đầy đủ MSSV và mật khẩu", Toast.LENGTH_SHORT).show();
                return;
            }

            // Log mssv và password
            Log.d("Login", "mssv: " + mssv + ", password: " + password);

            // Thực hiện truy vấn để lấy dữ liệu dựa trên mssv
            userRef.orderByChild("MSSV").equalTo(mssv).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // Dữ liệu tồn tại, có thể lấy thông tin người dùng từ dataSnapshot
                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                            String dbPassword = userSnapshot.child("password").getValue(String.class);

                            // Kiểm tra mật khẩu
                            if (password.equals(dbPassword)) {
                                // Mật khẩu đúng, đăng nhập thành công
                                Toast.makeText(login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                // Mật khẩu không đúng
                                Toast.makeText(login.this, "Mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        // Không tìm thấy dữ liệu, có thể là mssv không tồn tại
                        Toast.makeText(login.this, "MSSV không tồn tại", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Xử lý khi có lỗi xảy ra trong quá trình đọc dữ liệu
                    Toast.makeText(login.this, "Đã xảy ra lỗi", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
