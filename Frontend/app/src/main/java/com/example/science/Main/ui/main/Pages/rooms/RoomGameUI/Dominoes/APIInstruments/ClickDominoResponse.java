package com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.Dominoes.APIInstruments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClickDominoResponse {

    @SerializedName("response")
    @Expose
    private String response;

    public ClickDominoResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
