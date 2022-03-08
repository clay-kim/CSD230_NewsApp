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

        //Image handle by picasso
//        if (list.get(position).getUrlToImage()!= null){
//            Picasso.get().load(list.get(position).getUrlToImage()).into(holder.img_headline);
//        }
        Uri uri = Uri.parse(list.get(position).getUrlToImage());
        holder.img_headline.setImageURI(uri);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "WOW", Toast.LENGTH_SHORT).show();
                listener.OnNewsClicked(list.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}


//    private Context context;
//    private List<NewsHeadlines> list;
//    private SelectListener listener;
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder {
//        public TextView text_description;
//        public TextView text_info;
//        public SimpleDraweeView image_title;
//        public CardView cardView;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            text_info = itemView.findViewById(R.id.tv_info);
//            text_description = itemView.findViewById(R.id.tv_content);
//            image_title = itemView.findViewById(R.id.image_title);
//            cardView = itemView.findViewById(R.id.card_view);
//        }
//
//    }
//
//    // provide a suitable constructor
//    public MyAdapter(List<NewsHeadlines> list, Context context, SelectListener listener) {
//        this.context = context;
//        this.list = list;
//        Fresco.initialize(context);
//        this.listener = listener;
//    }
//
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        // create a new view
//        LinearLayout view = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
//
//        MyViewHolder holder = new MyViewHolder(view);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        NewsHeadlines news = list.get(position);
//
//        holder.text_info.setText(news.getTitle());
//        holder.text_description.setText(news.getDescription());
//        Uri uri = Uri.parse(news.getUrlToImage());
//        holder.image_title.setImageURI(uri);
//
//        //---------test----------------
//
//
//
//        //---------test----------------
//
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(), "HAHAHAHAHHAHAHAH",Toast.LENGTH_LONG).show();
//
////                Intent intent = new Intent(context, NewsDetailActivity.class);
////                intent.putExtra("context", news.getDescription());
////                context.startActivity(intent);
//
//
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return list == null ? 0 : list.size();
//    }
//
//
//}
