package com.example.science.UserAuthPackages.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginMessage {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("userId")
    @Expose
    private int userId;

    public LoginMessage(String message, int userId) {
        this.message = message;
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
