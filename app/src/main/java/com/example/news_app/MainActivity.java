package com.example.news_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.news_app.Models.Articles;
import com.example.news_app.Models.Posts;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

/*https://newsapi.org/v2/top-headlines?country=eg&apiKey=4f2185f3d27e42749600b84d556061d5*/
    public String  API_KEY = "4f2185f3d27e42749600b84d556061d5";
    String NEWS_SOURCE = "top-headlines";
    String country = "us";

    RecyclerView recyclerView;
    ProgressBar progressBar;
    PostsAdapter postsAdapter;
    LinearLayoutManager layoutManager;
    List<Articles> postsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findNews();
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        postsAdapter = new PostsAdapter(postsList);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(postsAdapter);
        recyclerView.setLayoutManager(layoutManager);


    }

    private void findNews() {

        RetrofiteClient.getRetrofiteClient().getPosts(  country , 38, API_KEY).enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                if(response.isSuccessful() && response.body() != null)
                {
                    Log.i("data" , response.body().toString());
                    postsList.addAll(response.body().articles);
                    postsAdapter.notifyDataSetChanged();
                    /*postsList.addAll(response.body());
                    postsAdapter.notifyDataSetChanged();*/

                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                Toast.makeText(MainActivity.this ,t.getMessage()  , Toast.LENGTH_SHORT).show();
                Log.i("Erorr" , t.getMessage());
            }
        });
    }
}