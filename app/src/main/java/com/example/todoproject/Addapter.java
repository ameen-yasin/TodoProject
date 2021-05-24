//package com.example.todoproject;
//
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.cardview.widget.CardView;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.todoproject.Activity.MainActivity;
//import com.example.todoproject.Activity.TaskActivityInfo;
//import com.example.todoproject.Model.Task;
//
//import java.util.List;
//
//public class Addapter extends RecyclerView.Adapter<Addapter.ViewHolder> {
//
//    private List<Task> taskList;
//    private Context context;
//
//    public Addapter(List<Task> taskList, Context context) {
//        this.taskList = taskList;
//        this.context = context;
//    }
//
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout,parent,false);
//    return new MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull Addapter.ViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        CardView cardView = holder.
//        holder.taskName.setText(taskList.get(position).getName());
//        holder.taskDate.setText(taskList.get(position).getDate());
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Addapter.this, TaskActivityInfo.class);
////                String id =
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return taskList.size();
//    }
//
//
////    public void deleteItem(int position){
////        taskList.remove(position);
////        notifyItemRemoved(position);
////    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//
//        TextView taskName;
//        TextView taskDate;
//
//        public MyViewHolder(@NonNull View itemView){
//            super(itemView);
//            taskName= itemView.findViewById(R.id.taskName);
//            taskDate = itemView.findViewById(R.id.date);
//        }
//    }
//}





package com.example.todoproject;


        import android.content.Context;
        import android.content.Intent;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.todoproject.Activity.MainActivity;
        import com.example.todoproject.Activity.MainActivityTwo;
        import com.example.todoproject.Model.Task;

        import java.util.List;

public class Addapter extends RecyclerView.Adapter<Addapter.MyViewHolder> {

    private List<Task> taskList;
    private Context context;


    public Addapter(List<Task> taskList, Context context) {
        this.taskList = taskList;
        this.context = context;
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivityTwo.class);
                int id = taskList.get(position).getId();
                String name = taskList.get(position).getName();
                String date = taskList.get(position).getDate();
                int status = taskList.get(position).getStatus();

                intent.putExtra("ID",id);
                intent.putExtra("NAME",name);
                intent.putExtra("DATE",date);
                intent.putExtra("STATUS",status);

                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }


//    public void deleteItem(int position){
//        taskList.remove(position);
//        notifyItemRemoved(position);
//    }

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
