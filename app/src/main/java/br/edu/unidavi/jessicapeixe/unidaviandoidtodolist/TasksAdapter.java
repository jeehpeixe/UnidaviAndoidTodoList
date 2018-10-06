package br.edu.unidavi.jessicapeixe.unidaviandoidtodolist;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {

    private List<Task> tasks = new ArrayList<>();
    private final OnTaskClickListener listener;

    public TasksAdapter(OnTaskClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflator.inflate(android.R.layout.simple_list_item_1, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Task task = tasks.get(position);
        holder.title.setText(tasks.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                listener.onClick(task);
            }
        });
        if (task.isDone()) {
            holder.title.setTextColor(Color.GREEN);
            //holder.itemView.setBackgroundColor(Color.GREEN);
        }
        else {
            holder.title.setTextColor(Color.RED);
            //holder.itemView.setBackgroundColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void setup(List<Task> tasks) {
        this.tasks.clear();
        this.tasks.addAll(tasks);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        public ViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(android.R.id.text1);
        }
    }

    interface OnTaskClickListener{
        void onClick(Task task);
    }


}
