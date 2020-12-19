package com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.RoomInfo.main.table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TableListModel {

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("points")
    @Expose
    private String points;

    @SerializedName("plus")
    @Expose
    private String plus;

    @SerializedName("minus")
    @Expose
    private String minus;

    public TableListModel(String username, String points, String plus, String minus) {
        this.username = username;
        this.points = points;
        this.plus = plus;
        this.minus = minus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getPlus() {
        return plus;
    }

    public void setPlus(String plus) {
        this.plus = plus;
    }

    public String getMinus() {
        return minus;
    }

    public void setMinus(String minus) {
        this.minus = minus;
    }
}
