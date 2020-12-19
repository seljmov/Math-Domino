package com.example.science.Main.ui.main.Pages.rooms.Teacher;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RoomModel implements Serializable {


    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("link")
    @Expose
    private String link;

    @SerializedName("icon")
    @Expose
    private String icon;

    @SerializedName("roomName")
    @Expose
    private String roomName;

    @SerializedName("subject")
    @Expose
    private String subject;

    @SerializedName("teacher")
    @Expose
    private String teacher;

    @SerializedName("gamersCount")
    @Expose
    private String gamersCount;

    @SerializedName("studentsCount")
    @Expose
    private String studentsCount;

    @SerializedName("dominoCount")
    @Expose
    private String dominoCount;

    @SerializedName("date")
    @Expose
    private String date;

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getLink() {
        return link;
    }

    public String getIcon() {
        return icon;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getSubject() {
        return subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getGamersCount() {
        return gamersCount;
    }

    public String getStudentsCount() {
        return studentsCount;
    }

    public String getDominoCount() {
        return dominoCount;
    }

    public String getDate() {
        return date;
    }
}
