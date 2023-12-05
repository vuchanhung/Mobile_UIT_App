package com.example.bigproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigproject.R;

import java.util.ArrayList;

public class SubjectDetailAdapter extends RecyclerView.Adapter<SubjectDetailAdapter.ViewHolder> {

    private ArrayList<String> dataList; // Thay ArrayList<String> bằng kiểu dữ liệu của bạn

    public SubjectDetailAdapter(ArrayList<String> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_subject_detail_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = dataList.get(position);
        holder.textView.setText(item);
        // Xử lý hình ảnh nếu cần thiết
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView11);
            textView = itemView.findViewById(R.id.textView37);
            cardView = itemView.findViewById(R.id.practice); // Đảm bảo rằng bạn có ID cho CardView trong viewholder_subject_detail.xml
        }
    }
}

