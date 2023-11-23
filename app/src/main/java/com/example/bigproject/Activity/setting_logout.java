package com.example.bigproject.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bigproject.R;

public class setting_logout extends AppCompatActivity {
    private Button dangXuatButton;
    private Button huyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_logout);

        // Lấy thông tin người dùng từ SharedPreferences
        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String mssv = preferences.getString("mssv", "");

        dangXuatButton = findViewById(R.id.cancelButton);
        huyButton = findViewById(R.id.confirmButton);

        dangXuatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xóa thông tin người dùng từ SharedPreferences
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();  // Xóa tất cả dữ liệu
                editor.apply();

                // Chuyển về màn hình đăng nhập
                Intent intent = new Intent(setting_logout.this, com.example.bigproject.Activity.login.class);
                startActivity(intent);
                finish();
            }
        });
        huyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển về màn hình đăng nhập
                Intent intent = new Intent(setting_logout.this, com.example.bigproject.Activity.Setting_Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
