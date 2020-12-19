package com.example.science.Main.ui.main.Pages.rooms.Teacher.CreateRoomPack;

import com.example.science.Main.ui.main.Pages.rooms.API.RoomContract;
import com.example.science.Main.ui.main.Pages.rooms.API.RoomRepository;
import com.example.science.Main.ui.main.Pages.rooms.Teacher.RoomModel;
import com.example.science.Main.ui.main.Pages.rooms.Teacher.adapter.ResponseMessage;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RoomPresenter implements RoomContract.RoomsPresenter {

    ArrayList<RoomModel> roomModels = new ArrayList<>();

    private RoomContract.Rooms rooms;
    private RoomContract.Repository repository;

    RoomPresenter(RoomContract.Rooms rooms) {
        this.rooms = rooms;
        this.repository = new RoomRepository();
    }

    @Override
    public void createRoomOnCLicked(String teacher, String name, String subject, int countStudent) {
        repository.createRoomToServer(teacher, name, subject, countStudent)
                .subscribe(new Observer<ResponseMessage>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseMessage responseMessage) {
                        String link = responseMessage.getLink();

                        if (link.equals("Ошибка")) {
                            String title = "Упс...";
                            String message = "Что-то пошло не так... Попробуйте снова.";
                            String btnText = "Закрыть";
                            rooms.showDialog(title, message, btnText);

                        } else {
                            String title = "Ура!";
                            String message = "Комната успешно создана. " +
                                    "Вот пригласительная ссылка вашей комнаты: "
                                    + responseMessage.getLink();
                            String btnText = "Закрыть";
                            rooms.showDialog(title, message, btnText);
                            // rooms.closePanel();

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        String title = "Упс...";
                        String message = "Что-то пошло не так... Код ошибки: " + e.getMessage();
                        String btnText = "Закрыть";
                        rooms.showDialog(title, message, btnText);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
