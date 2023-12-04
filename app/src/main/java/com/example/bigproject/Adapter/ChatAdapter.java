package com.example.bigproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigproject.R;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private List<DocumentSnapshot> messageSnapshots;

    public ChatAdapter() {
        this.messageSnapshots = new ArrayList<>();
    }
    public void setMessages(List<DocumentSnapshot> messageSnapshots) {
        // Sắp xếp danh sách theo timestamp trước khi cập nhật
        Collections.sort(messageSnapshots, new Comparator<DocumentSnapshot>() {
            @Override
            public int compare(DocumentSnapshot o1, DocumentSnapshot o2) {
                long timestamp1 = o1.getLong("timestamp");
                long timestamp2 = o2.getLong("timestamp");
                return Long.compare(timestamp1, timestamp2);
            }
        });

        this.messageSnapshots = messageSnapshots;
        notifyDataSetChanged(); // Thông báo là dữ liệu đã thay đổi
    }
    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new ChatViewHolder(view);
    }

        @Override
        public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
            DocumentSnapshot messageSnapshot = messageSnapshots.get(position);

            holder.senderTextView.setText(messageSnapshot.getString("senderId"));
            holder.messageTextView.setText(messageSnapshot.getString("content"));
            holder.timestampTextView.setText(formatTimestamp(messageSnapshot.getLong("timestamp")));
        }

    @Override
    public int getItemCount() {
        return messageSnapshots.size();
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView senderTextView;
        TextView messageTextView;
        TextView timestampTextView;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            senderTextView = itemView.findViewById(R.id.textViewSender);
            messageTextView = itemView.findViewById(R.id.textViewMessage);
            timestampTextView = itemView.findViewById(R.id.textViewTimestamp);
        }
    }

    private String formatTimestamp(long timestamp) {
        // Add your logic to format the timestamp (e.g., convert it to a readable date/time)
        // ...

        return String.valueOf(timestamp);
    }
}