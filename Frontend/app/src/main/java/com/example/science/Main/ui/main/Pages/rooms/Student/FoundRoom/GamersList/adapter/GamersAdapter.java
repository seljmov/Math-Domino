package com.example.science.Main.ui.main.Pages.rooms.Student.FoundRoom.GamersList.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.science.Main.ui.main.Pages.rooms.Student.FoundRoom.GamersList.GamerModel;
import com.example.science.R;

import java.util.ArrayList;

public class GamersAdapter extends RecyclerView.Adapter<GamersAdapter.GamersViewHolder>  {

    private ArrayList<GamerModel> mGamersList;

    class GamersViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView gamerUsername;

        GamersViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cvItem);
            gamerUsername = itemView.findViewById(R.id.tvGamerUsername);

        }
    }

    public GamersAdapter(ArrayList<GamerModel> gamersItems) {
        this.mGamersList = gamersItems;
    }

    @NonNull
    @Override
    public GamersAdapter.GamersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gamer_item, parent, false);
        GamersAdapter.GamersViewHolder rvh = new GamersAdapter.GamersViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull GamersAdapter.GamersViewHolder holder, final int position) {

        holder.gamerUsername.setText(mGamersList.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return mGamersList.size();
    }

}
