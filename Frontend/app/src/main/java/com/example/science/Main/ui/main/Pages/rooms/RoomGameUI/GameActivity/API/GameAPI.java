package com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.GameActivity.API;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GameAPI {

    // POST-запрос для получения задачи доминошки
    @FormUrlEncoded
    @POST("room/get_task")
    Call<TaskData> getTask(@Field("link") String link,
                           @Field("task_id") int taskId);

    // POST-запрос для проверки ответа пользователя
    @FormUrlEncoded
    @POST("room/check_task_answer")
    Call<AnswerResponse> sendAnswer(@Field("gamer") String gamer,
                                    @Field("answer") String answer,
                                    @Field("link") String link,
                                    @Field("domino_id") int dominoId,
                                    @Field("task_id") int taskId,
                                    @Field("task_num") int taskNum);

}
