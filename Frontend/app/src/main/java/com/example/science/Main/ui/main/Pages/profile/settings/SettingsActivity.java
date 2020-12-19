package com.example.science.Main.ui.main.Pages.profile.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.science.Main.ui.main.Pages.profile.settings.API.SettingsContract;
import com.example.science.R;
import com.example.science.UserAuthPackages.JoinActivity;

public class SettingsActivity extends AppCompatActivity implements SettingsContract.SettingsView {

    private CardView btnLogout;

    private SettingsContract.SettingsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        presenter = new SettingsPresenter(this);

        btnLogout = findViewById(R.id.cvExitButton);
        btnLogout.setOnClickListener(v -> {
            SharedPreferences preferences = PreferenceManager
                    .getDefaultSharedPreferences(getApplicationContext());

            presenter.logoutUserFromServer(preferences);
        });
    }

    @Override
    public void goToJoin() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(SettingsActivity.this, JoinActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
