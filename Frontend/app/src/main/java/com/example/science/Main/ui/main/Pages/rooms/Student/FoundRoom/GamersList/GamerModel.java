package com.example.science.Main.ui.main.Pages.rooms.Student.FoundRoom.GamersList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GamerModel {

    @SerializedName("username")
    @Expose
    private String username;

    public GamerModel(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
