package com.example.science.Main.ui.main.Pages.profile.settings.API;

import com.example.science.Main.ui.main.Pages.profile.settings.LogoutMessage;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SettingsAPI {

    @FormUrlEncoded
    @POST("users/logout")
    Call<LogoutMessage> logoutUsers(@Field("token") String token);

}
