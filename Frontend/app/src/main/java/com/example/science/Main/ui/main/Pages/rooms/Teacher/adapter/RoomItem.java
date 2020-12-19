package com.example.science.Main.ui.main.Pages.rooms.Teacher.adapter;

public class RoomItem {
    private String mRoomName;
    private String mSubject;
    private String mStatus;


    public RoomItem(String roomName, String  subject, String status) {
        mRoomName = roomName;
        mSubject = subject;
        mStatus = status;
    }

    public String getmRoomName() {
        return mRoomName;
    }

    public String getmSubject() {
        return mSubject;
    }

    public String getmStatus() {
        return mStatus;
    }
}
