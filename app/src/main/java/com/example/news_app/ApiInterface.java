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
            @Query("pageSize") int pageSize,//for specifying how many posts I want
            @Query("apiKey") String apiKey
    );
}
/*
ده اللنك الأصلي بيتقسم لbase url و الpath الهو top-headLines في الحالة دي
بعد كده اي حاجة بعد ? دي queries
بحدد بيها خضائص تانية وي البلد العايزة اخبارها و الapikey البيديني صلاحية اني استخدم api
* https://newsapi.org/v2/top-headlines?country=us&apiKey=4f2185f3d27e42749600b84d556061d5
*
* */
