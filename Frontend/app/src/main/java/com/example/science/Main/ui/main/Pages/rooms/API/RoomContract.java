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

import io.reactivex.Observable;

public interface RoomContract {

    interface FoundRoom {
        void loadGamersList(String link);
        void showDialog(String title, String message, String btnText);
        void disableButton();
    }

    interface FoundRoomPresenter {
        void connectButtonOnClicked(String username, String roomName, String link);
        void checkUserConnect(String username, String link);
    }

    interface SearchRoom {
        void successRequest(ArrayList<SearchRoomResponse> list);
        void showDialog(String title, String message, String btnText);
    }

    interface SearchRoomPresenter {
        void searchRoomOnClicked(String link);
        void loadRoomData(String link);
    }

    interface Rooms {
        void showDialog(String title, String message, String btnText);
        void closePanel();
    }

    interface RoomsPresenter {
        void createRoomOnCLicked(String teacher, String name,
                                 String subject, int countStudent);

    }

    interface Repository {

        // Преподские запрсосы
        Observable<ResponseMessage> createRoomToServer(final String teacher, final String name,
                                                       final String subject, final int countStudent);

        Observable<List<RoomModel>> loadRoomsListFromServer(final String teacher);

        // Студенческие запросы
        Observable<List<RoomModel>> loadMyRoomsListFromServer(final String gamer);

        Observable<ArrayList<SearchRoomResponse>> searchRoomForUser(final String link);

        Observable<UniversalResponse> doesRoomExist(final String link);

        Observable<UniversalResponse> userConnectToRoom(final String username,
                                                        final String roomName, final String link);

        Observable<UniversalResponse> isUserConnect(final String username, final String link);

        // Комнатные запросы
        Observable<List<GamerModel>> loadRoomGamersList(final String link);

        Observable<List<TableListModel>> loadRoomTableGamersList(final String link);

        Observable<List<TaskModel>> loadRoomTasksList(final String link);

        Observable<DominoModel> loadDominoStatus(final String link, final int id);

        Observable<ClickDominoResponse> changeDominoStatusFromServer(final String link,
                                                        final int id,
                                                        final String gamer);

    }
}
