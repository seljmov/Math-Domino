package com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.Dominoes.APIInstruments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DominoModel {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("roomLink")
    @Expose
    private String roomLink;

    @SerializedName("gamer")
    @Expose
    private String gamer;

    @SerializedName("domino_id")
    @Expose
    private int dominoId;

    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("task1Status")
    @Expose
    private int task1Status;

    @SerializedName("task2Status")
    @Expose
    private int task2Status;

    public DominoModel(int id, String roomLink, String gamer, int dominoId, int status, int task1Status, int task2Status) {
        this.id = id;
        this.roomLink = roomLink;
        this.gamer = gamer;
        this.dominoId = dominoId;
        this.status = status;
        this.task1Status = task1Status;
        this.task2Status = task2Status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomLink() {
        return roomLink;
    }

    public void setRoomLink(String roomLink) {
        this.roomLink = roomLink;
    }

    public String getGamer() {
        return gamer;
    }

    public void setGamer(String gamer) {
        this.gamer = gamer;
    }

    public int getDominoId() {
        return dominoId;
    }

    public void setDominoId(int dominoId) {
        this.dominoId = dominoId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTask1Status() {
        return task1Status;
    }

    public void setTask1Status(int task1Status) {
        this.task1Status = task1Status;
    }

    public int getTask2Status() {
        return task2Status;
    }

    public void setTask2Status(int task2Status) {
        this.task2Status = task2Status;
    }
}
