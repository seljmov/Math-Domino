package com.example.science.Main.ui.main.Pages.profile.settings.API;

import android.content.SharedPreferences;

import com.example.science.Main.ui.main.Pages.profile.settings.LogoutMessage;

import io.reactivex.Observable;

public interface SettingsContract {

    interface SettingsView {
        void goToJoin();
    }

    interface SettingsPresenter {
        void logoutUserFromServer(final SharedPreferences preferences);
    }

    interface Repository {
        Observable<LogoutMessage> logoutUser(final String token);
    }
}