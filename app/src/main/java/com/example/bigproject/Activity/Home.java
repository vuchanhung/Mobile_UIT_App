package com.example.bigproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bigproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);
        LinearLayout bottomLayout = findViewById(R.id.bottomLayout);
        BottomNavigationView bottomNavigationView = bottomLayout.findViewById(R.id.bottomnav);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nut_home) {
                // Xử lý khi nhấn vào Trang chủ
                openMainActivity();

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
    //    private void openMainActivity() {
//        Intent intent = new Intent(this, Home.class);
//        startActivity(intent);
//    }
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
}

