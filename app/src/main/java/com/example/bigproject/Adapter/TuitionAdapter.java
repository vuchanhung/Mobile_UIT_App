package com.example.bigproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigproject.R;

import java.util.List;
public class TuitionAdapter extends RecyclerView.Adapter<TuitionAdapter.ViewHolder> {

    private List<String> dataList; // Đổi kiểu dữ liệu tùy thuộc vào dữ liệu bạn muốn hiển thị
    private Context context;

    // Constructor để truyền dữ liệu vào adapter
    public TuitionAdapter(Context context, List<String> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Tạo ViewHolder dựa trên viewType nếu có nhiều loại mục
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder_tuition_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Lấy dữ liệu cho mục tại vị trí position
        String data = dataList.get(position);

        // Liên kết dữ liệu với các thành phần trong mỗi mục
        holder.school_year.setText(data);  // Đổi thành id phù hợp với TextView của bạn
        // Các thành phần khác tương tự...

        // Xử lý sự kiện khi nhấn vào Button
        holder.tuition_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý sự kiện khi nhấn nút "Nộp học phí"
                // Đưa ra hành động cụ thể tương ứng với nút này
            }
        });

        holder.extend_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý sự kiện khi nhấn nút "Gia hạn"
                // Đưa ra hành động cụ thể tương ứng với nút này
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // Khai báo các thành phần trong mỗi mục
        TextView school_year;
        TextView semester;
        TextView term_info;
        TextView total_tuition;
        Button tuition_btn;
        Button extend_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Ánh xạ các thành phần từ itemView
            school_year = itemView.findViewById(R.id.school_year);
            semester = itemView.findViewById(R.id.semester);
            term_info = itemView.findViewById(R.id.term_info);
            total_tuition = itemView.findViewById(R.id.total_tuition);
            tuition_btn = itemView.findViewById(R.id.tuition_btn);
            extend_btn = itemView.findViewById(R.id.extend_btn);
        }
    }
}

//    // Trong Activity
//    RecyclerView recyclerView = findViewById(R.id.recyclerView);
//    CustomAdapter adapter = new CustomAdapter(this, yourDataList);
//recyclerView.setAdapter(adapter);
//7
//// Hoặc trong Fragment
//        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
//        CustomAdapter adapter = new CustomAdapter(requireContext(), yourDataList);
//        recyclerView.setAdapter(adapter);


