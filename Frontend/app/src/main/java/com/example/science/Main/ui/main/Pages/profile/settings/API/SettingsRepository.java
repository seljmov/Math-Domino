package com.example.science.Main.ui.main.Pages.profile.settings.API;

import com.example.science.Main.ui.main.Pages.profile.settings.LogoutMessage;
import com.example.science.Other.RetrofitInstance;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsRepository implements SettingsContract.Repository {
    @Override
    public Observable<LogoutMessage> logoutUser(String token) {
        Observable<LogoutMessage> logoutMessageObservable =
                Observable.create(new ObservableOnSubscribe<LogoutMessage>() {
                    @Override
                    public void subscribe(final ObservableEmitter<LogoutMessage> emitter) throws Exception {

                        SettingsAPI settingsAPI = RetrofitInstance
                                .getRetrofitInstance().create(SettingsAPI.class);

                        Call<LogoutMessage> call = settingsAPI.logoutUsers(token);

                        call.enqueue(new Callback<LogoutMessage>() {
                            @Override
                            public void onResponse(Call<LogoutMessage> call, Response<LogoutMessage> response) {
                                emitter.onNext(response.body());
                            }

                            @Override
                            public void onFailure(Call<LogoutMessage> call, Throwable t) {
                                emitter.onError(t);
                            }
                        });

                    }
                })
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread());

        return logoutMessageObservable;
    }
}
