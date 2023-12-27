package com.example.bigproject.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.bigproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailNewsActivity extends AppCompatActivity {
    FirebaseFirestore db;
    TextView titleTextView;
    TextView contentTextView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tintuc);

        // Nhận dữ liệu từ Intent
        String idNews = getIntent().getStringExtra("news");
        Log.d("DetailNewsActivity", "ID News: " + idNews);

        titleTextView = findViewById(R.id.titletintuc);
        contentTextView = findViewById(R.id.contenttintuc);
        imageView = findViewById(R.id.imagetintuc);

        db = FirebaseFirestore.getInstance();

        db.collection("News")
                .document(idNews)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                String title = document.getString("title");
                                String content = document.getString("content");
                                String imageUrl = document.getString("imageUrl");

                                titleTextView.setText(title);
                                contentTextView.setText(content);

                                // Load image using Glide
                                Glide.with(DetailNewsActivity.this)
                                        .load(imageUrl)
//                                        .placeholder(R.drawable.placeholder)  // Placeholder image while loading
//                                        .error(R.drawable.error_placeholder)  // Error image if loading fails
                                        .into(imageView);
                            }
                        }
                    }
                });
    }
}
