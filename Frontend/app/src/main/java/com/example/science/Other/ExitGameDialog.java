package com.example.science.Other;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.cardview.widget.CardView;

import com.example.science.R;

public class ExitGameDialog extends AppCompatDialogFragment {

    private CardView btnExit;
    private CardView btnCancel;

    private Activity activity;

    public ExitGameDialog(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_message, null);

        btnCancel = view.findViewById(R.id.cvCancel);
        btnCancel.setOnClickListener(v -> dismiss());

        btnExit = view.findViewById(R.id.cvExit);
        btnExit.setOnClickListener(v -> exit());

        builder.setView(view);

        return builder.create();
    }

    private void exit() {
        activity.finish();
    }
}