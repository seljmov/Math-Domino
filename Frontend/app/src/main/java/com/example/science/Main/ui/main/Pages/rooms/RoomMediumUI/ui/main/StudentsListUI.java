package com.example.science.Main.ui.main.Pages.rooms.RoomMediumUI.ui.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.science.Main.ui.main.Pages.rooms.API.RoomContract;
import com.example.science.Main.ui.main.Pages.rooms.API.RoomRepository;
import com.example.science.Main.ui.main.Pages.rooms.Student.FoundRoom.GamersList.GamerModel;
import com.example.science.Main.ui.main.Pages.rooms.Student.FoundRoom.GamersList.adapter.GamersAdapter;
import com.example.science.Other.DialogMessage;
import com.example.science.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class StudentsListUI extends Fragment {

    private TextView tvGamers;
    private TextView tvGamersNull;

    private String link;

    View root;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.medium_students_list, container, false);

        tvGamers = root.findViewById(R.id.tvRoomGamers);
        tvGamersNull = root.findViewById(R.id.tvRoomGamersNull);

        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(getContext());

        link = preferences.getString("roomLink", "unknown");

        loadGamersList(link, root);

        return root;
    }

    private void showDialog(String title, String message, String btnText) {

        DialogMessage dialogMessage = new DialogMessage(title, message, btnText);
        dialogMessage.show(getChildFragmentManager(), "dialog message");

    }

    private void loadGamersList(String link, View root) {
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
