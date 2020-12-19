package com.example.science.Main.ui.main.Pages.rooms.Student;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SearchRoomResponse implements  Serializable{

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("link")
    @Expose
    private String link;

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

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getGamersCount() {
        return gamersCount;
    }

    public void setGamersCount(String gamersCount) {
        this.gamersCount = gamersCount;
    }

    public String getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(String studentsCount) {
        this.studentsCount = studentsCount;
    }

    public String getDominoCount() {
        return dominoCount;
    }

    public void setDominoCount(String dominoCount) {
        this.dominoCount = dominoCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
