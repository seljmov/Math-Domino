package com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.RoomInfo.main.table;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.science.R;

import java.util.ArrayList;

public class TableListAdapter extends RecyclerView.Adapter<TableListAdapter.TableViewHolder> {

    private ArrayList<TableListModel> mList;

    class TableViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        CardView cvPlaceCard;
        TextView tvPlace;
        TextView tvUsername;
        TextView tvPoints;
        TextView tvPlus;
        TextView tvMinus;

        TableViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cvGamerTableItem);
            cvPlaceCard = itemView.findViewById(R.id.cvPlaceCard);
            tvPlace = itemView.findViewById(R.id.tvPlace);
            tvUsername = itemView.findViewById(R.id.tvTableGamerUsername);
            tvPoints = itemView.findViewById(R.id.tvTableGamerPoints);
            tvPlus = itemView.findViewById(R.id.tvTablePlus);
            tvMinus = itemView.findViewById(R.id.tvTableMinus);
        }
    }

    public TableListAdapter(ArrayList<TableListModel> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public TableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gamer_table_item, parent, false);
        TableListAdapter.TableViewHolder rvh = new TableListAdapter.TableViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull TableViewHolder holder, int position) {

        if ((position+1) == 1) holder.cvPlaceCard.setCardBackgroundColor(Color.parseColor("#FFD700"));
        else if ((position+1) == 2) holder.cvPlaceCard.setCardBackgroundColor(Color.parseColor("#C0C0C0"));
        else if ((position+1) == 3) holder.cvPlaceCard.setCardBackgroundColor(Color.parseColor("#cd7f32"));
        else holder.cvPlaceCard.setCardBackgroundColor(Color.parseColor("#D8D8D8"));

        holder.tvPlace.setText(String.valueOf(position+1));
        holder.tvUsername.setText(mList.get(position).getUsername());
        holder.tvPoints.setText(mList.get(position).getPoints());
        if (mList.get(position).getPlus().equals("0")) holder.tvPlus.setText(mList.get(position).getPlus());
        else holder.tvPlus.setText("+" + mList.get(position).getPlus());
        holder.tvMinus.setText(mList.get(position).getMinus());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
