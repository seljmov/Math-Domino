package com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.GameActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.Dominoes.APIInstruments.Task;
import com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.GameActivity.API.GameContract;
import com.example.science.Other.DialogMessage;
import com.example.science.R;

import java.util.Timer;
import java.util.TimerTask;

import io.github.kexanie.library.MathView;

public class GameTaskActivity extends AppCompatActivity implements GameContract.GameView {

    private Task task = new Task();

    private TextView tvTimerScore;
    private TextView tvTaskCondition;
    private MathView mvFormulas;

    private EditText etAnswer;
    private CardView cvSendAnswerBtn;

    private CountDownTimer splashTimer;
    private CountDownTimer gameTimer;

    private String link;
    private String gamer;
    private int dominoId;
    private int taskId;
    private int taskNum;

    private ConstraintLayout clLoadUI;
    private ConstraintLayout clPlayUI;

    private final String TAG = "DEBUG";

    private GameContract.GamePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_task);

        presenter = new GamePresenter(this);

        clLoadUI = findViewById(R.id.clLoadUI);
        clPlayUI = findViewById(R.id.clPlayUI);
        showProgress();

        tvTimerScore = findViewById(R.id.tvTimerScore);
        tvTaskCondition = findViewById(R.id.tvTaskCondition);
        mvFormulas = findViewById(R.id.mvFormulas);

        etAnswer = findViewById(R.id.etAnswer);
        cvSendAnswerBtn = findViewById(R.id.cvSendAnswerBtn);
        cvSendAnswerBtn.setOnClickListener(v -> {
            String answer = etAnswer.getText().toString();
            presenter.sendAnswer(gamer, answer, link, dominoId, taskId, taskNum);
            gameTimer.cancel();
            etAnswer.setText("");
        });

        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        link = preferences.getString("roomLink", "unknown");
        gamer = preferences.getString("username", "unknown");

        dominoId = getIntent().getIntExtra("domino_id", -1);
        taskId = getIntent().getIntExtra("task1_id", -1);
        taskNum = getIntent().getIntExtra("task_num", -1);

        task.setId(taskId);

        presenter.getTaskData(link, task);

    }

    @Override
    public void openNextTask() {
        if (taskNum == 1) {
            showProgress();
            task.setId(++taskId);
            presenter.getTaskData(link, task);
            taskNum++;
        } else {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(() -> {
                        timer.cancel();
                        closeActivity();
                    });
                }
            }, 750, 750);
        }
    }

    private void closeActivity() {
        this.finish();
    }

    @Override
    public void displayTaskData(String title, String task) {
        tvTaskCondition.setText(title);
        mvFormulas.setText("$$\\large" + task + "$$");
    }

    @Override
    public void showDialog(String title, String message, String btnText) {
        FragmentManager manager = getSupportFragmentManager();
        DialogMessage dialogMessage = new DialogMessage(title, message, btnText);
        dialogMessage.show(manager, "dialog message");
    }

    @Override
    public void showProgress() {
        clPlayUI.setVisibility(View.INVISIBLE);
        clLoadUI.setVisibility(View.VISIBLE);
    }

    @Override
    public void closeProgress() {
        clPlayUI.setVisibility(View.VISIBLE);

        splashTimer = new CountDownTimer(900, 900) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                clLoadUI.setVisibility(View.INVISIBLE);

                gameTimer = new CountDownTimer(301000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        int sec = (int) (millisUntilFinished / 1000);
                        int min = (sec / 60);
                        sec = sec % 60;

                        if (sec < 10) { tvTimerScore.setText(min + ":0" + sec);}
                        else { tvTimerScore.setText(min + ":" + sec); }
                    }

                    @Override
                    public void onFinish() {

                    }
                }.start();

            }
        }.start();

    }

    // Убираем клавиатуру при нажатии на пустое место
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                v.clearFocus();
                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            }
        }
        return super.dispatchTouchEvent(ev);
    }


}
