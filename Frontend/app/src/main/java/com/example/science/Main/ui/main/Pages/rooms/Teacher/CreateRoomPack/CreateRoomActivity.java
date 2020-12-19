package com.example.science.Main.ui.main.Pages.rooms.Teacher.CreateRoomPack;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;

import com.example.science.Other.DialogMessage;
import com.example.science.Main.ui.main.Pages.rooms.API.RoomContract;
import com.example.science.R;

public class CreateRoomActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener, RoomContract.Rooms {

    private EditText etRoomName;
    private CardView btnCreateRoom;
    private Spinner subjectsSpinner;
    private SeekBar sbGamerCount;
    private TextView tvCount;
    private EditText etDate;
    private CardView cvBtnCreateBack;

    public String teacherName;
    public String roomName;
    public String subjectName;
    public int countStudents = 4;

    private View view;

    private RoomContract.RoomsPresenter roomsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);

        roomsPresenter = new RoomPresenter(this);

        view = findViewById(R.id.createRoomLayout);
        etRoomName = findViewById(R.id.etRoomName);
        cvBtnCreateBack = findViewById(R.id.cvBtnCreateBack);
        sbGamerCount = findViewById(R.id.sbGamerCount);
        sbGamerCount.setMax(4);
        tvCount = findViewById(R.id.tvCount);
        tvCount.setText("4");
        sbGamerCount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvCount.setText(String.valueOf(progress+2));
                countStudents = progress+2;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btnCreateRoom = findViewById(R.id.cvBtnCreateRoom);
        subjectsSpinner = findViewById(R.id.subjectsSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                        R.layout.custom_spinner,
                        getResources().getStringArray(R.array.subjects)
        );
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        subjectsSpinner.setAdapter(adapter);
        subjectsSpinner.setOnItemSelectedListener(this);

        btnCreateRoom.setOnClickListener(v -> {
            SharedPreferences preferences = PreferenceManager
                    .getDefaultSharedPreferences(CreateRoomActivity.this);

            teacherName = preferences.getString("username", "unknown");

            roomName = etRoomName.getText().toString();

            roomsPresenter.createRoomOnCLicked(teacherName, roomName, subjectName, countStudents);

        });

        cvBtnCreateBack.setOnClickListener(v -> finish());

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        subjectName = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void showDialog(String title, String message, String btnText) {
        FragmentManager manager = getSupportFragmentManager();
        DialogMessage dialogMessage = new DialogMessage(title, message, btnText);
        dialogMessage.show(manager, "dialog message");
    }

    @Override
    public void closePanel() {
        finish();
    }
}
