package com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.RoomInfo.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.Dominoes.DominoesTabs.DominoActivity;
import com.example.science.R;

public class GameRoomInfo extends Fragment {

    private ImageView ivImage_Icon;
    private TextView tvRoomName;
    private TextView tvSubject;
    private CardView btnLoadTasksPages;

    private int roomId;
    private String roomName;
    private String subject;
    private String link;
    private String teacher;
    private String status;
    private String icon;
    private int stdCount;

    View root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_game_room, container, false);

        ivImage_Icon = root.findViewById(R.id.ivImage_Icon);
        tvRoomName = root.findViewById(R.id.tvRoomName);
        tvSubject = root.findViewById(R.id.tvRoomSubject);
        btnLoadTasksPages = root.findViewById(R.id.cvLoadTasksPages);
        btnLoadTasksPages.setVisibility(View.INVISIBLE);

        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(getContext());

        roomId = preferences.getInt("roomId", -1);
        roomName = preferences.getString("roomName", "unknown");
        subject = preferences.getString("roomSubject", "unknown");
        link = preferences.getString("roomLink", "unknown");
        teacher = preferences.getString("roomTeacher", "unknown");
        status = preferences.getString("roomStatus", "unknown");
        icon = preferences.getString("icon", "unknown");
        stdCount = preferences.getInt("roomStdCount", -1);

        int userRole = preferences.getInt("role", -1);

        if (userRole != 0) {
            btnLoadTasksPages.setVisibility(View.VISIBLE);
            btnLoadTasksPages.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), DominoActivity.class);
                intent.putExtra("stdCount", stdCount);
                startActivity(intent);
            });
        }

        tvRoomName.setText(roomName);
        tvSubject.setText(subject);
        setRoomIcon(icon, ivImage_Icon);

        return root;
    }

    private void setRoomIcon(String icon, ImageView img) {
        if ("room_logo_1.png".equals(icon)) img.setImageResource(R.mipmap.room_logo_1);
        else if ("room_logo_2.png".equals(icon)) img.setImageResource(R.mipmap.room_logo_2);
        else if ("room_logo_3.png".equals(icon)) img.setImageResource(R.mipmap.room_logo_3);
        else if ("room_logo_4.png".equals(icon)) img.setImageResource(R.mipmap.room_logo_4);
        else img.setImageResource(R.mipmap.room_logo_5);
    }
}
