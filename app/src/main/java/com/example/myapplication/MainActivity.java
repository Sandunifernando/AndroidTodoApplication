package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.adapters.TodoAdapter;
import com.example.myapplication.models.ToDo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    RecyclerView rv_todolist;
    ArrayList<ToDo> toDoItemList;
    FloatingActionButton addToDoItem;
    TodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addToDoItem = findViewById(R.id.fab_add_todo_item);
        addToDoItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TodoDetailActivity.class);
                startActivityForResult(intent,1);
            }
        });

        rv_todolist = (RecyclerView) findViewById(R.id.rv_todolist);
        toDoItemList = ToDo.createDummyToDoList(100);

        adapter = new TodoAdapter(toDoItemList,this);
        rv_todolist.setAdapter(adapter);

        rv_todolist.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2)
        {
            toDoItemList.set(data.getIntExtra("Position",0), new ToDo(data.getStringExtra("Title"),data.getStringExtra("Description")));
            adapter.notifyDataSetChanged();
            rv_todolist.smoothScrollToPosition(data.getIntExtra("Position",0));
        }

        if(requestCode==1){
            toDoItemList.add(new ToDo(data.getStringExtra("Title"),data.getStringExtra("Description")));
            adapter.notifyDataSetChanged();
            rv_todolist.smoothScrollToPosition(toDoItemList.size());
        }
    }
}