package com.example.finalproject;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
//    private List<NewsData> mDataset;
    private List<NewsData> mDataset;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView text_content;
        public TextView text_info;
        public SimpleDraweeView image_title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text_info = itemView.findViewById(R.id.tv_info);
            text_content = itemView.findViewById(R.id.tv_content);
            image_title = itemView.findViewById(R.id.image_title);
        }
    }

    // provide a suitable constructor
    public MyAdapter(List<NewsData> mDataset, Context context) {

        this.mDataset = mDataset;
        Fresco.initialize(context);
    }


    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // create a new view
        LinearLayout view = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);

        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        NewsData news = mDataset.get(position);

        holder.text_info.setText(news.getTitle());
        holder.text_content.setText(news.getContent());

        Uri uri = Uri.parse(news.getUrlToImage());

        holder.image_title.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }


}
