package com.example.news_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news_app.Models.Articles;
import com.example.news_app.Models.Posts;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.viewHolder> {

    private List<Articles> articlesList ;

    //Constructor
    public PostsAdapter(List<Articles> posts)
    {
        this.articlesList = posts ;
    }
    public class viewHolder extends RecyclerView.ViewHolder {
        TextView title ;
        TextView author ;
        TextView time ;
        TextView description;
        ImageView image ;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            time = itemView.findViewById(R.id.time);
            description = itemView.findViewById(R.id.sdetails);
            image = itemView.findViewById(R.id.galleryImage);
        }
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_posts , parent , false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.title.setText(articlesList.get(position).getTitle());
        holder.author.setText(articlesList.get(position).getAuthor());
        holder.description.setText(articlesList.get(position).getDescription());
        holder.time.setText(articlesList.get(position).getPublishedAt());
        /*holder.image.setText(articlesList.get(position).getDescription());*/
        String url=articlesList.get(position).getUrlToImage();
        Picasso.get().load(url).into(holder.image);

    }


    @Override
    public int getItemCount() {
        return articlesList.size();
    }


}
