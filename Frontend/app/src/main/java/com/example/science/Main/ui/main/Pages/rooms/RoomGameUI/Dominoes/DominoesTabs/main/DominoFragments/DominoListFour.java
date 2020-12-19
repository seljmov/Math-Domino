package com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.Dominoes.DominoesTabs.main.DominoFragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.science.Main.ui.main.Pages.rooms.API.RoomRepository;
import com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.Dominoes.APIInstruments.ClickDominoResponse;
import com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.Dominoes.APIInstruments.Domino;
import com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.Dominoes.APIInstruments.TaskModel;
import com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.GameActivity.GameTaskActivity;
import com.example.science.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DominoListFour extends Fragment {

    private Domino domino1;
    private Domino domino2;

    private ImageView img11;
    private ImageView img12;
    private ImageView img21;
    private ImageView img22;

    private CardView button1;
    private CardView button2;

    private TextView textView1;
    private TextView textView2;

    ArrayList<TaskModel> arrayList;
    View view;

    private static final String TAG = "ERROR";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_with_two_domino, container, false);

        img11 = view.findViewById(R.id.Domino11);
        img12 = view.findViewById(R.id.Domino12);
        img21 = view.findViewById(R.id.Domino21);
        img22 = view.findViewById(R.id.Domino22);

        button1 = view.findViewById(R.id.begin1);
        button2 = view.findViewById(R.id.begin2);

        textView1 = button1.findViewById(R.id.text_button_1);
        textView2 = button2.findViewById(R.id.text_button_2);

        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(getContext());

        String link = preferences.getString("roomLink", "unknown");
        String gamer = preferences.getString("username", "unknown");

        loadTasks(link, gamer);

        // Observable.just(true).repeatWhen(t->t.delay(1, TimeUnit.SECONDS)).subscribe(b->{loadTasks(link, gamer);});

        button1.setOnClickListener(v -> clickOnDominoButton(link,  gamer, 6, 12));

        button2.setOnClickListener(v -> clickOnDominoButton(link,  gamer, 7, 14));

        return view;
    }

    // Загрузка задач с сервера и инициализация доминошек
    private void loadTasks(String link, String gamer) {
        RoomRepository repository = new RoomRepository();
        repository.loadRoomTasksList(link)
                .subscribe(new Observer<List<TaskModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<TaskModel> taskModels) {

                        arrayList = new ArrayList<>(taskModels);

                        domino1 = new Domino(arrayList,6, 12, 13);
                        domino2 = new Domino(arrayList,7, 14, 15);

                        domino1.initDominoUI(img11, img12, button1, textView1);
                        domino2.initDominoUI(img21, img22, button2, textView2);

                        domino1.updateStatus(link, gamer, getContext(), 0);
                        domino2.updateStatus(link, gamer, getContext(), 0);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void clickOnDominoButton(String link, String gamer, int domino_id, int task_id) {
        RoomRepository repository = new RoomRepository();
        repository.changeDominoStatusFromServer(link, domino_id, gamer)
                .subscribe(new Observer<ClickDominoResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClickDominoResponse clickDominoResponse) {
                        String response = clickDominoResponse.getResponse();

                        // Если все плохо, то вывести соответствующее сообщение
                        if (response.equals("bad")) {

                            Log.d(TAG, "onNext: Что-то пошло не так..." );
                            // Dialog message

                        } else {

                            // Если все окей, то открыть игровую активность
                            // И передать в нее id домишки и первой задачи
                            Intent intent = new Intent(getActivity(), GameTaskActivity.class);
                            intent.putExtra("domino_id", domino_id);
                            intent.putExtra("task1_id", task_id);
                            startActivity(intent);
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
}
