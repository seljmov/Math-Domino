package com.example.science.Other;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import com.example.science.R;

public class DialogMessage extends AppCompatDialogFragment {

    private TextView tvTitle;
    private TextView tvMessageText;
    private Button btnDismiss;

    private Activity activity;
    private boolean close = false;

    private String title;
    private String message;
    private String btnText;

     public DialogMessage(String title, String message, String btnText) {
         this.title = title;
         this.message = message;
         this.btnText = btnText;
    }

    public DialogMessage(String title, String message, String btnText, Activity activity, boolean close) {
        this.title = title;
        this.message = message;
        this.btnText = btnText;
        this.activity = activity;
        this.close = close;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_message, null);

        tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText(title);
        tvMessageText = view.findViewById(R.id.tvMessageText);
        tvMessageText.setText(message);
        btnDismiss = view.findViewById(R.id.btnDismiss);
        btnDismiss.setText(btnText);
        btnDismiss.setOnClickListener(v -> dis());

        builder.setView(view);

        return builder.create();
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (close) {
            activity.finish();
        }
    }

    public void dis() {
        if (close) activity.finish();
        else dismiss();
    }
}