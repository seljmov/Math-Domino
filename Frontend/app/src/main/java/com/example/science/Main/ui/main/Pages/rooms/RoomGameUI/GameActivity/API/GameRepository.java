package com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.GameActivity.API;

import com.example.science.Other.RetrofitInstance;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameRepository implements GameContract.GameRepository {

    @Override
    public Observable<TaskData> getTaskFromServer(String link, int taskId) {
        return Observable.create(new ObservableOnSubscribe<TaskData>() {
            @Override
            public void subscribe(ObservableEmitter<TaskData> emitter) throws Exception {

                GameAPI gameAPI = RetrofitInstance
                        .getRetrofitInstance().create(GameAPI.class);

                Call<TaskData> call = gameAPI.getTask(link, taskId);

                call.enqueue(new Callback<TaskData>() {
                    @NonNull
                    @Override
                    public void onResponse(Call<TaskData> call, Response<TaskData> response) {
                        emitter.onNext(response.body());
                    }

                    @NonNull
                    @Override
                    public void onFailure(Call<TaskData> call, Throwable t) {
                        emitter.onError(t);
                    }
                });
            }
        });
    }

    @Override
    public Observable<AnswerResponse> checkTaskAnswer(String gamer, String answer, String link,
                                                      int dominoId, int taskId, int taskNum) {

        return Observable.create(new ObservableOnSubscribe<AnswerResponse>() {
            @Override
            public void subscribe(ObservableEmitter<AnswerResponse> emitter) throws Exception {

                GameAPI gameAPI = RetrofitInstance
                        .getRetrofitInstance().create(GameAPI.class);

                Call<AnswerResponse> call = gameAPI.sendAnswer(gamer, answer, link,
                                                                dominoId, taskId, taskNum);

                call.enqueue(new Callback<AnswerResponse>() {
                    @Override
                    public void onResponse(Call<AnswerResponse> call, Response<AnswerResponse> response) {
                        emitter.onNext(response.body());
                    }

                    @Override
                    public void onFailure(Call<AnswerResponse> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        });
    }
}
