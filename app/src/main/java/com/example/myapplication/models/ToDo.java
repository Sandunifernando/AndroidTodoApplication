package com.example.myapplication.models;

import java.util.ArrayList;

public class ToDo {
    private String title = "";
    private String description = "";

    public ToDo(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public static ArrayList<ToDo> createDummyToDoList(int noOfItems) {
        ArrayList<ToDo> todoItems = new ArrayList<ToDo>();

        for (int i = 1; i <= noOfItems; i++) {
            todoItems.add(new ToDo("Todo Item #"+i,"This is a dummy description for todo #"+i));
        }

        return todoItems;
    }
}

