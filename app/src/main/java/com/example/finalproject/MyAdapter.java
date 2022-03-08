package com.example.finalproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private List<NewsHeadlines> list;
    private SelectListener listener;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView text_description;
        public TextView text_info;
        public SimpleDraweeView image_title;
        public CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text_info = itemView.findViewById(R.id.tv_info);
            text_description = itemView.findViewById(R.id.tv_content);
            image_title = itemView.findViewById(R.id.image_title);
            cardView = itemView.findViewById(R.id.card_view);
        }

    }

    // provide a suitable constructor
    public MyAdapter(List<NewsHeadlines> list, Context context, SelectListener listener) {
        this.context = context;
        this.list = list;
        Fresco.initialize(context);
        this.listener = listener;
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
        NewsHeadlines news = list.get(position);

        holder.text_info.setText(news.getTitle());
        holder.text_description.setText(news.getDescription());
        Uri uri = Uri.parse(news.getUrlToImage());
        holder.image_title.setImageURI(uri);

        //---------test----------------



        //---------test----------------

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "HAHAHAHAHHAHAHAH",Toast.LENGTH_LONG).show();

//                Intent intent = new Intent(context, NewsDetailActivity.class);
//                intent.putExtra("context", news.getDescription());
//                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


}
