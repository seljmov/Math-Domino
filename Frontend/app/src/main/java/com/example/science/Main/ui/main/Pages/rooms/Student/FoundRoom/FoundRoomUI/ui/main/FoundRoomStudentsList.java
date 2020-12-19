package com.example.science.Main.ui.main.Pages.rooms.Student.FoundRoom.FoundRoomUI.ui.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.science.Main.ui.main.Pages.rooms.API.RoomContract;
import com.example.science.Main.ui.main.Pages.rooms.API.RoomRepository;
import com.example.science.Main.ui.main.Pages.rooms.Student.FoundRoom.FoundRoomPresenter;
import com.example.science.Main.ui.main.Pages.rooms.Student.FoundRoom.GamersList.GamerModel;
import com.example.science.Main.ui.main.Pages.rooms.Student.FoundRoom.GamersList.adapter.GamersAdapter;
import com.example.science.Other.DialogMessage;
import com.example.science.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FoundRoomStudentsList extends Fragment implements RoomContract.FoundRoom {

    private TextView tvGamers;
    private TextView tvGamersNull;
    private CardView btnConnectToRoom;

    private String username;
    private String roomName;
    private String status;
    private String link;

    View root;

    private RoomContract.FoundRoomPresenter foundRoomPresenter;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.found_room_students_list, container, false);

        foundRoomPresenter = new FoundRoomPresenter(this);

        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(getContext());

        username = preferences.getString("username", "unknown");
        roomName = preferences.getString("roomName", "unknown");
        link = preferences.getString("roomLink", "unknown");
        status = preferences.getString("roomStatus", "unknown");

        tvGamers = root.findViewById(R.id.tvRoomGamers);
        tvGamersNull = root.findViewById(R.id.tvRoomGamersNull);
        btnConnectToRoom = root.findViewById(R.id.btnConnectToRoom);

        // Проверка на то, присоединен ли уже к комнате пользователь
        foundRoomPresenter.checkUserConnect(username, link);

        btnConnectToRoom.setOnClickListener(v -> foundRoomPresenter
                .connectButtonOnClicked(username, roomName, link));

        loadGamersList(link);

        return root;
    }

    @Override
    public void showDialog(String title, String message, String btnText) {

        DialogMessage dialogMessage = new DialogMessage(title, message, btnText);
        dialogMessage.show(getChildFragmentManager(), "dialog message");

    }

    @Override
    public void disableButton() {

        if (status.equals("going")) {
            btnConnectToRoom.setVisibility(View.INVISIBLE);
        } else {
            btnConnectToRoom.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void loadGamersList(String link) {
        RoomContract.Repository repository = new RoomRepository();
        repository.loadRoomGamersList(link)
                .subscribe(new Observer<List<GamerModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<GamerModel> gamerModels) {

                        ArrayList<GamerModel> gamersItems = new ArrayList<>(gamerModels);

                        mRecyclerView = root.findViewById(R.id.rvRoomGamersList);
                        mRecyclerView.setHasFixedSize(true);
                        mLayoutManager = new LinearLayoutManager(getContext());
                        mAdapter = new GamersAdapter(gamersItems);

                        mRecyclerView.setLayoutManager(mLayoutManager);
                        mRecyclerView.setAdapter(mAdapter);

                        if (mAdapter.getItemCount() == 0) {
                            tvGamers.setVisibility(View.INVISIBLE);
                            mRecyclerView.setVisibility(View.INVISIBLE);
                            tvGamersNull.setVisibility(View.VISIBLE);

                        } else {
                            tvGamers.setVisibility(View.VISIBLE);
                            mRecyclerView.setVisibility(View.VISIBLE);
                            tvGamersNull.setVisibility(View.INVISIBLE);

                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        showDialog("Упс...", e.getMessage(), "Закрыть");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
