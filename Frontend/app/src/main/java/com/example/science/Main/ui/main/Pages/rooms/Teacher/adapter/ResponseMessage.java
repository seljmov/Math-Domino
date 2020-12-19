package com.example.science.Main.ui.main.Pages.rooms.Teacher.adapter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseMessage {

    @SerializedName("link")
    @Expose
    private String link;

    public ResponseMessage(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
