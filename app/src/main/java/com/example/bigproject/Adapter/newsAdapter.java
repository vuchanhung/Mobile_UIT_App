package com.example.bigproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bigproject.Model.News;
import com.example.bigproject.R;

import java.util.ArrayList;

public class newsAdapter extends RecyclerView.Adapter<newsAdapter.ViewHolder> {
    private ArrayList<News> newsList;
    Context context;

    public newsAdapter(Context context, ArrayList<News> newsList){
        this.context = context;
        this.newsList = newsList;
    }
    public void updateData(ArrayList<News> newList) {
        newsList.clear();
        newsList.addAll(newList);
        notifyDataSetChanged();
    }
    // Constructor and methods for updating data

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView16);
            titleTextView = itemView.findViewById(R.id.textView36);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_news_general_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        News news = newsList.get(position);

        // Load image using Glide or Picasso
        // Example using Glide:
        Glide.with(holder.itemView.getContext())
                .load(news.getImageUrl())
                .into(holder.imageView);

        holder.titleTextView.setText(news.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(news);
                }
            }
        });
    }
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(News news);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
    @Override
    public int getItemCount() {
        return newsList.size();
    }
    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView imageTextView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageTextView = itemView.findViewById(R.id.imageView16);
            titleTextView = itemView.findViewById(R.id.textView36);
        }
    }
}
