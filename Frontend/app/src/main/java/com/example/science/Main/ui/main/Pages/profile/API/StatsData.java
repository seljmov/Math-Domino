package com.example.science.Main.ui.main.Pages.profile.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatsData {

    @SerializedName("points")
    @Expose
    private int points;

    @SerializedName("plus")
    @Expose
    private int plus;

    @SerializedName("minus")
    @Expose
    private int minus;

    public StatsData(int points, int plus, int minus) {
        this.points = points;
        this.plus = plus;
        this.minus = minus;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPlus() {
        return plus;
    }

    public void setPlus(int plus) {
        this.plus = plus;
    }

    public int getMinus() {
        return minus;
    }

    public void setMinus(int minus) {
        this.minus = minus;
    }
}
