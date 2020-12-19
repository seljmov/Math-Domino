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
import androidx.fragment.app.Fragment;

import com.example.science.R;

public class FoundRoomInfo extends Fragment {

    private TextView tvRoomId;
    private TextView tvRoomName;
    private TextView tvSubject;
    private TextView tvLink;
    private TextView tvStatus;

    private int roomId;
    private String roomName;
    private String subject;
    private String link;
    private String teacher;
    private String status;

    View root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.found_room_info, container, false);

        tvRoomId = root.findViewById(R.id.tvRoomId_found);
        tvRoomName = root.findViewById(R.id.tvRoomName_found);
        tvSubject = root.findViewById(R.id.tvRoomSubject_found);
        tvLink = root.findViewById(R.id.tvRoomLink_found);
        tvStatus = root.findViewById(R.id.tvRoomStatus_found);


        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(getContext());

        roomId = preferences.getInt("roomId", -1);
        roomName = preferences.getString("roomName", "unknown");
        subject = preferences.getString("roomSubject", "unknown");
        link = preferences.getString("roomLink", "unknown");
        teacher = preferences.getString("roomTeacher", "unknown");
        status = preferences.getString("roomStatus", "unknown");


        tvRoomId.setText("id: " + String.valueOf(roomId));
        tvRoomName.setText("Название: " + roomName);
        tvSubject.setText("Предмет: " + subject);
        tvLink.setText("Ссылка: #" + link);
        tvStatus.setText("Статус: " + status);

        return root;
    }
}
