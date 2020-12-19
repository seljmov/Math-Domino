package com.example.science.UserAuthPackages.API;


import com.example.science.Other.RetrofitInstance;
import com.example.science.UserAuthPackages.Auth.AuthMessage;
import com.example.science.UserAuthPackages.Login.LoginMessage;
import com.example.science.UserAuthPackages.Login.UserData;
import com.example.science.UserAuthPackages.Reg.RegToken;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class Repository implements MainContract.Repository {

    @Override
    public Observable<LoginMessage> loginUserToApp(final String email,
                                                   final String password) {

        Observable<LoginMessage> messageObservable =
                Observable.create(new ObservableOnSubscribe<LoginMessage>() {
                    @Override
                    public void subscribe(final ObservableEmitter<LoginMessage> emitter) throws Exception {

                        AuthAPI authAPI = RetrofitInstance.getRetrofitInstance()
                                .create(AuthAPI.class);

                        Call<LoginMessage> call = authAPI.loginUser(email, password);

                        call.enqueue(new Callback<LoginMessage>() {
                            @EverythingIsNonNull
                            @Override
                            public void onResponse(Call<LoginMessage> call, Response<LoginMessage> response) {
                                emitter.onNext(response.body());
                            }

                            @EverythingIsNonNull
                            @Override
                            public void onFailure(Call<LoginMessage> call, Throwable t) {
                                emitter.onError(t);
                            }
                        });

                    }
                })
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread());

        return messageObservable;
    }

    @Override
    public Observable<UserData> loadDataAboutUser(final int userId) {

        Observable<UserData> dataObservable =
                Observable.create(new ObservableOnSubscribe<UserData>() {
                    @Override
                    public void subscribe(final ObservableEmitter<UserData> emitter) throws Exception {

                        AuthAPI authAPI = RetrofitInstance
                                .getRetrofitInstance().create(AuthAPI.class);

                        Call<UserData> call = authAPI.loadDataFromServer(userId);

                        call.enqueue(new Callback<UserData>() {
                            @Override
                            public void onResponse(Call<UserData> call, Response<UserData> response) {
                                emitter.onNext(response.body());
                            }

                            @Override
                            public void onFailure(Call<UserData> call, Throwable t) {
                                emitter.onError(t);
                            }
                        });

                    }
                })
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread());

        return dataObservable;
    }

    @Override
    public Observable<ResponseBody> isUsername(final String username) {
        Observable<ResponseBody> observable =
                Observable.create(new ObservableOnSubscribe<ResponseBody>() {
                    @Override
                    public void subscribe(final ObservableEmitter<ResponseBody> emitter) throws Exception {

                        AuthAPI authAPI = RetrofitInstance
                                .getRetrofitInstance().create(AuthAPI.class);

                        Call<ResponseBody> call = authAPI.isUsernameExists(username);

                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                emitter.onNext(response.body());
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                emitter.onError(t);
                            }
                        });

                    }
                })
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread());

        return observable;
    }

    @Override
    public Observable<RegToken> regUserToServer(final String avatar, final String name,
                                                final String surname, final String username,
                                                final String email, final String password,
                                                final int role) {

        Observable<RegToken> tokenObservable =
                Observable.create(new ObservableOnSubscribe<RegToken>() {
                    @Override
                    public void subscribe(final ObservableEmitter<RegToken> emitter) {

                        AuthAPI authAPI = RetrofitInstance
                                .getRetrofitInstance().create(AuthAPI.class);

                        Call<RegToken> call = authAPI.regUser(avatar, name,
                                                                surname, username,
                                                                email, password, role);

                        call.enqueue(new Callback<RegToken>() {
                            @EverythingIsNonNull
                            @Override
                            public void onResponse(Call<RegToken> call, Response<RegToken> response) {
                                emitter.onNext(response.body());
                            }

                            @EverythingIsNonNull
                            @Override
                            public void onFailure(Call<RegToken> call, Throwable t) {
                                emitter.onError(t);
                            }
                        });

                    }
                })
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread());

        return tokenObservable;
    }

    @Override
    public Observable<AuthMessage> checkUserToken(final String token) {

        Observable<AuthMessage> messageObservable =
                Observable.create(new ObservableOnSubscribe<AuthMessage>() {
                    @Override
                    public void subscribe(final ObservableEmitter<AuthMessage> emitter) throws Exception {

                        AuthAPI authAPI = RetrofitInstance
                                .getRetrofitInstance().create(AuthAPI.class);

                        Call<AuthMessage> call = authAPI.checkAuthWithToken(token);

                        call.enqueue(new Callback<AuthMessage>() {
                            @Override
                            public void onResponse(Call<AuthMessage> call, Response<AuthMessage> response) {
                                emitter.onNext(response.body());
                            }

                            @Override
                            public void onFailure(Call<AuthMessage> call, Throwable t) {
                                emitter.onError(t);
                            }
                        });
                    }
                })
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread());

        return messageObservable;
    }
}
