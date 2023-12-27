package com.example.bigproject.Activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigproject.Adapter.SeminarAdapter;
import com.example.bigproject.Adapter.TrendsAdapter;
import com.example.bigproject.Domain.SeminarDomain;
import com.example.bigproject.Domain.TrendsDomain;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterTrendsList;
    private RecyclerView recyclerViewTrends;

    private RecyclerView.Adapter adapterSeminarList;
    private RecyclerView recyclerViewSeminar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.news_search);
        Log.d(TAG, "onCreate đã được gọi");
        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String mssv = preferences.getString("mssv", "");

        if(mssv.isEmpty()){
            Intent intent = new Intent(this, logo.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        }


//        initRecycleView();
//        initRecycleView2();
    }

    private void initRecycleView() {
        ArrayList<TrendsDomain> items=new ArrayList<>();
//        picAddress đang là 1 hình, nếu nhiều hình thì tạo folder và picAddress là tên folder
        items.add(new TrendsDomain("CLB TIẾNG ANH UIT - OEC","Trau dồi kĩ năng Tiếng Anh cùng CLB Tiếng Anh tại UIT","trend_club"));
        items.add(new TrendsDomain("CLB TIẾNG ANH UIT - OEC","Trau dồi kĩ năng Tiếng Anh cùng CLB Tiếng Anh tại UIT","trend_club"));
        items.add(new TrendsDomain("CLB TIẾNG ANH UIT - OEC","Trau dồi kĩ năng Tiếng Anh cùng CLB Tiếng Anh tại UIT","trend_club"));

//        recyclerViewTrends=findViewById(R.id.club_list_detail);
        recyclerViewTrends.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        adapterTrendsList= new TrendsAdapter(items);
        recyclerViewTrends.setAdapter(adapterTrendsList);
    }

    private void initRecycleView2() {
        ArrayList<SeminarDomain> items=new ArrayList<>();
//        picAddress đang là 1 hình, nếu nhiều hình thì tạo folder và picAddress là tên folder
        items.add(new SeminarDomain("UIT SEMINAR: Tháng 10 này có gì?","By UIT","seminar"));
        items.add(new SeminarDomain("Sky Mavis Uni Tour - AXIE GAME JAM 2023","By UIT","seminar2"));

//        recyclerViewSeminar=findViewById(R.id.seminar_list_detail);
        try {
            recyclerViewTrends.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        adapterSeminarList= new SeminarAdapter(items);
        recyclerViewSeminar.setAdapter(adapterSeminarList);
    }
}


