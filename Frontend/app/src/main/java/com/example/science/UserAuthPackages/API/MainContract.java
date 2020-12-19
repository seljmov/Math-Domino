package com.example.science.UserAuthPackages.API;

import android.content.SharedPreferences;

import com.example.science.UserAuthPackages.Auth.AuthMessage;
import com.example.science.UserAuthPackages.Login.LoginMessage;
import com.example.science.UserAuthPackages.Login.UserData;
import com.example.science.UserAuthPackages.Reg.RegToken;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public interface MainContract {

    interface Auth {
        void userAuth();
        void userNotAuth();
    }

    interface AuthPresenter {
        void checkUserAuth(SharedPreferences preferences);
    }

    interface Login {
        void userLogin();
        void showDialog(String title, String message, String btnText);
        void showSnackBar(String message);
    }

    interface LoginPresenter {
        boolean matchesData(String email,
                            String password);
        void loginButtonOnClicked(String email,
                                  String password,
                                  SharedPreferences preferences);
        void loadUserData(SharedPreferences preferences);
    }

    interface Register {
        void proceedButtonOnClick(String avatar, String name, String surname,
                                  String username, int role);
        void setButtons();
        void showSnackBar(String message);
    }

    interface RegisterProceed {
        void userIsReg();
        void showSnackBar(String message);
    }

    interface RegPresenter {
        void checkUsername(String avatar, String name,
                           String surname, String username, int role);
        boolean matchesData(String name, String surname, String username, int role);
    }

    interface RegProceedPresenter {
        boolean matchesData(String email, String password, String confPassword);
        void regButtonOnClicked(SharedPreferences preferences, String avatar,
                                String name, String surname, String username,
                                String email, String password, int role);
    }


    interface Repository {
        Observable<LoginMessage> loginUserToApp(final String email,
                                                final String password);

        Observable<UserData> loadDataAboutUser(final int userId);

        Observable<ResponseBody> isUsername(final String username);

        Observable<RegToken> regUserToServer(final String avatar, final String name,
                                             final String surname, final String username,
                                             final String email, final String password,
                                             final int role);

        Observable<AuthMessage> checkUserToken(final String token);

    }

}
