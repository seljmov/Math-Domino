package com.example.science.Main.ui.main.Pages.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.science.Main.ui.main.Pages.profile.API.ProfileRepository;
import com.example.science.Main.ui.main.Pages.profile.API.StatsData;
import com.example.science.Main.ui.main.Pages.profile.settings.SettingsActivity;
import com.example.science.R;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ProfileFragment extends Fragment {

    private TextView tvNameSurname;
    private TextView tvEmail;
    private TextView tvPlus;
    private TextView tvPoints;
    private TextView tvMunis;

    private CardView btnSettings;

    private String name;
    private String surname;
    private String email;
    private String username;

    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());

        name = preferences.getString("name", "unknown");
        surname = preferences.getString("surname", "unknown");
        email = preferences.getString("email", "unknown");
        username = preferences.getString("username", "unknown");
        int role = preferences.getInt("role", 666);

        if (role == 0) {
            root = inflater.inflate(R.layout.fragment_profile_teacher, container, false);

        } else if (role == 1) {
            root = inflater.inflate(R.layout.fragment_profile_student, container, false);

            tvPlus = root.findViewById(R.id.stat_1);
            tvPoints = root.findViewById(R.id.stat_2);
            tvMunis = root.findViewById(R.id.stat_3);

            Student.loadUserStats(username, tvPlus, tvPoints, tvMunis);
        }

        tvNameSurname = root.findViewById(R.id.tvName);
        tvNameSurname.setText(name + " " + surname);

        tvEmail = root.findViewById(R.id.tvEmail);
        tvEmail.setText(email);

        btnSettings = root.findViewById(R.id.cvGoSettingsButton);
        btnSettings.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SettingsActivity.class);
            startActivity(intent);
        });

        return root;
    }

    class Teacher {


    }

    static class Student {
        static void loadUserStats(String username, TextView tvPlus,
                                  TextView tvPoints, TextView tvMinus) {
            ProfileRepository repository = new ProfileRepository();
            repository.getUserStatsFromServer(username)
                    .subscribe(new Observer<StatsData>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(StatsData statsData) {
                            tvPlus.setText(String.valueOf(statsData.getPlus()));
                            tvPoints.setText(String.valueOf(statsData.getPoints()));
                            tvMinus.setText(String.valueOf(Math.abs(statsData.getMinus())));
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

}