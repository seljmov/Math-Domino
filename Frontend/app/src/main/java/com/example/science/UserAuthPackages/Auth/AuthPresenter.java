package com.example.science.UserAuthPackages.Auth;

import android.content.SharedPreferences;

import com.example.science.UserAuthPackages.API.MainContract;
import com.example.science.UserAuthPackages.API.Repository;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AuthPresenter implements MainContract.AuthPresenter {

    private Disposable disposable;

    private MainContract.Auth auth;
    private MainContract.Repository repository;

    AuthPresenter(MainContract.Auth auth) {
        this.auth = auth;
        this.repository = new Repository();
    }

    @Override
    public void checkUserAuth(final SharedPreferences preferences) {
        String token = preferences.getString("userToken", "");
        repository.checkUserToken(token)
                .subscribe(new Observer<AuthMessage>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(AuthMessage authMessage) {
                        String message = authMessage.getAuthMessage();
                        if (!message.equals("bad")) {
                            SharedPreferences.Editor editor = preferences.edit();
                            int userId = Integer.parseInt(message);
                            editor.putInt("userId", userId);
                            editor.apply();

                            auth.userAuth();
                        } else {
                            auth.userNotAuth();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        //disposable.dispose();
    }
}
