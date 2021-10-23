package com.example.myapplication.adapters;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.MainActivity;
import com.example.myapplication.models.ToDo;
import com.example.myapplication.TodoDetailActivity;

import java.util.List;



public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {
    private List<ToDo> mTodoList;
    private Activity activity;

    public TodoAdapter(List<ToDo> toDoList, Activity activity){
        mTodoList = toDoList; this.activity = activity;
    }

    @Override
    public int getItemCount() {
        return mTodoList.size();
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View todoView = inflater.inflate(R.layout.todo_cell_item,parent,false);

        ViewHolder viewHolder = new ViewHolder(todoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TodoAdapter.ViewHolder holder, int position) {
        ToDo toDoItem = mTodoList.get(position);

        TextView tv_title = holder.tv_title;
        tv_title.setText(toDoItem.getTitle());

        TextView tv_description = holder.tv_description;
        tv_description.setText(toDoItem.getDescription());

        ImageView iv_delete = holder.iv_delete;
        iv_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mTodoList.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, TodoDetailActivity.class);
                intent.putExtra("Title", mTodoList.get(position).getTitle());
                intent.putExtra("Description", mTodoList.get(position).getDescription());
                intent.putExtra("RequestCode", 2);
                intent.putExtra("Position", position);
                activity.startActivityForResult(intent,2);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView iv_delete;
        public TextView tv_title;
        public TextView tv_description;

        public ViewHolder (View toDoItemView){
            super(toDoItemView);
            tv_title = (TextView) toDoItemView.findViewById(R.id.tv_title);
            tv_description = (TextView) toDoItemView.findViewById(R.id.tv_description);
            iv_delete = (ImageView) toDoItemView.findViewById(R.id.iv_delete);
        }
    }
}
