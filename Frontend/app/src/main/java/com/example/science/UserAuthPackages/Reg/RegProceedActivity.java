package com.example.science.UserAuthPackages.Reg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.science.Main.MenuActivity;
import com.example.science.R;
import com.example.science.UserAuthPackages.API.MainContract;
import com.google.android.material.snackbar.Snackbar;

import java.util.Timer;

public class RegProceedActivity extends AppCompatActivity implements MainContract.RegisterProceed {

    private EditText etPassword;
    private EditText etConfirmPassword;
    private CardView btnRegister;
    private CardView btnGoBack;
    private ConstraintLayout view;

    public String avatar;
    public String name;
    public String surname;
    public String username;
    public int role;
    public String email;
    public String password;
    public String confirmPassword;

    private Timer timer = new Timer();
    private MainContract.RegProceedPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_proceed);

        presenter = new CommonRegPresenter.RegProceed(this);

        Intent intent = getIntent();
        avatar = intent.getStringExtra("avatar");
        name = intent.getStringExtra("name");
        surname = intent.getStringExtra("surname");
        email = intent.getStringExtra("email");
        username = intent.getStringExtra("username");
        role = intent.getIntExtra("role", 666);

        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.cvGoRegButton);
        btnGoBack = findViewById(R.id.cvBackToReg);

        view = findViewById(R.id.regProceedLayout);

        /*
         При нажание на кнопку "Назад", возвращает в окно Авторизации
         Без складывания в Стэк
        */
        btnGoBack.setOnClickListener(v -> {
            Intent intent1 = new Intent(RegProceedActivity.this, RegActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent1);
        });

        btnRegister.setOnClickListener(v -> {
            password = etPassword.getText().toString();
            confirmPassword = etConfirmPassword.getText().toString();

            SharedPreferences preferences = PreferenceManager
                    .getDefaultSharedPreferences(getApplicationContext());

            presenter.regButtonOnClicked(preferences, avatar, name, surname, username,
                    email, password, role);
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
    public void userIsReg() {
        Intent intent = new Intent(RegProceedActivity.this, MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void showSnackBar(String message) {
        Snackbar.make(view, message,
                Snackbar.LENGTH_LONG)
                .show();
    }
}
