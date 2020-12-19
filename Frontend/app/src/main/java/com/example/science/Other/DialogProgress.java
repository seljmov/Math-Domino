package com.example.science.Other;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.science.R;

public class DialogProgress extends AppCompatDialogFragment {

    private ProgressBar progressBar;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_progress, null);

        progressBar = view.findViewById(R.id.gameLoadProgressBar);

        builder.setView(view);

        return builder.create();
    }

    @Override
    public void dismiss() {
        // progressBar.setVisibility(View.GONE);
        dismiss();
    }
}
