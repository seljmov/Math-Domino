package com.example.science.Main.ui.main.Pages.profile.API;

import com.example.science.Other.RetrofitInstance;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileRepository {

    public Observable<StatsData> getUserStatsFromServer(final String username) {

        return Observable.create(new ObservableOnSubscribe<StatsData>() {
            @Override
            public void subscribe(ObservableEmitter<StatsData> emitter) throws Exception {

                ProfileAPI profileAPI = RetrofitInstance
                        .getRetrofitInstance().create(ProfileAPI.class);

                Call<StatsData> call = profileAPI.getUserStats(username);

                call.enqueue(new Callback<StatsData>() {
                    @Override
                    public void onResponse(Call<StatsData> call, Response<StatsData> response) {
                        emitter.onNext(response.body());
                    }

                    @Override
                    public void onFailure(Call<StatsData> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
