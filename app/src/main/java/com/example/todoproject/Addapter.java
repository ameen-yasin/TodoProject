package com.example.todoproject;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoproject.Model.Task;

import java.util.List;

public class Addapter extends RecyclerView.Adapter<Addapter.MyViewHolder> {

    private List<Task> taskList;

    public Addapter(List<Task> taskList) {
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout,parent,false);
    return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.taskName.setText(taskList.get(position).getName());
        holder.taskDate.setText(taskList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView taskName;
        TextView taskDate;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            taskName= itemView.findViewById(R.id.taskName);
            taskDate = itemView.findViewById(R.id.date);
        }
    }
}
