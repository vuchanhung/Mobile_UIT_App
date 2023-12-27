package com.example.bigproject.Activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigproject.Adapter.newsAdapter;
import com.example.bigproject.Model.News;
import com.example.bigproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private newsAdapter adapter;
    ArrayList<News> newsList; // Change the type to ArrayList<Class>

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_general);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        db = FirebaseFirestore.getInstance();

        newsList = new ArrayList<>();
        adapter = new newsAdapter(NewsActivity.this, newsList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new newsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(News news) {
                // Xử lý chuyển trang chi tiết tin tức ở đây
                // Ví dụ: Chuyển sang một Activity chi tiết mới
                Intent intent = new Intent(NewsActivity.this, DetailNewsActivity.class);
                intent.putExtra("news", news.getId());  // Truyền dữ liệu tin tức sang trang chi tiết
                startActivity(intent);
            }
        });
        // Set the adapter to the RecyclerView
        db.collection("News")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "ok");

                            ArrayList<News> newList = new ArrayList<>();

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Convert Firestore document to your News object
                                News news = document.toObject(News.class);
                                news.toString();
                                // Log to check the data
                                Log.d(TAG, "Firestore Data - Title: " + news.getTitle() + ", Content: " + news.getContent() + ", ImageUrl: " + news.getImageUrl());

                                // Add the news to the list
                                newsList.add(news);
                                adapter.notifyDataSetChanged();
                            }
                        } else {
                            // Handle errors
                        }
                    }
                });


    }

}