package com.example.news_app;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofiteClient {



    private static Retrofit retrofit = null;

    public static  ApiInterface getRetrofiteClient()
    {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
            .baseUrl(ApiInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        }
        return  retrofit.create(ApiInterface.class);
    }
}
