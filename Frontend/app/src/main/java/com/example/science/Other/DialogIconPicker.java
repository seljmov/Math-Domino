package com.example.science.Other;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.cardview.widget.CardView;

import com.example.science.R;
import com.example.science.UserAuthPackages.Reg.RegActivity;

public class DialogIconPicker extends AppCompatDialogFragment {

    private int image;

    private CardView icon_1;
    private CardView icon_2;
    private CardView icon_3;
    private CardView icon_4;
    private CardView icon_5;
    private CardView icon_6;
    private CardView icon_7;
    private CardView icon_8;
    private CardView icon_9;

    private CardView cvDismiss_full;
    private CardView cvDismiss_mini;
    private CardView cvConfirm;

    private boolean take = false;

    private CardView chooceicon;
    private int icon_id;
    private String icon_name;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_icon_picker, null);

        int color_white = Color.rgb(243, 245, 255);
        int color_pink = Color.rgb(2, 177, 227);

        icon_1 = view.findViewById(R.id.cvIcon_1);
        icon_2 = view.findViewById(R.id.cvIcon_2);
        icon_3 = view.findViewById(R.id.cvIcon_3);
        icon_4 = view.findViewById(R.id.cvIcon_4);
        icon_5 = view.findViewById(R.id.cvIcon_5);
        icon_6 = view.findViewById(R.id.cvIcon_6);
        icon_7 = view.findViewById(R.id.cvIcon_7);
        icon_8 = view.findViewById(R.id.cvIcon_8);
        icon_9 = view.findViewById(R.id.cvIcon_9);

        cvDismiss_full = view.findViewById(R.id.cvDismiss_full);
        cvDismiss_mini = view.findViewById(R.id.cvDismiss_mini);
        cvConfirm = view.findViewById(R.id.cvConfirm);

        chooceicon = icon_1;

        icon_1.setOnClickListener(v -> {
            chooceicon.setCardBackgroundColor(color_white);
            icon_1.setCardBackgroundColor(color_pink);
            chooceicon = icon_1;
            take = true;

            image = R.mipmap.user_icon_1;

            setIconName(1);
            setBtn();
        });

        icon_2.setOnClickListener(v -> {
            chooceicon.setCardBackgroundColor(color_white);
            icon_2.setCardBackgroundColor(color_pink);
            chooceicon = icon_2;
            take = true;

            image = R.mipmap.user_icon_2;

            setIconName(2);
            setBtn();
        });

        icon_3.setOnClickListener(v -> {
            chooceicon.setCardBackgroundColor(color_white);
            icon_3.setCardBackgroundColor(color_pink);
            chooceicon = icon_3;
            take = true;

            image = R.mipmap.user_icon_3;

            setIconName(3);
            setBtn();
        });

        icon_4.setOnClickListener(v -> {
            chooceicon.setCardBackgroundColor(color_white);
            icon_4.setCardBackgroundColor(color_pink);
            chooceicon = icon_4;
            take = true;

            image = R.mipmap.user_icon_4;

            setIconName(4);
            setBtn();
        });

        icon_5.setOnClickListener(v -> {
            chooceicon.setCardBackgroundColor(color_white);
            icon_5.setCardBackgroundColor(color_pink);
            chooceicon = icon_5;
            take = true;

            image = R.mipmap.user_icon_5;

            setIconName(5);
            setBtn();
        });

        icon_6.setOnClickListener(v -> {
            chooceicon.setCardBackgroundColor(color_white);
            icon_6.setCardBackgroundColor(color_pink);
            chooceicon = icon_6;
            take = true;

            image = R.mipmap.user_icon_6;

            setIconName(6);
            setBtn();
        });

        icon_7.setOnClickListener(v -> {
            chooceicon.setCardBackgroundColor(color_white);
            icon_7.setCardBackgroundColor(color_pink);
            chooceicon = icon_7;
            take = true;

            image = R.mipmap.user_icon_7;

            setIconName(7);
            setBtn();
        });

        icon_8.setOnClickListener(v -> {
            chooceicon.setCardBackgroundColor(color_white);
            icon_8.setCardBackgroundColor(color_pink);
            chooceicon = icon_8;
            take = true;

            image = R.mipmap.user_icon_8;

            setIconName(8);
            setBtn();
        });

        icon_9.setOnClickListener(v -> {
            chooceicon.setCardBackgroundColor(color_white);
            icon_9.setCardBackgroundColor(color_pink);
            chooceicon = icon_9;
            take = true;

            image = R.mipmap.user_icon_9;

            setIconName(9);
            setBtn();
        });

        cvDismiss_full.setOnClickListener(v -> dismiss());
        cvDismiss_mini.setOnClickListener(v -> dismiss());

        cvConfirm.setOnClickListener(v -> {
            SharedPreferences preferences = PreferenceManager
                    .getDefaultSharedPreferences(getContext());

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("user_icon", icon_name);
            editor.apply();

            RegActivity.userImage.setImageResource(image);

            dismiss();
        });

        builder.setView(view);

        return builder.create();
    }

    private void setIconName(int id) {
        this.icon_id = id;
        this.icon_name = "user_icon_" + id + ".png";
    }

    private void setBtn() {
        if (!take) {
            cvDismiss_full.setVisibility(View.VISIBLE);
            cvDismiss_mini.setVisibility(View.INVISIBLE);
            cvConfirm.setVisibility(View.INVISIBLE);
        } else {
            cvDismiss_full.setVisibility(View.INVISIBLE);
            cvDismiss_mini.setVisibility(View.VISIBLE);
            cvConfirm.setVisibility(View.VISIBLE);
        }
    }

}