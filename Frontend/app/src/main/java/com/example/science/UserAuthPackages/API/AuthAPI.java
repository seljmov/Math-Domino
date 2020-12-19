package com.example.science.UserAuthPackages.API;

import com.example.science.UserAuthPackages.Auth.AuthMessage;
import com.example.science.UserAuthPackages.Login.LoginMessage;
import com.example.science.UserAuthPackages.Login.UserData;
import com.example.science.UserAuthPackages.Reg.RegToken;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthAPI {

    /*
    // GET запрос для получения информации пользователя
    @GET("/getUserInfo.php")
    Call<List<ProfileChars>> getUserInfo(@Query("username") String username);
    */

    // POST-запрос для авторизацию(Логирование) пользователя
    @FormUrlEncoded
    @POST("users/login")
    Call<LoginMessage> loginUser(@Field("email") String email,
                                 @Field("password") String password);

    // Post-запрос для получения данных о пользователе
    @FormUrlEncoded
    @POST("users/userdata")
    Call<UserData> loadDataFromServer(@Field("user_id") int userId);

    // POST-запрос для проверки username пользователя
    @FormUrlEncoded
    @POST("users/username")
    Call<ResponseBody> isUsernameExists(@Field("username") String username);

    // POST-запрос для регистрации пользователя
    @FormUrlEncoded
    @POST("users/registration")
    Call<RegToken> regUser(@Field("avatar") String avatar,
                           @Field("name") String name,
                           @Field("surname") String surname,
                           @Field("username") String username,
                           @Field("email") String email,
                           @Field("password") String password,
                           @Field("role") int role);

    // POST-запрос для проверки авторизованности пользователя
    @FormUrlEncoded
    @POST("users/auth")
    Call<AuthMessage> checkAuthWithToken(@Field("token") String token);
}
