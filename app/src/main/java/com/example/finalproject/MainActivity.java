package com.example.finalproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.view.textclassifier.TextLinks;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Sung Kim (Clay)
 * MainActivity page - Shows App's name and logo with random daily quote.
 * Click GET() button to enter
 */

public class MainActivity extends AppCompatActivity {

    private TextView tv_homeQuote;
    private TextView tv_homeAuthor;
    private ImageView img_homeLogo;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_homeQuote = findViewById(R.id.tv_quote);
        tv_homeAuthor = findViewById(R.id.tv_author);
        img_homeLogo = findViewById(R.id.img_bulb_on);
        img_homeLogo.setVisibility(View.INVISIBLE);

        getQuote(); // get random quote via API

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

 }

    // ====================== Method for getting random quote ==============================
    public void getQuote(){

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        String url = "https://api.quotable.io/random?maxLength=80";

        StringRequest request = new StringRequest(com.android.volley.Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TESTING:::", response);
                        String str = response;

                        try{
                            JSONObject obj = new JSONObject(str);

                            String q = obj.getString("content");
                            String author = obj.getString("author");

                            Log.d("Quote:", q + "Author: " + author);

                            tv_homeQuote.setText("\"" +q+ "\"");
                            tv_homeAuthor.setText("- " + author);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("Error:::", "notWorking");
                        }

                    }
                }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("TESTING:::", "Error!!");
                        }
                    });

        // Add the request to the RequestQueue.
        queue.add(request);

    }

    // ================= Move to NewsActivity page when button is clicked  ===================
    public void getNews_button(View view) {

        img_homeLogo.setVisibility(view.VISIBLE); // Image is on visible (light bulb is on)

        Toast.makeText(getApplicationContext(), "Welcome to the World", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this, NewsActivity.class);

        startActivity(intent);
    }
}