package com.example.finalproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Sung Kim (Clay)
 * Adapter class for RecyclerView
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<NewsHeadlines> list;
    private SelectListener listener;

    public MyAdapter(Context context, List<NewsHeadlines> list, SelectListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
        Fresco.initialize(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_title.setText(list.get(position).getTitle());
        holder.tv_description.setText(list.get(position).getDescription());

        Uri uri = Uri.parse(list.get(position).getUrlToImage());
        holder.img_headline.setImageURI(uri);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.OnNewsClicked(list.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
