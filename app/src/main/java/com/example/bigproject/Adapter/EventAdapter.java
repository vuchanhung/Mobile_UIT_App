package com.example.bigproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigproject.Model.myClass;
import com.example.bigproject.R;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    Context context;
    ArrayList<myClass> classArrayList;  // Change the type to ArrayList<myClass>

    public EventAdapter(Context context, ArrayList<myClass> classArrayList){
        this.context = context;
        this.classArrayList = classArrayList;
    }

    @NonNull
    @Override
    public EventAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.viewholder_event_list, parent, false);
        return new EventAdapter.EventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.EventViewHolder holder, int position) {
        myClass enrolledClass = classArrayList.get(position);
        // Log the class name and teacher
        // Set the class name and teacher for the TextViews in the ViewHolder
        holder.classTitle.setText(enrolledClass.getName());
        holder.lecturerName.setText(enrolledClass.getTeacher());
        holder.classNumber.setText(enrolledClass.getClassNumber());

    }

    public int getItemCount() {
        return classArrayList.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView classTitle;
        TextView lecturerName;
        TextView classNumber;
        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            classTitle = itemView.findViewById(R.id.classTitle);
            lecturerName = itemView.findViewById(R.id.lecturerName);
            classNumber = itemView.findViewById(R.id.classNumber);
        }
    }

}
