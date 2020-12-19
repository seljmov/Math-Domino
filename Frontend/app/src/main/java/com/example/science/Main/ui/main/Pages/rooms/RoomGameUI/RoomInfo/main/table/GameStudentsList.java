package com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.RoomInfo.main.table;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.science.Main.ui.main.Pages.rooms.API.RoomRepository;
import com.example.science.Other.DialogMessage;
import com.example.science.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class GameStudentsList extends Fragment {

    private String link;

    View root;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_game_table, container, false);

        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(getContext());

        link = preferences.getString("roomLink", "unknown");

        loadTableList(link);

        return root;
    }

    private void showDialog(String title, String message, String btnText) {

        DialogMessage dialogMessage = new DialogMessage(title, message, btnText);
        dialogMessage.show(getChildFragmentManager(), "dialog message");

    }

    private void loadTableList(String link) {
        RoomRepository repository = new RoomRepository();
        repository.loadRoomTableGamersList(link)
                .subscribe(new Observer<List<TableListModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<TableListModel> tableListModels) {

                        ArrayList<TableListModel> arrayList = new ArrayList<>(tableListModels);

                        mRecyclerView = root.findViewById(R.id.rvRoomGamersList);
                        mRecyclerView.setHasFixedSize(true);
                        mLayoutManager = new LinearLayoutManager(getContext());
                        mAdapter = new TableListAdapter(arrayList);

                        mRecyclerView.setLayoutManager(mLayoutManager);
                        mRecyclerView.setAdapter(mAdapter);
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
