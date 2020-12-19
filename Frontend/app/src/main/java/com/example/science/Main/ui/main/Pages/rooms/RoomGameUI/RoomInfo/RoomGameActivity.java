package com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.RoomInfo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.RoomInfo.main.SectionsPagerAdapter;
import com.example.science.Main.ui.main.Pages.rooms.Teacher.RoomModel;
import com.example.science.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class RoomGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_game);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        ArrayList<RoomModel> arrayList = (ArrayList<RoomModel>) getIntent().getSerializableExtra("roomData");

        int id = getIntent().getIntExtra("roomId", -1);

        int roomId = arrayList.get(id).getId();
        String roomName = arrayList.get(id).getRoomName();
        String icon = arrayList.get(id).getIcon();
        String subject = arrayList.get(id).getSubject();
        String link = arrayList.get(id).getLink();
        String teacher = arrayList.get(id).getTeacher();
        String status = arrayList.get(id).getStatus();
        int stdCount = Integer.parseInt(arrayList.get(id).getStudentsCount());

        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("roomId", roomId);
        editor.putString("roomName", roomName);
        editor.putString("icon", icon);
        editor.putString("roomSubject", subject);
        editor.putString("roomLink", link);
        editor.putString("roomTeacher", teacher);
        editor.putString("roomStatus", status);
        editor.putInt("roomStdCount", stdCount);
        editor.apply();
    }
}