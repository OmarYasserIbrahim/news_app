package com.example.news_app;

import com.example.news_app.Models.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    String BASE_URL = "https://newsapi.org/v2/";

    @GET("top-headlines")
    Call<Posts> getPosts(
            @Query("country") String country ,
            @Query("pageSize") int pageSize,
            @Query("apiKey") String apiKey
    );
}
/*
* https://newsapi.org/v1/articles?source=techcrunch&sortBy=top&apiKey=4f2185f3d27e42749600b84d556061d5
* @Query("source") String source ,
            @Query("country") String country,
            @Query("apikey") String apiKey
* */
