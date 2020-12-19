package com.example.science.Main.ui.main.Pages.profile.API;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ProfileAPI {

    // POST-запрос для получения статистики пользователя
    @FormUrlEncoded
    @POST("student/get_stats")
    Call<StatsData> getUserStats(@Field("username") String username);

}
