package com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.GameActivity.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaskData {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("task")
    @Expose
    private String task;

    public TaskData(String title, String task) {
        this.title = title;
        this.task = task;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
