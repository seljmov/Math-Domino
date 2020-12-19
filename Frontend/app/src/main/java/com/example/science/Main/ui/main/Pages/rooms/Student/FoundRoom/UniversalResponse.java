package com.example.science.Main.ui.main.Pages.rooms.Student.FoundRoom;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UniversalResponse {

    @SerializedName("response")
    @Expose
    private String response;

    public UniversalResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
