package com.example.finalproject;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.navigation.NavigationView;

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

    EditText search_edit;
    private Button search_button;

    //for navigation side bar
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);


        //Search Bar
        search_button = findViewById(R.id.search_button);
        search_edit = findViewById(R.id.search_keyword);

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword = search_edit.getText().toString();
                search_edit.onEditorAction(EditorInfo.IME_ACTION_DONE);
                Log.d("DEBUG:", keyword);
                progressDialog.setTitle("Searching news article of " + keyword);
                progressDialog.show();
                NetworkUtils manager = new NetworkUtils(NewsActivity.this);
                manager.getNewsByWord(listener, keyword);

            }
        });


        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading News Articles..");
        progressDialog.show();

        mRecyclerView = findViewById(R.id.re_NewsList);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        NetworkUtils manager = new NetworkUtils(this);
        manager.getNewsHeadlines(listener, "us", "general", null);

        //-----------------------for Navigation tool bar-------------------------------
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.menu_Open, R.string.close_menu);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.nav_home:
                        Log.d("DEBUG:::","Home is clicked!");

                        Intent intent = new Intent(NewsActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.nav_kr:
                        Log.d("DEBUG:::","Korea is clicked!");
                        progressDialog.setTitle("Searching news article");
                        progressDialog.show();
                        NetworkUtils manager = new NetworkUtils(NewsActivity.this);
                        manager.getNewsHeadlines(listener, "kr","general", null);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_us:
                        Log.d("DEBUG:::","US is clicked!");
                        progressDialog.setTitle("Searching news article");
                        progressDialog.show();
                        manager = new NetworkUtils(NewsActivity.this);
                        manager.getNewsHeadlines(listener, "us","general", null);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;


                    case R.id.nav_general:
                        Log.d("DEBUG:::","General is clicked!");
                        progressDialog.setTitle("Searching news article");
                        progressDialog.show();
                        manager = new NetworkUtils(NewsActivity.this);
                        manager.getNewsHeadlines(listener, "us","general", null);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_sports:
                        Log.d("DEBUG:::","Sports is clicked!");
                        progressDialog.setTitle("Searching news article");
                        progressDialog.show();
                        manager = new NetworkUtils(NewsActivity.this);
                        manager.getNewsHeadlines(listener, "us","sports", null);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_entertainment:
                        Log.d("DEBUG:::","entertainment is clicked!");
                        progressDialog.setTitle("Searching news article");
                        progressDialog.show();
                        manager = new NetworkUtils(NewsActivity.this);
                        manager.getNewsHeadlines(listener, "us","entertainment", null);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_business:
                        Log.d("DEBUG:::","business is clicked!");
                        progressDialog.setTitle("Searching news article");
                        progressDialog.show();
                        manager = new NetworkUtils(NewsActivity.this);
                        manager.getNewsHeadlines(listener, "us","business", null);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_health:
                        Log.d("DEBUG:::","Health is clicked!");
                        progressDialog.setTitle("Searching news article");
                        progressDialog.show();
                        manager = new NetworkUtils(NewsActivity.this);
                        manager.getNewsHeadlines(listener, "us","health", null);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_technology:
                        Log.d("DEBUG:::","tech is clicked!");
                        progressDialog.setTitle("Searching news article");
                        progressDialog.show();
                        manager = new NetworkUtils(NewsActivity.this);
                        manager.getNewsHeadlines(listener, "us","technology", null);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_logout:
                        Log.d("DEBUG:::","EXIT is clicked!");
                        progressDialog.setTitle("Closing app..");
                        progressDialog.show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent2.putExtra("EXIT", true);
                        startActivity(intent2);
                        break;
                }
                return true;
            }
        });
    }

    private final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {
            
            showNews(list);
            progressDialog.dismiss();
        }

        @Override
        public void onError(String message) {
            Log.d("ERROR:", "fetching data failed");
        }
    };

    private void showNews(List<NewsHeadlines> list) {

        mRecyclerView = findViewById(R.id.re_NewsList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        mAdapter = new MyAdapter( NewsActivity.this, list, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void OnNewsClicked(NewsHeadlines headlines) {
        startActivity(new Intent(NewsActivity.this, NewsDetailActivity.class)
        .putExtra("data", headlines));
    }

}