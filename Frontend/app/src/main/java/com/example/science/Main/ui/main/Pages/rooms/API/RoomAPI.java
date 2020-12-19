package com.example.science.Main.ui.main.Pages.rooms.API;

import com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.Dominoes.APIInstruments.ClickDominoResponse;
import com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.Dominoes.APIInstruments.DominoModel;
import com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.Dominoes.APIInstruments.TaskModel;
import com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.RoomInfo.main.table.TableListModel;
import com.example.science.Main.ui.main.Pages.rooms.Student.FoundRoom.GamersList.GamerModel;
import com.example.science.Main.ui.main.Pages.rooms.Student.FoundRoom.UniversalResponse;
import com.example.science.Main.ui.main.Pages.rooms.Student.SearchRoomResponse;
import com.example.science.Main.ui.main.Pages.rooms.Teacher.RoomModel;
import com.example.science.Main.ui.main.Pages.rooms.Teacher.adapter.ResponseMessage;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RoomAPI {

    // POST-запрос для создания комнаты
    @FormUrlEncoded
    @POST("teacher/create_room")
    Call<ResponseMessage> createRoom(@Field("teacher") String teacher,
                                     @Field("name") String name,
                                     @Field("subject") String subject,
                                     @Field("count") int countStudent);

    // POST-запрос для получения списка комнат
    @FormUrlEncoded
    @POST("teacher/room_list")
    Call<List<RoomModel>> loadRoomsList(@Field("teacher") String teacher);


    // POST-запрос для проверки комнаты на существование
    @FormUrlEncoded
    @POST("student/exist_room")
    Call<UniversalResponse> roomExist(@Field("link") String link);

    // POST-запрос для поиска комнаты
    @FormUrlEncoded
    @POST("student/search_room")
    Call<ArrayList<SearchRoomResponse>> searchRoom(@Field("link") String link);

    // POST-запрос для подсоединения игрока к комнате
    @FormUrlEncoded
    @POST("student/user_connect")
    Call<UniversalResponse> connectToRoom(@Field("username") String username,
                                          @Field("roomName") String roomName,
                                          @Field("link") String link);

    // POST-запрос для проверки подсоединености игрока к комнате
    @FormUrlEncoded
    @POST("student/check_user_connect")
    Call<UniversalResponse> checkConnect(@Field("username") String username,
                                         @Field("link") String link);

    // POST-запрос для получения списка комнат
    @FormUrlEncoded
    @POST("student/my_room_list")
    Call<List<RoomModel>> loadMyRoomList(@Field("gamer") String gamer);


    // POST-запрос для получения списка игроков, присоединенных к комнате
    @FormUrlEncoded
    @POST("room/gamers_list")
    Call<List<GamerModel>> loadGamerList(@Field("link") String link);

    // POST-запрос для получения списка игроков, присоединенных к комнате
    @FormUrlEncoded
    @POST("room/gamers_list_for_table")
    Call<List<TableListModel>> loadTableGamersList(@Field("link") String link);

    // POST-запрос для получения списка комнатных задач
    @FormUrlEncoded
    @POST("room/load_task_list")
    Call<List<TaskModel>> loadTasksList(@Field("link") String link);

    // POST-запрос для получения статуса доминошки и задач
    @FormUrlEncoded
    @POST("room/domino_status")
    Call<DominoModel> checkDominoStatus(@Field("link") String link,
                                        @Field("domino_id") int domino_id);

    // POST-запрос для обновления статуса доминошки
    @FormUrlEncoded
    @POST("room/change_domino_status")
    Call<ClickDominoResponse> changeDominoStatus(@Field("link") String link,
                                                 @Field("domino_id") int domino_id,
                                                 @Field("gamer") String gamer);

}
