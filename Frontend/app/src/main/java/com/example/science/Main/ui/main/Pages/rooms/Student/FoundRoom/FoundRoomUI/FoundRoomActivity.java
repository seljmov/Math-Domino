package com.example.science.Main.ui.main.Pages.rooms.Student.FoundRoom.FoundRoomUI;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.science.Main.ui.main.Pages.rooms.Student.SearchRoomResponse;
import com.example.science.R;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.preference.PreferenceManager;

import com.example.science.Main.ui.main.Pages.rooms.Student.FoundRoom.FoundRoomUI.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;

public class FoundRoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_room);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        ArrayList<SearchRoomResponse> arrayList = (ArrayList<SearchRoomResponse>) getIntent().getSerializableExtra("roomData");

        int id = getIntent().getIntExtra("roomId", -1);

        int roomId = arrayList.get(0).getId();
        String roomName = arrayList.get(0).getRoomName();
        String subject = arrayList.get(0).getSubject();
        String link = arrayList.get(0).getLink();
        String teacher = arrayList.get(0).getTeacher();
        String status = arrayList.get(0).getStatus();

        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("roomId", roomId);
        editor.putString("roomName", roomName);
        editor.putString("roomSubject", subject);
        editor.putString("roomLink", link);
        editor.putString("roomTeacher", teacher);
        editor.putString("roomStatus", status);
        editor.apply();

    }
}