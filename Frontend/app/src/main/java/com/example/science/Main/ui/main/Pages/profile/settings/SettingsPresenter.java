package com.example.science.Main.ui.main.Pages.profile.settings;

import android.content.SharedPreferences;

import com.example.science.Main.ui.main.Pages.profile.settings.API.SettingsRepository;
import com.example.science.Main.ui.main.Pages.profile.settings.API.SettingsContract;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SettingsPresenter implements SettingsContract.SettingsPresenter {

    private SettingsContract.SettingsView settingsView;
    private SettingsContract.Repository repository;

    SettingsPresenter(SettingsContract.SettingsView settingsView) {
        this.settingsView = settingsView;
        this.repository = new SettingsRepository();
    }

    @Override
    public void logoutUserFromServer(SharedPreferences preferences) {
        String token = preferences.getString("userToken", "");
        repository.logoutUser(token)
                .subscribe(new Observer<LogoutMessage>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LogoutMessage logoutMessage) {
                        String message = logoutMessage.getMessage();
                        if (message.equals("cool")) {

                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();

                            settingsView.goToJoin();

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
