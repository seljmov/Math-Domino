package com.example.science.UserAuthPackages.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.science.Main.MenuActivity;
import com.example.science.Other.DialogMessage;
import com.example.science.R;
import com.example.science.UserAuthPackages.API.MainContract;
import com.example.science.UserAuthPackages.JoinActivity;
import com.example.science.UserAuthPackages.Reg.RegActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends AppCompatActivity implements MainContract.Login {

    private EditText etEmail;
    private EditText etPassword;
    private CardView btnLogin;
    private CardView btnBack;

    private Timer timer = new Timer();

    private SharedPreferences preferences;

    View view;

    private MainContract.LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenter(this);
        preferences = PreferenceManager
                .getDefaultSharedPreferences(LoginActivity.this);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.cvGoLoginButton);
        btnBack = findViewById(R.id.cvLoginBackToJoin);
        view = findViewById(R.id.loginLayout);

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            loginPresenter.loginButtonOnClicked(email, password, preferences);
        });

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

    }

    // Убираем клавиатуру при нажатии на пустое место
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                v.clearFocus();
                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void userLogin() {
        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void showDialog(String title, String message, String btnText) {
        FragmentManager manager = getSupportFragmentManager();
        DialogMessage dialogMessage = new DialogMessage(title, message, btnText);
        dialogMessage.show(manager, "dialog message");
    }


    @Override
    public void showSnackBar(String message) {
        Snackbar.make(view, message,
                Snackbar.LENGTH_LONG)
                .show();
    }

}
