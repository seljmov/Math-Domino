package com.example.science.Main.ui.main.Pages.rooms;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.science.Main.ui.main.Pages.rooms.API.RoomContract;
import com.example.science.Main.ui.main.Pages.rooms.API.RoomRepository;
import com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.RoomInfo.RoomGameActivity;
import com.example.science.Main.ui.main.Pages.rooms.RoomMediumUI.RoomMediumActivity;
import com.example.science.Main.ui.main.Pages.rooms.Student.FoundRoom.FoundRoomUI.FoundRoomActivity;
import com.example.science.Main.ui.main.Pages.rooms.Student.SearchRoomPresenter;
import com.example.science.Main.ui.main.Pages.rooms.Student.SearchRoomResponse;
import com.example.science.Main.ui.main.Pages.rooms.Teacher.CreateRoomPack.CreateRoomActivity;
import com.example.science.Main.ui.main.Pages.rooms.Teacher.RoomModel;
import com.example.science.Main.ui.main.Pages.rooms.Teacher.adapter.RoomsAdapter;
import com.example.science.Other.DialogMessage;
import com.example.science.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RoomsFragment extends Fragment {

    private EditText etSearchLink;
    private CardView btnSearchRoom;
    private TextView tvMyRoom;
    private ConstraintLayout clNullRoomTexts;
    private CardView cvBtnCreateRoom;

    private String link;
    private View root;
    private String gamer;
    private String teacherUsername;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    SharedPreferences preferences;
    private RoomContract.SearchRoomPresenter presenter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

         preferences = PreferenceManager
                .getDefaultSharedPreferences(getContext());

        int role = preferences.getInt("role",666);

        if (role == 0) {
            root = inflater.inflate(R.layout.fragment_rooms_teacher, container, false);
            Teacher teacher = new Teacher();

             // Observable.just(true).repeatWhen(t->t.delay(1, TimeUnit.SECONDS)).subscribe(b->{teacher.loadRoomsList(teacherUsername, root, teacher);});

        } else if (role == 1) {
            root = inflater.inflate(R.layout.fragment_rooms_student, container, false);
            Student student = new Student();

            // Observable.just(true).repeatWhen(t->t.delay(1, TimeUnit.SECONDS)).subscribe(b->{student.loadMyRoomsList(teacherUsername, root, student);});

        }

        return root;
    }

    class Teacher implements RoomsAdapter.OnRoomListener {

        Teacher() {
            teacherUsername = preferences.getString("username", "unknown");
            tvMyRoom = root.findViewById(R.id.tvMyRoom_tch);
            tvMyRoom.setVisibility(View.INVISIBLE);

            clNullRoomTexts = root.findViewById(R.id.clNullRoomTexts_tch);
            clNullRoomTexts.setVisibility(View.INVISIBLE);

            cvBtnCreateRoom = root.findViewById(R.id.cvBtnCreateRoom);
            cvBtnCreateRoom.setOnClickListener(v -> {

                if (mAdapter.getItemCount() < 5) {
                    Intent intent = new Intent(getActivity(), CreateRoomActivity.class);
                    startActivity(intent);

                } else {
                    String title = "Упс...";
                    String message = "Вы создали максимальное количество комнат";
                    String btnText = "Закрыть";
                    DialogMessage dialogMessage = new DialogMessage(title, message, btnText);
                    dialogMessage.show(getFragmentManager(), "dialog message");
                }

            });
            loadRoomsList(teacherUsername, root, this);
        }

        @Override
        public void onRoomClick(ArrayList<RoomModel> arrayList, int position) {

            String status = arrayList.get(position).getStatus();
            Intent intent = null;
            if (status.equals("notStarted")) {
                intent = new Intent(getActivity(), RoomMediumActivity.class);
            } else {
                intent = new Intent(getActivity(), RoomGameActivity.class);
            }
            intent.putExtra("roomData", arrayList);
            intent.putExtra("roomId", position);
            startActivity(intent);
        }

        void loadRoomsList(String teacher, final View root,
                           final RoomsAdapter.OnRoomListener onRoomListener) {
            RoomContract.Repository repository = new RoomRepository();
            repository.loadRoomsListFromServer(teacher)
                    .subscribe(new Observer<List<RoomModel>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(List<RoomModel> roomModels) {

                            ArrayList<RoomModel> roomItems = new ArrayList<>(roomModels);

                            mRecyclerView = root.findViewById(R.id.rvRoomList);
                            mRecyclerView.setHasFixedSize(true);
                            mLayoutManager = new LinearLayoutManager(getActivity());
                            mAdapter = new RoomsAdapter(roomItems, onRoomListener);

                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mRecyclerView.setAdapter(mAdapter);

                            if (mAdapter.getItemCount() == 0) {
                                clNullRoomTexts.setVisibility(View.VISIBLE);
                                tvMyRoom.setVisibility(View.INVISIBLE);
                                mRecyclerView.setVisibility(View.INVISIBLE);
                            } else {
                                tvMyRoom.setVisibility(View.VISIBLE);
                                mRecyclerView.setVisibility(View.VISIBLE);
                                clNullRoomTexts.setVisibility(View.INVISIBLE);

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

    }

    class Student implements RoomContract.SearchRoom, RoomsAdapter.OnRoomListener {

        Student() {
            gamer = preferences.getString("username", "unknown");
            presenter = new SearchRoomPresenter(this);

            tvMyRoom = root.findViewById(R.id.tvMyRoom);
            tvMyRoom.setVisibility(View.INVISIBLE);

            clNullRoomTexts = root.findViewById(R.id.clNullRoomTexts);
            clNullRoomTexts.setVisibility(View.INVISIBLE);

            etSearchLink = root.findViewById(R.id.etSearchLink);
            btnSearchRoom = root.findViewById(R.id.btnSearchRoom);

            btnSearchRoom.setOnClickListener(v -> {

                link = etSearchLink.getText().toString();

                presenter.searchRoomOnClicked(link);

            });
            loadMyRoomsList(gamer, root, this);
        }

        @Override
        public void successRequest(ArrayList<SearchRoomResponse> list) {

            Intent intent = new Intent(getActivity(), FoundRoomActivity.class);
            intent.putExtra("roomData", list);
            intent.putExtra("roomId", 0);
            startActivity(intent);

        }

        @Override
        public void showDialog(String title, String message, String btnText) {
            DialogMessage dialogMessage = new DialogMessage(title, message, btnText);
            dialogMessage.show(getFragmentManager(), "dialog message");
        }

        @Override
        public void onRoomClick(ArrayList<RoomModel> arrayList, int position) {

            String status = arrayList.get(position).getStatus();
            Intent intent = null;
            if (status.equals("notStarted")) {
                intent = new Intent(getActivity(), RoomMediumActivity.class);
            } else {
                intent = new Intent(getActivity(), RoomGameActivity.class);
            }
            intent.putExtra("roomData", arrayList);
            intent.putExtra("roomId", position);
            startActivity(intent);
        }

        void loadMyRoomsList(String gamer, final View root,
                             final RoomsAdapter.OnRoomListener onRoomListener) {
            RoomContract.Repository repository = new RoomRepository();
            repository.loadMyRoomsListFromServer(gamer)
                    .subscribe(new Observer<List<RoomModel>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(List<RoomModel> roomModels) {

                            ArrayList<RoomModel> roomItems = new ArrayList<>(roomModels);

                            mRecyclerView = root.findViewById(R.id.rvMyRoomList);
                            mRecyclerView.setHasFixedSize(true);
                            mLayoutManager = new LinearLayoutManager(getActivity());
                            mAdapter = new RoomsAdapter(roomItems, onRoomListener);

                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mRecyclerView.setAdapter(mAdapter);

                            if (mAdapter.getItemCount() == 0) {
                                clNullRoomTexts.setVisibility(View.VISIBLE);
                                tvMyRoom.setVisibility(View.INVISIBLE);
                                mRecyclerView.setVisibility(View.INVISIBLE);
                            } else {
                                tvMyRoom.setVisibility(View.VISIBLE);
                                mRecyclerView.setVisibility(View.VISIBLE);
                                clNullRoomTexts.setVisibility(View.INVISIBLE);

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
    }

}