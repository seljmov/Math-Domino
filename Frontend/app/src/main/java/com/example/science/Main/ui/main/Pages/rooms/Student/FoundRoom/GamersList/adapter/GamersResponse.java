package com.example.science.Main.ui.main.Pages.rooms.Student.FoundRoom.GamersList.adapter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GamersResponse {

    @SerializedName("username")
    @Expose
    private String username;

    public GamersResponse(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}