package com.example.science.Main.ui.main.Pages.rooms.Student;

import com.example.science.Main.ui.main.Pages.rooms.API.RoomContract;
import com.example.science.Main.ui.main.Pages.rooms.API.RoomRepository;
import com.example.science.Main.ui.main.Pages.rooms.Student.FoundRoom.UniversalResponse;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SearchRoomPresenter implements RoomContract.SearchRoomPresenter {

    private RoomContract.SearchRoom searchRoom;
    private RoomContract.Repository repository;

    public SearchRoomPresenter(RoomContract.SearchRoom searchRoom) {
        this.searchRoom = searchRoom;
        this.repository = new RoomRepository();
    }

    @Override
    public void searchRoomOnClicked(final String link) {
        repository.doesRoomExist(link)
                .subscribe(new Observer<UniversalResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UniversalResponse universalResponse) {
                        String m = universalResponse.getResponse();

                        if (m.equals("yes")) {
                            loadRoomData(link);

                        } else {
                            String title = "Упс...";
                            String message = "Такой комнаты не существует";
                            String btnText = "Закрыть";
                            searchRoom.showDialog(title, message, btnText);

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

    @Override
    public void loadRoomData(String link) {
        repository.searchRoomForUser(link)
                .subscribe(new Observer<ArrayList<SearchRoomResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<SearchRoomResponse> searchRoomResponses) {
                        searchRoom.successRequest(searchRoomResponses);
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
