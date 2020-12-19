package com.example.science.UserAuthPackages.Login;

import android.content.SharedPreferences;

import com.example.science.UserAuthPackages.API.MainContract;
import com.example.science.UserAuthPackages.API.Repository;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginPresenter implements MainContract.LoginPresenter {

    private Disposable disposable;

    private MainContract.Login login;
    private MainContract.Repository repository;

    LoginPresenter(MainContract.Login login) {
        this.login = login;
        this.repository = new Repository();
    }

    @Override
    public boolean matchesData(String email, String password) {
        return false;
    }

    @Override
    public void loginButtonOnClicked(String email,
                                     String password,
                                     final SharedPreferences preferences) {

        repository.loginUserToApp(email, password)
                .subscribe(new Observer<LoginMessage>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginMessage loginMessage) {
                        String mes = loginMessage.getMessage();
                        int userId = loginMessage.getUserId();
                        if (!mes.equals("Пользователя с таким Email не существует!") &
                                !mes.equals("Вы ввели неправильный пароль!")) {

                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("userToken", mes);
                            editor.putInt("userId", userId);
                            editor.apply();

                            loadUserData(preferences);

                            login.userLogin();

                        } else {
                            String title = "Упс...";
                            String btnText = "Закрыть";
                            login.showDialog(title, mes, btnText);
                            // login.showError(mes);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        login.showSnackBar(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        // disposable.dispose();
    }

    @Override
    public void loadUserData(final SharedPreferences preferences) {
        final int userId = preferences.getInt("userId", 0);
        repository.loadDataAboutUser(userId)
                .subscribe(new Observer<UserData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(UserData userData) {
                        String name = userData.getName();
                        String surname = userData.getSurname();
                        String username = userData.getUsername();
                        String email = userData.getEmail();
                        String avatar = userData.getAvatar();
                        int role = userData.getRole();

                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("name", name);
                        editor.putString("surname", surname);
                        editor.putString("username", username);
                        editor.putString("email", email);
                        editor.putString("avatar", avatar);
                        editor.putInt("role", role);
                        editor.apply();
                    }

                    @Override
                    public void onError(Throwable e) {
                        login.showSnackBar(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
