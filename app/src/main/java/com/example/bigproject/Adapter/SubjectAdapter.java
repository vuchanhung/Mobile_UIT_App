package com.example.bigproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigproject.R;

import java.util.ArrayList;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {
    Context context;
    ArrayList<String> classArrayList;  // Change the type to ArrayList<String>

    public SubjectAdapter(Context context, ArrayList<String> classArrayList){
        this.context = context;
        this.classArrayList = classArrayList;
    }

    @NonNull
    @Override
    public SubjectAdapter.SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.viewholder_subject_list, parent, false);
        return new SubjectViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectAdapter.SubjectViewHolder holder, int position) {
        String className = classArrayList.get(position);

        // Set the class name for the TextView in the ViewHolder
        holder.nameClass.setText(className);
    }

    @Override
    public int getItemCount() {
        return classArrayList.size();
    }

    public static class SubjectViewHolder extends RecyclerView.ViewHolder {
        TextView nameClass;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            nameClass = itemView.findViewById(R.id.textView19);
        }
    }
}

