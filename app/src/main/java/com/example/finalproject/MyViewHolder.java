package com.example.finalproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView tv_title, tv_description;
    ImageView img_headline;
    CardView cardView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        tv_title = itemView.findViewById(R.id.tv_title);
        tv_description = itemView.findViewById(R.id.tv_description);
        img_headline = itemView.findViewById(R.id.img_headline);
        cardView = itemView.findViewById(R.id.card_view);

    }
}
