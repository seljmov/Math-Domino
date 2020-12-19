package com.example.science.UserAuthPackages.Reg;

import android.content.SharedPreferences;

import com.example.science.UserAuthPackages.API.MainContract;
import com.example.science.UserAuthPackages.API.Repository;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public class CommonRegPresenter {

    static class Reg implements MainContract.RegPresenter {

        private MainContract.Register register;
        private MainContract.Repository repository;

        Reg(MainContract.Register register) {
            this.register = register;
            this.repository = new Repository();
        }

        @Override
        public void checkUsername(final String avatar, final String name,
                                  final String surname, final String username, final int role) {

            repository.isUsername(username)
                    .subscribe(new Observer<ResponseBody>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ResponseBody responseBody) {
                            try {

                                String result = responseBody.string();
                                if (!result.equals("Пользователь с таким Никнеймом уже существует!")) {
                                    register.proceedButtonOnClick(avatar, name,
                                            surname, username, role);
                                } else {

                                    register.showSnackBar(result);

                                }

                            } catch (IOException e) {
                                e.printStackTrace();
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

        @Override
        public boolean matchesData(String name, String surname, String username, int role) {
            return false;
        }
    }

    static class RegProceed implements MainContract.RegProceedPresenter {

        private MainContract.RegisterProceed register;
        private MainContract.Repository repository;

        RegProceed(MainContract.RegisterProceed register) {
            this.register = register;
            this.repository = new Repository();
        }

        @Override
        public void regButtonOnClicked(final SharedPreferences preferences, final String avatar,
                                       final String name, final String surname,
                                       final String username, String email, String password,
                                       final int role) {

            repository.regUserToServer(avatar, name, surname, username, email, password, role)
                    .subscribe(new Observer<RegToken>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(RegToken regToken) {
                            String myToken = regToken.getToken();
                            if (!myToken.equals("Пользователь с таким email уже существует!")) {

                                int userId = regToken.getUserId();
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("avatar", avatar);
                                editor.putString("name", name);
                                editor.putString("surname", surname);
                                editor.putString("username", username);
                                editor.putString("userToken", myToken);
                                editor.putInt("userId", userId);
                                editor.putInt("role", role);
                                editor.apply();

                                register.userIsReg();
                            } else {
                                register.showSnackBar(myToken);
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

        @Override
        public boolean matchesData(String email, String password, String confPassword) {
            return false;
        }
    }

}
