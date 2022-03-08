package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.TextureView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class NewsDetailActivity extends AppCompatActivity {
    NewsHeadlines headlines;

    TextView tv_title;
    TextView tv_author;
    TextView tv_detail;
    TextView tv_content;
    TextView tv_time;
    ImageView img_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        tv_title = findViewById(R.id. tv_detail_title);
        tv_author = findViewById(R.id. tv_detail_author);
        tv_detail = findViewById(R.id. tv_detail_detail);
        tv_content = findViewById(R.id. tv_detail_content);
        tv_time = findViewById(R.id. tv_detail_time);
        img_news = findViewById(R.id. img_detail_news);


        headlines = (NewsHeadlines) getIntent().getSerializableExtra("data");

        tv_title.setText(headlines.getTitle());
        tv_author.setText(headlines.getAuthor());
        tv_detail.setText(headlines.getDescription());
        tv_time.setText(headlines.getPublishedAt());
        tv_content.setText(headlines.getContent());

        Picasso.get().load(headlines.getUrlToImage()).into(img_news);
//        Uri uri = Uri.parse(headlines.getUrlToImage());
//        img_news.setImageURI(uri);


    }
}