package com.example.science.UserAuthPackages.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthMessage {

    @SerializedName("message")
    @Expose
    private String authMessage;

    public AuthMessage(String authMessage) {
        this.authMessage = authMessage;
    }

    public String getAuthMessage() {
        return authMessage;
    }

    public void setAuthMessage(String authMessage) {
        this.authMessage = authMessage;
    }
}
