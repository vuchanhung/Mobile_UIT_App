package com.example.bigproject.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bigproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Home extends AppCompatActivity {
    Button xemthem_btn;
    Button clb_AV_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);

        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setSelectedItemId(R.id.nut_home);



        LinearLayout tintucButton = findViewById(R.id.tintuc_button);
        tintucButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, com.example.bigproject.Activity.NewsActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });

        LinearLayout lichthiButton = findViewById(R.id.lichthi_button);
        lichthiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, com.example.bigproject.Activity.LichthiActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });
        LinearLayout diem = findViewById(R.id.diem_btn);
        diem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Home.this, "Chức năng sẽ sớm được hoạt động !", Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayout hocphi = findViewById(R.id.hocphi_button);
        hocphi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Home.this, "Chức năng sẽ sớm được hoạt động !", Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayout dl = findViewById(R.id.deadline_btn);
        dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Home.this, "Chức năng sẽ sớm được hoạt động !", Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayout hp = findViewById(R.id.hocphi_button);
        hp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Home.this, "Chức năng sẽ sớm được hoạt động !", Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayout xephang = findViewById(R.id.xephang_button);
        xephang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Home.this, "Chức năng sẽ sớm được hoạt động !", Toast.LENGTH_SHORT).show();
            }
        });


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nut_TKB){
                    startActivity(new Intent(getApplicationContext(), Schedule.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                } else if(item.getItemId() == R.id.nut_qr)
                {
                    startActivity(new Intent(getApplicationContext(), Diemdanh.class));
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
//        button xem them
        xemthem_btn = findViewById(R.id.xemthem_btn);
        xemthem_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://student.uit.edu.vn/"));
                startActivity(intent);
            }
        });


        clb_AV_btn = findViewById(R.id.clbTA_btn);
        clb_AV_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.facebook.com/oeclub.uit"));
                startActivity(intent);
            }
        });

    }

}