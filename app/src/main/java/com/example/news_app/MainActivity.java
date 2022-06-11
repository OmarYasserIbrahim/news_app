package com.example.news_app;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news_app.Models.Articles;
import com.example.news_app.Models.Posts;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

/*https://newsapi.org/v2/top-headlines?country=eg&apiKey=4f2185f3d27e42749600b84d556061d5*/

    String  API_KEY = "4f2185f3d27e42749600b84d556061d5";
    String country = "us";
    int pageSize = 45;
    private PostsAdapter.RecyclerViewListener listener;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    PostsAdapter postsAdapter;
    LinearLayoutManager layoutManager;
    List<Articles> articlesList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findNews();

        listener = new PostsAdapter.RecyclerViewListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext() , DetailsActivity.class);
                intent.putExtra("url" ,articlesList.get(position).getUrl() );
                startActivity(intent);
            }
        };

        recyclerView = findViewById(R.id.recyclerView);


        progressBar = findViewById(R.id.progressBar);

        postsAdapter = new PostsAdapter(articlesList, listener);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(postsAdapter);
        recyclerView.setLayoutManager(layoutManager);


    }


    private void findNews() {

        RetrofiteClient.getRetrofiteClient().getPosts(  country , pageSize ,  API_KEY).enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                if(response.isSuccessful() && response.body() != null)
                {
                    Log.i("data" , response.body().toString());
                    articlesList.addAll(response.body().articles);
                    pageSize = response.body().articles.size();
                    postsAdapter.notifyDataSetChanged();


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

    //To check Inter net Connection
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}