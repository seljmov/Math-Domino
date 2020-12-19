package com.example.science.UserAuthPackages.Reg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegToken {

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("userId")
    @Expose
    private int userId;

    public RegToken(String token, int userId) {
        this.token = token;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
