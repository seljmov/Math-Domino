package com.example.science.UserAuthPackages.Auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.science.Main.MenuActivity;
import com.example.science.Other.DialogMessage;
import com.example.science.R;
import com.example.science.UserAuthPackages.API.MainContract;
import com.example.science.UserAuthPackages.JoinActivity;

import java.util.Timer;
import java.util.TimerTask;

public class AuthActivity extends AppCompatActivity implements MainContract.Auth {

    private Timer timer = new Timer();

    private SharedPreferences preferences;

    private MainContract.AuthPresenter authPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        authPresenter = new AuthPresenter(this);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (!hasConnection(getApplicationContext())) {
            String title = "Упс...";
            String message = "Отсутствует соединение с интернетом...";
            String btnText = "Понятно";
            showDialog(title, message, btnText);
        } else {
            // Задержка на экране на указаное время
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(() -> {
                        timer.cancel();
                        authPresenter.checkUserAuth(preferences);
                    });
                }
            }, 1000, 1000);
        }
    }

    @Override
    public void userAuth() {
        Intent intent = new Intent(AuthActivity.this, MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void userNotAuth() {
        Intent intent = new Intent(AuthActivity.this, JoinActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void showDialog(String title, String message, String btnText) {
        FragmentManager manager = getSupportFragmentManager();
        DialogMessage dialogMessage = new DialogMessage(title, message, btnText, AuthActivity.this, true);
        dialogMessage.show(manager, "dialog message");
    }

    public static boolean hasConnection(final Context context)
    {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        return false;
    }
}
