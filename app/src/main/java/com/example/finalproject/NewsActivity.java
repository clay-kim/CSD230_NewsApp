package com.example.finalproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.drawable.ProgressBarDrawable;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NewsActivity extends MainActivity implements SelectListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    //progressDialog instead of progressBar
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading News Articles..");
        progressDialog.show();

        mRecyclerView = findViewById(R.id.re_NewsList);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Intent intent = getIntent();
        String msg = intent.getStringExtra(MainActivity.TEXT_MSG);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();


        NetworkUtils manager = new NetworkUtils(this); //!?!?
        manager.getNewsHeadlines(listener, "general", null);



    }

    //test----
    private final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {
            
            showNews(list);
            progressDialog.dismiss();

        }

        @Override
        public void onError(String message) {

        }
    };

    private void showNews(List<NewsHeadlines> list) {

        mRecyclerView = findViewById(R.id.re_NewsList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        mAdapter = new MyAdapter(list, NewsActivity.this, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void OnNewsClicked(NewsHeadlines headlines) {
        startActivity(new Intent(NewsActivity.this, NewsDetailActivity.class)
        .putExtra("data", headlines));
    }


}








//    public void getNews(){
//
//        String url = "https://newsapi.org/v2/everything?q=bitcoin&apiKey=be5f460cf91a4335a73b5e1a9fd08efe";
//        //apiKey=gER3f7n2zDShnDrX2mPpvg3KxSO2UQ9vQbduNiHQ
//        // 433801-SungAmKi-K3XMPTHV
//
//        // Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//
//                    @Override
//                    public void onResponse(String response) {
//
//                        try {
//                            JSONObject jsonObj = new JSONObject(response);
//                            JSONArray arrayArticle = jsonObj.getJSONArray("articles");
//
//                            // response -->> NewsData class sort
//                            List<NewsData> news = new ArrayList<>();
//
//
//                            for(int i = 0, j = arrayArticle.length(); i < j; i++)
//                            {
//                                JSONObject obj = arrayArticle.getJSONObject(i);
//
//                                Log.d("NEWS::-->>>", obj.toString());  // for log info
//
//                                NewsData newsData = new NewsData();
//                                newsData.setTitle(obj.getString("title"));
//                                newsData.setUrlToImage(obj.getString("urlToImage"));
//                                newsData.setContent(obj.getString("description"));
//                                news.add(newsData);
//                            }
//
//
//                            // specify an adapter
//                            mAdapter = new MyAdapter(news, NewsActivity.this);
//                            mRecyclerView.setAdapter(mAdapter);
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("NEW ERROR:","Wrong@@!!");
//
//            }
//        });
//
//        // Add the request to the RequestQueue.
//        queue.add(stringRequest);
// }

