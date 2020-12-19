package com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.Dominoes.APIInstruments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaskModel {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("task")
    @Expose
    private String task;

    @SerializedName("answer")
    @Expose
    private String answer;

    @SerializedName("points")
    @Expose
    private int points;

    public TaskModel(String title, String task, String answer, int points) {
        this.title = title;
        this.task = task;
        this.answer = answer;
        this.points = points;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
