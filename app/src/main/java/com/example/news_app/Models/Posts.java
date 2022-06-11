package com.example.news_app.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Posts {

    private String status ;
    private int totalResults ;
    public List<Articles> articles;

    public Posts(String status, int totalResults, List<Articles> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }


}
