package com.example.bigproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigproject.Domain.SubjectDomain;
import com.example.bigproject.R;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {

    private List<SubjectDomain> subjectList;
    private Context context;

    private List<SubjectDomain> filteredList; // Danh sách đã lọc
    public SubjectAdapter(List<SubjectDomain> subjectList, Context context) {
        this.subjectList = subjectList;
        this.context = context;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_subject_list, parent, false);
        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        SubjectDomain subject = subjectList.get(position);
        holder.textViewTitle.setText(subject.getCourseName());
        holder.textViewLecturer.setText(subject.getLecturer());
        // Gán sự kiện click vào button
        holder.buttonDetail.setOnClickListener(v -> {
            // Xử lý sự kiện khi click vào button Chi Tiết
            // Ví dụ: Mở activity hoặc thực hiện hành động mong muốn
        });
        holder.buttonChat.setOnClickListener(v -> {
            // Xử lý sự kiện khi click vào button Group Chat
            // Ví dụ: Mở activity chat hoặc thực hiện hành động mong muốn
        });
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    public static class SubjectViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewLecturer;
        Button buttonDetail;
        Button buttonChat;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textView17);
            textViewLecturer = itemView.findViewById(R.id.textView20);
            buttonDetail = itemView.findViewById(R.id.button4);
            buttonChat = itemView.findViewById(R.id.button5);
        }
    }

    public void filterByYear(String selectedYear) {
        filteredList.clear(); // Xóa danh sách đã lọc trước đó

        // Lọc lại các mục theo năm được chọn
        for (SubjectDomain subject : subjectList) {
            if (subject.getYear().equals(selectedYear)) {
                filteredList.add(subject);
            }
        }

        notifyDataSetChanged(); // Thông báo cho RecyclerView cập nhật lại danh sách hiển thị
    }
}
