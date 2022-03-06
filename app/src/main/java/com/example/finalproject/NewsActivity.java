package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class NewsActivity extends MainActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    // Instantiate the RequestQueue.
    RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        mRecyclerView = findViewById(R.id.re_NewsList);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Intent intent = getIntent();
        String msg = intent.getStringExtra(MainActivity.TEXT_MSG);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        queue = Volley.newRequestQueue(this);
        getNews();


    }

    public void getNews(){

        String url = "https://saurav.tech/NewsAPI/top-headlines/category/entertainment/in.json";


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            JSONArray arrayArticle = jsonObj.getJSONArray("articles");

                            // response -->> NewsData class sort
                            List<NewsData> news = new ArrayList<>();


                            for(int i = 0, j = arrayArticle.length(); i < j; i++)
                            {
                                JSONObject obj = arrayArticle.getJSONObject(i);

                                Log.d("NEWS::-->>>", obj.toString());  // for log info

                                NewsData newsData = new NewsData();
                                newsData.setTitle(obj.getString("title"));
                                newsData.setUrlToImage(obj.getString("urlToImage"));
                                newsData.setContent(obj.getString("description"));
                                news.add(newsData);
                            }

                            // specify an adapter
                            mAdapter = new MyAdapter(news, NewsActivity.this);
                            mRecyclerView.setAdapter(mAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("NEW ERROR:","Wrong@@!!");

            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
 }
}
