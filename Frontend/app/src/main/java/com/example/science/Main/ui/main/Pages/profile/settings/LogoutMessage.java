package com.example.science.Main.ui.main.Pages.profile.settings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogoutMessage {

    @SerializedName("message")
    @Expose
    private String message;

    public LogoutMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
