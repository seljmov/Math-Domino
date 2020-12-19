package com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.GameActivity;

import com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.Dominoes.APIInstruments.Task;
import com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.GameActivity.API.AnswerResponse;
import com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.GameActivity.API.GameContract;
import com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.GameActivity.API.GameRepository;
import com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.GameActivity.API.TaskData;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class GamePresenter implements GameContract.GamePresenter {

    private GameContract.GameView gameView;
    private GameContract.GameRepository repository;

    GamePresenter(GameContract.GameView gameView) {
        this.gameView = gameView;
        this.repository = new GameRepository();
    }

    @Override
    public void getTaskData(String link, Task task) {
        gameView.showProgress();
        repository.getTaskFromServer(link, task.getId())
                .subscribe(new Observer<TaskData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TaskData taskData) {

                        task.setTitle(taskData.getTitle());
                        task.setTask(taskData.getTask());

                        gameView.displayTaskData(task.getTitle(), task.getTask());

                        gameView.closeProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        gameView.showDialog("Oops...", e.getMessage(), "close");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void sendAnswer(String gamer, String answer, String link,
                           int dominoId, int taskId, int taskNum) {

        repository.checkTaskAnswer(gamer, answer, link, dominoId, taskId, taskNum)
                .subscribe(new Observer<AnswerResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AnswerResponse answerResponse) {
                        String mes = answerResponse.getResponse();
                        String title, message, btnText = "Продолжить";
                        if (mes.equals("true")) {
                            title = "Ура!";
                            message = "Вы правильно решили задачу";
                        } else {
                            title = "Упс...";
                            message = "Вы неправильно решили задачу";
                        }
                        gameView.showDialog(title, message, btnText);
                        gameView.openNextTask();
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
