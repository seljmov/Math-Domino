package com.example.science.Main.ui.main.Pages.profile.settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.science.Main.ui.main.Pages.profile.settings.API.SettingsContract;
import com.example.science.R;
import com.example.science.UserAuthPackages.JoinActivity;


public class SettingsFragment extends Fragment implements SettingsContract.SettingsView {

    private CardView cvExitButton;

    private SettingsContract.SettingsPresenter presenter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        presenter = new SettingsPresenter(this);

        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        cvExitButton = root.findViewById(R.id.cvExitButton);
        cvExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager
                        .getDefaultSharedPreferences(getContext());

                presenter.logoutUserFromServer(preferences);
            }
        });

        return root;
    }

    @Override
    public void goToJoin() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(getContext(), JoinActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}