package com.example.bigproject;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class logo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_main);

        // Tạo một Handler để chuyển sang LoginActivity sau 3 giây
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Tạo Intent để chuyển sang LoginActivity
                Intent intent = new Intent(logo.this, login.class);
                startActivity(intent);
                finish(); // Đóng màn hình LogoActivity
            }
        }, 3000); // 3000 milliseconds (3 giây)
    }
}

