package com.example.bigproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigproject.Model.Lichthi;
import com.example.bigproject.R;

import java.util.List;

public class LichthiAdapter extends RecyclerView.Adapter<LichthiAdapter.LichthiViewHolder> {

    private List<Lichthi> lichthiList;

    public LichthiAdapter(List<Lichthi> lichthiList) {
        this.lichthiList = lichthiList;
    }

    @NonNull
    @Override
    public LichthiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_lich_thi, parent, false);
        return new LichthiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichthiViewHolder holder, int position) {
        Lichthi lichthi = lichthiList.get(position);

        holder.thoiDiemThiTextView.setText(lichthi.getTime());
        holder.caThiTextView.setText(lichthi.getCa());
        holder.tenMonTextView.setText(lichthi.getName());
        holder.phongTextView.setText(lichthi.getRoom());
    }

    @Override
    public int getItemCount() {
        return lichthiList.size();
    }

    public static class LichthiViewHolder extends RecyclerView.ViewHolder {
        TextView thoiDiemThiTextView;
        TextView caThiTextView;
        TextView tenMonTextView;
        TextView phongTextView;

        public LichthiViewHolder(@NonNull View itemView) {
            super(itemView);

            thoiDiemThiTextView = itemView.findViewById(R.id.thoidiemthi);
            caThiTextView = itemView.findViewById(R.id.cathi);
            tenMonTextView = itemView.findViewById(R.id.tenmon);
            phongTextView = itemView.findViewById(R.id.phong);
        }
    }
}
