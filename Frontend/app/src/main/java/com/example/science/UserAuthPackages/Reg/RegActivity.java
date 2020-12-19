package com.example.science.UserAuthPackages.Reg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.science.Other.DialogIconPicker;
import com.example.science.R;
import com.example.science.UserAuthPackages.API.MainContract;
import com.example.science.UserAuthPackages.JoinActivity;
import com.google.android.material.snackbar.Snackbar;

public class RegActivity extends AppCompatActivity implements MainContract.Register {

    public static ImageView userImage;

    public final static int TEACHER_TYPE = 0;
    public final static int STUDENT_TYPE = 1;

    private EditText etName;
    private EditText etSurname;
    private EditText etEmail;
    private CardView btnProceed;
    private CardView btnGoBack;
    private ConstraintLayout view;

    public String name;
    public String surname;
    public String username;
    public String email;

    public int roleType;

    private CardView teacherButton;
    private CardView studentButton;

    private MainContract.RegPresenter regPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        userImage = findViewById(R.id.userImage);
        userImage.setOnClickListener(v -> openIconDialog());

        teacherButton = findViewById(R.id.cvTeacher);
        studentButton = findViewById(R.id.cvStudent);

        setButtons();

        teacherButton.setOnClickListener(v -> {
            roleType = TEACHER_TYPE;
            setButtons();
        });

        studentButton.setOnClickListener(v -> {
            roleType = STUDENT_TYPE;
            setButtons();
        });

        regPresenter = new CommonRegPresenter.Reg(this);

        etName = findViewById(R.id.etName);
        etSurname = findViewById(R.id.etSurname);
        etEmail = findViewById(R.id.etEmail);
        btnProceed = findViewById(R.id.cvGoProceedButton);
        btnGoBack = findViewById(R.id.cvRegBackToJoin);
        view = findViewById(R.id.regLayout);

        /*
         При нажание на кнопку "Назад", возвращает в окно Авторизации
         Без складывания в Стэк
        */
        btnGoBack.setOnClickListener(v -> {
            Intent intent = new Intent(RegActivity.this, JoinActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        /*
         При нажание на кнопку "Зарегистрироваться", регистрирует пользователя
         И переходит в главное меню
         Или выводит сообщение об ошибке
        */
        btnProceed.setOnClickListener(v -> {
            name = etName.getText().toString();
            surname = etSurname.getText().toString();
            email = etEmail.getText().toString();
            username = "null";

            regPresenter.checkUsername("default.png", name, surname, username, roleType);
        });

    }

    private void openIconDialog() {
        DialogIconPicker dialogIconPicker = new DialogIconPicker();
        dialogIconPicker.show(getSupportFragmentManager(), "dialog icon picker");
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
    public void proceedButtonOnClick(String avatar, String name,
                                     String surname, String username, int role) {

        Intent intent = new Intent(RegActivity.this, RegProceedActivity.class);
        intent.putExtra("avatar", avatar);
        intent.putExtra("name", name);
        intent.putExtra("surname", surname);
        intent.putExtra("username", username);
        intent.putExtra("role", role);
        startActivity(intent);
    }

    @Override
    public void setButtons() {
        switch(roleType){
            case TEACHER_TYPE:
                teacherButton.setCardBackgroundColor(getResources().getColorStateList(R.color.newPrimaryColor));
                studentButton.setCardBackgroundColor(getResources().getColorStateList(R.color.newSecondColor));
                break;
            case STUDENT_TYPE:
                teacherButton.setCardBackgroundColor(getResources().getColorStateList(R.color.newSecondColor));
                studentButton.setCardBackgroundColor(getResources().getColorStateList(R.color.newPrimaryColor));
        }
    }

    @Override
    public void showSnackBar(String message) {
        Snackbar.make(view, message,
                Snackbar.LENGTH_LONG)
                .show();
    }
}
