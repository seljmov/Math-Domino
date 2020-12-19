package com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.GameActivity.API;

import com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.Dominoes.APIInstruments.Task;

import io.reactivex.Observable;

public interface GameContract {

    // View
    interface GameView {
        void displayTaskData(String title, String task);
        void showDialog(String title, String message, String btnText);
        void showProgress();
        void closeProgress();
        void openNextTask();
    }

    // Presenter
    interface GamePresenter {
        void getTaskData(String link, Task task);
        void sendAnswer(String gamer, String answer, String link,
                        int dominoId, int taskId, int taskNum);
    }

    // Repository
    interface GameRepository {
        Observable<TaskData> getTaskFromServer(String link, int taskId);
        Observable<AnswerResponse> checkTaskAnswer(String gamer, String answer, String link,
                                                   int dominoId, int taskId, int taskNum);
    }
}
