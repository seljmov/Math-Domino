package com.example.science.Main.ui.main.Pages.rooms.Teacher.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.science.Main.ui.main.Pages.rooms.Teacher.RoomModel;
import com.example.science.R;

import java.util.ArrayList;

import static com.example.science.R.mipmap.room_back_1;
import static com.example.science.R.mipmap.room_back_2;
import static com.example.science.R.mipmap.room_back_3;

public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.RoomsViewHolder> {

    private ArrayList<RoomModel> mRoomList;
    private OnRoomListener mOnRoomListener;

    class RoomsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cardView;
        CardView cvStatusCard;
        ImageView ivRoomIcon_Back;
        ImageView ivRoomIcon_Icon;
        TextView roomName;
        TextView subject;
        TextView gCount;

        OnRoomListener onRoomListener;

        RoomsViewHolder(View itemView, OnRoomListener onRoomListener) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cvItem);
            cvStatusCard = itemView.findViewById(R.id.cvStatusCard);
            ivRoomIcon_Back = itemView.findViewById(R.id.ivRoomIcon_Back);
            ivRoomIcon_Icon = itemView.findViewById(R.id.ivRoomIcon_Icon);
            roomName = itemView.findViewById(R.id.tvRoomNameCard);
            subject = itemView.findViewById(R.id.tvSubjectCard);
            gCount = itemView.findViewById(R.id.tvGcount);
            this.onRoomListener = onRoomListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int s = getAdapterPosition();
            onRoomListener.onRoomClick(mRoomList, s);
        }
    }

    public RoomsAdapter(ArrayList<RoomModel> roomItems, OnRoomListener onRoomListener) {
        this.mRoomList = roomItems;
        this.mOnRoomListener = onRoomListener;
    }

    @NonNull
    @Override
    public RoomsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_item, parent, false);
        RoomsViewHolder rvh = new RoomsViewHolder(v, mOnRoomListener);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RoomsViewHolder holder, final int position) {

        /* Если статус комнаты "Не начато", то дать индикатору темно-розовый цвет,
         *  Иначе если статус "Идет", то - оранжевый цвет,
         *  Иначе - зеленый.
         * */
        String status = mRoomList.get(position).getStatus();
        String icon = mRoomList.get(position).getIcon();
        if (status.equals("notStarted")) {
            holder.cvStatusCard.setCardBackgroundColor(Color.parseColor("#42A5F5"));
            holder.ivRoomIcon_Back.setImageResource(room_back_1);
        } else if (status.equals("going")) {
            holder.cvStatusCard.setCardBackgroundColor(Color.parseColor("#FFCA28"));
            holder.ivRoomIcon_Back.setImageResource(room_back_2);
        } else {
            holder.cvStatusCard.setCardBackgroundColor(Color.parseColor("#66BB6A"));
            holder.ivRoomIcon_Back.setImageResource(room_back_3);
        }

        setRoomIcon(icon, holder.ivRoomIcon_Icon);
        holder.roomName.setText(mRoomList.get(position).getRoomName());
        holder.subject.setText(mRoomList.get(position).getSubject());
        holder.gCount.setText(mRoomList.get(position).getGamersCount() + "/"
                + mRoomList.get(position).getStudentsCount());
    }

    private void setRoomIcon(String icon, ImageView img) {
        if ("room_logo_1.png".equals(icon)) img.setImageResource(R.mipmap.room_logo_1);
        else if ("room_logo_2.png".equals(icon)) img.setImageResource(R.mipmap.room_logo_2);
        else if ("room_logo_3.png".equals(icon)) img.setImageResource(R.mipmap.room_logo_3);
        else if ("room_logo_4.png".equals(icon)) img.setImageResource(R.mipmap.room_logo_4);
        else img.setImageResource(R.mipmap.room_logo_5);
    }

    @Override
    public int getItemCount() {
        return mRoomList.size();
    }

    public int getRoomId(int position) {
        return mRoomList.get(position).getId();
    }

    public interface OnRoomListener {
        void onRoomClick(ArrayList<RoomModel> arrayList, int position);
    }

}
