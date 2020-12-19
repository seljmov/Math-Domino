package com.example.science.Main.ui.main.Pages.rooms.Student.FoundRoom;

import android.util.Log;

import com.example.science.Main.ui.main.Pages.rooms.API.RoomContract;
import com.example.science.Main.ui.main.Pages.rooms.API.RoomRepository;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FoundRoomPresenter implements RoomContract.FoundRoomPresenter {

    private RoomContract.FoundRoom foundRoom;
    private RoomContract.Repository repository;

    public FoundRoomPresenter(RoomContract.FoundRoom foundRoom) {
        this.foundRoom = foundRoom;
        this.repository = new RoomRepository();
    }

    @Override
    public void connectButtonOnClicked(String username, String roomName, final String link) {
        repository.userConnectToRoom(username, roomName, link)
                .subscribe(new Observer<UniversalResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UniversalResponse universalResponse) {
                        String m = universalResponse.getResponse();

                        if (m.equals("cool")) {
                            String title = "Ура!";
                            String message = "Вы успешно присоединились к комнате";
                            String btnText = "Закрыть";
                            foundRoom.loadGamersList(link);
                            foundRoom.showDialog(title, message, btnText);
                            foundRoom.disableButton();

                        } else {
                            String title = "Упс...";
                            String btnText = "Закрыть";
                            foundRoom.showDialog(title, m, btnText);
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.wtf("myTag", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void checkUserConnect(String username, String link) {
        repository.isUserConnect(username, link)
                .subscribe(new Observer<UniversalResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UniversalResponse universalResponse) {
                        String m = universalResponse.getResponse();
                        if (m.equals("yes")) {
                            foundRoom.disableButton();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
