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
import com.example.science.Other.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomRepository implements RoomContract.Repository {

    @Override
    public Observable<ResponseMessage> createRoomToServer(final String teacher, final String name,
                                                          final String subject, final int countStudent) {

        return Observable.create(new ObservableOnSubscribe<ResponseMessage>() {
            @Override
            public void subscribe(final ObservableEmitter<ResponseMessage> emitter) throws Exception {

                RoomAPI roomAPI = RetrofitInstance
                        .getRetrofitInstance().create(RoomAPI.class);

                Call<ResponseMessage> call = roomAPI.createRoom(teacher, name, subject, countStudent);

                call.enqueue(new Callback<ResponseMessage>() {
                    @Override
                    public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                        emitter.onNext(response.body());
                    }

                    @Override
                    public void onFailure(Call<ResponseMessage> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        })
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<RoomModel>> loadRoomsListFromServer(final String teacher) {
        return Observable.create(new ObservableOnSubscribe<List<RoomModel>>() {
            @Override
            public void subscribe(final ObservableEmitter<List<RoomModel>> emitter) throws Exception {

                RoomAPI roomAPI = RetrofitInstance
                        .getRetrofitInstance().create(RoomAPI.class);

                Call<List<RoomModel>> call = roomAPI.loadRoomsList(teacher);

                call.enqueue(new Callback<List<RoomModel>>() {
                    @Override
                    public void onResponse(Call<List<RoomModel>> call, Response<List<RoomModel>> response) {
                        emitter.onNext(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<RoomModel>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<RoomModel>> loadMyRoomsListFromServer(final String gamer) {
        return Observable.create(new ObservableOnSubscribe<List<RoomModel>>() {
            @Override
            public void subscribe(final ObservableEmitter<List<RoomModel>> emitter) throws Exception {

                RoomAPI roomAPI = RetrofitInstance
                        .getRetrofitInstance().create(RoomAPI.class);

                Call<List<RoomModel>> call = roomAPI.loadMyRoomList(gamer);

                call.enqueue(new Callback<List<RoomModel>>() {
                    @Override
                    public void onResponse(Call<List<RoomModel>> call, Response<List<RoomModel>> response) {
                        emitter.onNext(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<RoomModel>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<ArrayList<SearchRoomResponse>> searchRoomForUser(final String link) {
        return Observable.create(new ObservableOnSubscribe<ArrayList<SearchRoomResponse>>() {
            @Override
            public void subscribe(final ObservableEmitter<ArrayList<SearchRoomResponse>> emitter) throws Exception {

                RoomAPI roomAPI = RetrofitInstance
                        .getRetrofitInstance().create(RoomAPI.class);

                Call<ArrayList<SearchRoomResponse>> call = roomAPI.searchRoom(link);

                call.enqueue(new Callback<ArrayList<SearchRoomResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<SearchRoomResponse>> call,
                                           Response<ArrayList<SearchRoomResponse>> response) {

                        emitter.onNext(response.body());
                    }

                    @Override
                    public void onFailure(Call<ArrayList<SearchRoomResponse>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<UniversalResponse> doesRoomExist(final String link) {
        return Observable.create(new ObservableOnSubscribe<UniversalResponse>() {
            @Override
            public void subscribe(final ObservableEmitter<UniversalResponse> emitter) throws Exception {

                RoomAPI roomAPI = RetrofitInstance
                        .getRetrofitInstance().create(RoomAPI.class);

                Call<UniversalResponse> call = roomAPI.roomExist(link);

                call.enqueue(new Callback<UniversalResponse>() {
                    @Override
                    public void onResponse(Call<UniversalResponse> call, Response<UniversalResponse> response) {
                        emitter.onNext(response.body());
                    }

                    @Override
                    public void onFailure(Call<UniversalResponse> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<UniversalResponse> userConnectToRoom(final String username,
                                                           final String roomName, final String link) {
        return Observable.create(new ObservableOnSubscribe<UniversalResponse>() {
            @Override
            public void subscribe(final ObservableEmitter<UniversalResponse> emitter) throws Exception {

                RoomAPI roomAPI = RetrofitInstance
                        .getRetrofitInstance().create(RoomAPI.class);

                Call<UniversalResponse> call = roomAPI.connectToRoom(username, roomName, link);

                call.enqueue(new Callback<UniversalResponse>() {
                    @Override
                    public void onResponse(Call<UniversalResponse> call, Response<UniversalResponse> response) {
                        emitter.onNext(response.body());
                    }

                    @Override
                    public void onFailure(Call<UniversalResponse> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<UniversalResponse> isUserConnect(final String username, final String link) {
        return Observable.create(new ObservableOnSubscribe<UniversalResponse>() {
            @Override
            public void subscribe(final ObservableEmitter<UniversalResponse> emitter) throws Exception {

                RoomAPI roomAPI = RetrofitInstance
                        .getRetrofitInstance().create(RoomAPI.class);

                Call<UniversalResponse> call = roomAPI.checkConnect(username, link);

                call.enqueue(new Callback<UniversalResponse>() {
                    @Override
                    public void onResponse(Call<UniversalResponse> call, Response<UniversalResponse> response) {
                        emitter.onNext(response.body());
                    }

                    @Override
                    public void onFailure(Call<UniversalResponse> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<GamerModel>> loadRoomGamersList(final String link) {
        return Observable.create(new ObservableOnSubscribe<List<GamerModel>>() {
            @Override
            public void subscribe(final ObservableEmitter<List<GamerModel>> emitter) throws Exception {

                RoomAPI roomAPI = RetrofitInstance
                        .getRetrofitInstance().create(RoomAPI.class);

                Call<List<GamerModel>> call = roomAPI.loadGamerList(link);

                call.enqueue(new Callback<List<GamerModel>>() {
                    @Override
                    public void onResponse(Call<List<GamerModel>> call, Response<List<GamerModel>> response) {
                        emitter.onNext(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<GamerModel>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<TableListModel>> loadRoomTableGamersList(String link) {
        return Observable.create((ObservableOnSubscribe<List<TableListModel>>) emitter -> {

            RoomAPI roomAPI = RetrofitInstance
                    .getRetrofitInstance().create(RoomAPI.class);

            Call<List<TableListModel>> call = roomAPI.loadTableGamersList(link);

            call.enqueue(new Callback<List<TableListModel>>() {
                @Override
                public void onResponse(Call<List<TableListModel>> call, Response<List<TableListModel>> response) {
                    emitter.onNext(response.body());
                }

                @Override
                public void onFailure(Call<List<TableListModel>> call, Throwable t) {
                    emitter.onError(t);
                }
            });

        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<TaskModel>> loadRoomTasksList(String link) {
        return Observable.create((ObservableOnSubscribe<List<TaskModel>>) emitter -> {

            RoomAPI roomAPI = RetrofitInstance
                    .getRetrofitInstance().create(RoomAPI.class);

            Call<List<TaskModel>> call = roomAPI.loadTasksList(link);

            call.enqueue(new Callback<List<TaskModel>>() {
                @Override
                public void onResponse(Call<List<TaskModel>> call, Response<List<TaskModel>> response) {
                    emitter.onNext(response.body());
                }

                @Override
                public void onFailure(Call<List<TaskModel>> call, Throwable t) {
                    emitter.onError(t);
                }
            });

        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<DominoModel> loadDominoStatus(String link, int id) {
        return Observable.create(new ObservableOnSubscribe<DominoModel>() {
            @Override
            public void subscribe(ObservableEmitter<DominoModel> emitter) throws Exception {

                RoomAPI roomAPI = RetrofitInstance
                        .getRetrofitInstance().create(RoomAPI.class);

                Call<DominoModel> call = roomAPI.checkDominoStatus(link, id);

                call.enqueue(new Callback<DominoModel>() {
                    @Override
                    public void onResponse(Call<DominoModel> call, Response<DominoModel> response) {
                        emitter.onNext(response.body());
                    }

                    @Override
                    public void onFailure(Call<DominoModel> call, Throwable t) {
                        emitter.onError(t);
                    }
                });

            }
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<ClickDominoResponse> changeDominoStatusFromServer(String link, int id, String gamer) {
        return Observable.create(new ObservableOnSubscribe<ClickDominoResponse>() {
            @Override
            public void subscribe(ObservableEmitter<ClickDominoResponse> emitter) throws Exception {

                RoomAPI roomAPI = RetrofitInstance
                        .getRetrofitInstance().create(RoomAPI.class);

                Call<ClickDominoResponse> call = roomAPI.changeDominoStatus(link, id, gamer);

                call.enqueue(new Callback<ClickDominoResponse>() {
                    @Override
                    public void onResponse(Call<ClickDominoResponse> call, Response<ClickDominoResponse> response) {
                        emitter.onNext(response.body());
                    }

                    @Override
                    public void onFailure(Call<ClickDominoResponse> call, Throwable t) {
                        emitter.onError(t);
                    }
                });
            }
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }
}