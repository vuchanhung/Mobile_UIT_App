package com.example.bigproject.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigproject.Adapter.TrendsAdapter;
import com.example.bigproject.Domain.TrendsDomain;
import com.example.bigproject.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterTrendsList;
    private RecyclerView recyclerViewTrends;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_main);

        Intent intent = new Intent(this, logo.class);
        startActivity(intent);

        initRecycleView();
    }

    private void initRecycleView() {
        ArrayList<TrendsDomain> items=new ArrayList<>();
//        picAddress đang là 1 hình, nếu nhiều hình thì tạo folder và picAddress là tên folder
        items.add(new TrendsDomain("CLB TIẾNG ANH UIT - OEC","Trau dồi kĩ năng Tiếng Anh cùng CLB Tiếng Anh tại UIT","trend_club"));
        items.add(new TrendsDomain("CLB TIẾNG ANH UIT - OEC","Trau dồi kĩ năng Tiếng Anh cùng CLB Tiếng Anh tại UIT","trend_club"));
        items.add(new TrendsDomain("CLB TIẾNG ANH UIT - OEC","Trau dồi kĩ năng Tiếng Anh cùng CLB Tiếng Anh tại UIT","trend_club"));

        recyclerViewTrends=findViewById(R.id.view1);
        recyclerViewTrends.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        adapterTrendsList= new TrendsAdapter(items);
        recyclerViewTrends.setAdapter(adapterTrendsList);
    }
}


