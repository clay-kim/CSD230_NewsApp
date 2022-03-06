package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mTextInput;
    private TextView mTextView;
    private EditText mTextPrice;
    private Toast mToast;


    public static final String TEXT_MSG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextInput = findViewById(R.id.editText);

    }


    public void searchButton(View view) {
        Log.d("LOG_TAG", "Button clicked!");
        String str = mTextInput.getText().toString();
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this, NewsActivity.class);
        intent.putExtra(TEXT_MSG, str);
        startActivity(intent);


    }
}