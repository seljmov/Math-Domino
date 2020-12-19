package com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.Dominoes.APIInstruments;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.science.Main.ui.main.Pages.rooms.API.RoomRepository;
import com.example.science.R;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Domino {

   private int id;
   private int attempt = 1;

   private ImageView imgUP;
   private ImageView imgDOWN;
   private CardView btnStart;
   private TextView btnText;

   private Task task;
   private Task task2;
   private int status;

   private static final String TAG = "DEBUG";

   private static final int FREE_MODE = 0;       // Не решается
   private static final int SOLVING_MODE = 1;    // Решается игроком
   private static final int WASTED_MODE = 2;     // Решается вами
   private static final int RESERVED = 3;        // Решена одна задача
   private static final int STOP_MODE = 4;        // Решение задач завершено

   public Domino(ArrayList<TaskModel> arrayList, int dominoId, int one, int two) {
      id = dominoId;
      task = new Task(arrayList, one);
      task2 = new Task(arrayList, two);
      status = FREE_MODE;
   }

   public Domino(int dominoId) {
       id = dominoId;
       task = new Task();
       task2 = new Task();
   }

   public void updateStatus(String link, String username, Context context, int mode) {
        RoomRepository repository = new RoomRepository();
        repository.loadDominoStatus(link, id)
                .subscribe(new Observer<DominoModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DominoModel dominoModel) {

                        String gamer = dominoModel.getGamer();
                        int domStatus = dominoModel.getStatus();

                        task.setStatus(dominoModel.getTask1Status());
                        task2.setStatus(dominoModel.getTask2Status());

                        if (task.getStatus() != Task.WAIT && task2.getStatus() != Task.WAIT) {
                            status = STOP_MODE;
                        } else if (!gamer.equals(username) && !gamer.equals("null")) {
                            status = SOLVING_MODE;
                        } else {
                            if (domStatus == 2) {
                                status = WASTED_MODE;
                            } else if (domStatus == 3) {
                                status = RESERVED;
                            } else  {
                                status = FREE_MODE;
                            }
                        }
                        setBones(context);
                        if (mode == 0) {
                            setButtons(context);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
   }

   public void initDominoUI(ImageView imgUP, ImageView imgDOWN, CardView btnStart, TextView btnText) {
       this.imgUP = imgUP;
       this.imgDOWN = imgDOWN;
       this.btnStart = btnStart;
       this.btnText = btnText;
   }

   public void initDominoUI(ImageView imgUP, ImageView imgDOWN) {
        this.imgUP = imgUP;
        this.imgDOWN = imgDOWN;
   }

   // Инициализация костяшек домино с соответствующим цветом
   private void setBones(Context context) {
       switch (task.getStatus()) {
           case Task.WAIT:
               imgUP.setImageBitmap(ColoredNumbers.getInstance().numberWhite(context, task.getPoints()));
               break;
           case Task.NOT_DECIDED:
               imgUP.setImageBitmap(ColoredNumbers.getInstance().numberRed(context, task.getPoints()));
               break;
           case Task.DECIDED:
               imgUP.setImageBitmap(ColoredNumbers.getInstance().numberGreen(context, task.getPoints()));
               break;
       }
       switch (task2.getStatus()) {
           case Task.WAIT:
               imgDOWN.setImageBitmap(ColoredNumbers.getInstance().numberWhite(context, task2.getPoints()));
               break;
           case Task.NOT_DECIDED:
               imgDOWN.setImageBitmap(ColoredNumbers.getInstance().numberRed(context, task2.getPoints()));
               break;
           case Task.DECIDED:
               imgDOWN.setImageBitmap(ColoredNumbers.getInstance().numberGreen(context, task2.getPoints()));
               break;
       }
   }

   // Инициализация кнопок домино с соответсвующим текстом и цветом
   private void setButtons(Context context) {
       switch (status) {
           case FREE_MODE:
               btnStart.setCardBackgroundColor(context.getResources().getColorStateList(R.color.domino_pink));
               btnText.setText("Начать\nрешать");
               break;
           case Domino.SOLVING_MODE:
               btnStart.setCardBackgroundColor(context.getResources().getColorStateList(R.color.domino_dark));
               btnText.setText("Решается\nигроком");
               btnStart.setClickable(false);
               break;
           case Domino.WASTED_MODE:
               btnStart.setCardBackgroundColor(context.getResources().getColorStateList(R.color.customWhite));
               btnText.setTextColor(context.getResources().getColorStateList(R.color.lightBlack));
               btnText.setText("Начать\nрешать");
               break;
           case Domino.RESERVED:
               if (task.getStatus() == Task.NOT_DECIDED) {
                   btnStart.setCardBackgroundColor(context.getResources().getColorStateList(R.color.domino_red));
               } else {
                   btnStart.setCardBackgroundColor(context.getResources().getColorStateList(R.color.domino_green));
               }
               btnText.setTextColor(context.getResources().getColorStateList(R.color.customWhite));
               btnText.setText("Продолжить\nрешать");
               break;
           case Domino.STOP_MODE:
               if (task2.getStatus() == Task.NOT_DECIDED) {
                   btnStart.setCardBackgroundColor(context.getResources().getColorStateList(R.color.domino_red));
               } else {
                   btnStart.setCardBackgroundColor(context.getResources().getColorStateList(R.color.domino_green));
               }
               btnStart.setClickable(false);
               btnText.setText("Решение\nЗавершено");
               btnText.setTextColor(context.getResources().getColorStateList(R.color.customWhite));
               break;
       }
       btnStart.setVisibility(View.VISIBLE);
   }

   public Task getTask() {
      return task;
   }

   public Task getTask2() {
      return task2;
   }

   public int getStatus() {
      return status;
   }

   public void setStatus(int status) {
      this.status = status;
   }

   public int getId() {
      return id;
   }

   public int getAttempt() {
      return attempt;
   }
}
