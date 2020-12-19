package com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.Dominoes.APIInstruments;

import java.util.ArrayList;

public class Task {

    private int id;
    private String title;
    private String task;
    private String answer;
    private int points;
    private int status = WAIT;

    static final int WAIT = 0;
    static final int NOT_DECIDED = 1;
    static final int DECIDED = 2;

    Task (ArrayList<TaskModel> list, int num) {
        id = num;
        title = list.get(num).getTitle();
        task = list.get(num).getTask();
        answer = list.get(num).getAnswer();
        points = list.get(num).getPoints();
    }

   /* Task (int id, String title, String task, String answer, int points) {
        this.id = id;
        this.title = title;
        this.task = task;
        this.answer = answer;
        this.points = points;
    }
    */

    public Task() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
