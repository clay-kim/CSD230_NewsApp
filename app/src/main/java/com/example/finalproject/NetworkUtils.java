package com.example.finalproject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Sung Kim (Clay)
 * API request handler:
 * Using Retrofit
 */

public class NetworkUtils {

    Context context;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    // ====================== Method for getting news by keyword ==============================
    public void getNewsByWord(OnFetchDataListener listener, String query){

        CallNewsApi2 callSearchNews = retrofit.create(CallNewsApi2.class);
        Call<NewsApiResponse> call = callSearchNews.callHeadlines(query,"relevancy", "be5f460cf91a4335a73b5e1a9fd08efe");
        try {
            call.enqueue(new Callback<NewsApiResponse>() {
                @Override
                public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(context, "Errorrr!!", Toast.LENGTH_SHORT).show();
                    }
                    Log.d("NEW QUERY::",response.toString());
                    listener.onFetchData(response.body().getArticles(), response.message());

                }

                @Override
                public void onFailure(Call<NewsApiResponse> call, Throwable t) {

                    listener.onError("ERROR: Request Failed.");
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    // ====================== Method for getting Headlines ==============================
    public void getNewsHeadlines(OnFetchDataListener listener, String country, String category, String query){

        CallNewsApi callNewsApi = retrofit.create(CallNewsApi.class);
        Call<NewsApiResponse> call = callNewsApi.callHeadlines(country, category, query, context.getString(R.string.api_Key));

            try {
                call.enqueue(new Callback<NewsApiResponse>() {
                    @Override
                    public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(context, "Errorrr!!", Toast.LENGTH_SHORT).show();
                        }
                    Log.d("NEW QUERY::",response.toString());
                        listener.onFetchData(response.body().getArticles(), response.message());

                    }

                    @Override
                    public void onFailure(Call<NewsApiResponse> call, Throwable t) {

                        listener.onError("ERROR: Request Failed.");
                    }
                });
            }
            catch (Exception e){
                e.printStackTrace();
            }

    }

    public NetworkUtils(Context context) {
        this.context = context;
    }

    // ======= For getting Headlines in U.S. ===========
    public interface CallNewsApi {
        @GET("top-headlines")
        Call<NewsApiResponse> callHeadlines(
                @Query("country") String country,
                @Query("category") String category,
                @Query("q") String query,
                @Query("apiKey") String apiKey
        );
    }
    // ======= For searching News ===========
    public interface CallNewsApi2 {
        @GET("everything")
        Call<NewsApiResponse> callHeadlines(
                @Query("q") String query,
                @Query("sortBy") String sortBy,
                @Query("apiKey") String apiKey
        );
    }
}