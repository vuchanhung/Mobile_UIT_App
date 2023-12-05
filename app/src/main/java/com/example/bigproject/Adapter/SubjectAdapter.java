package com.example.bigproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigproject.Activity.Chat;
import com.example.bigproject.Activity.SubjectDetail;
import com.example.bigproject.Model.myClass;
import com.example.bigproject.R;

import java.util.ArrayList;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {
    Context context;
    ArrayList<myClass> classArrayList;  // Change the type to ArrayList<myClass>

    public SubjectAdapter(Context context, ArrayList<myClass> classArrayList){
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
        myClass enrolledClass = classArrayList.get(position);

        // Log the class name and teacher


        // Set the class name and teacher for the TextViews in the ViewHolder
        holder.nameClass.setText(enrolledClass.getName());
        holder.nameTeacher.setText(enrolledClass.getTeacher());
        holder.buttonChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click, you can start a new activity or do something else
                // For example, you can start a new activity:
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, Chat.class);
                intent.putExtra("idGroupchat", enrolledClass.getChat());
                context.startActivity(intent);
            }
        });
        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click, you can start a new activity or do something else
                // For example, you can start a new activity:
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, SubjectDetail.class);
                intent.putExtra("idGroupchat", enrolledClass.getChat());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return classArrayList.size();
    }

    public static class SubjectViewHolder extends RecyclerView.ViewHolder {
        TextView nameClass;
        TextView nameTeacher;

        Button btnDetail;
        Button buttonChat;
        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            nameClass = itemView.findViewById(R.id.textView19);
            nameTeacher = itemView.findViewById(R.id.textView20);
            buttonChat = itemView.findViewById(R.id.chat);
            btnDetail = itemView.findViewById(R.id.button4);
        }
    }
}
