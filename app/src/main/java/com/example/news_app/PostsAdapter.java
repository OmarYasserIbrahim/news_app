package com.example.news_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news_app.Models.Articles;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.viewHolder> {

    private List<Articles> articlesList ;
    private  RecyclerViewListener listener;

    Context context;
    //Constructor
    public PostsAdapter(List<Articles> posts , RecyclerViewListener listener)
    {
        this.listener = listener ;
        this.articlesList = posts ;
    }
    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title ;
        TextView author ;
        TextView time ;
        TextView description;
        ImageView image ;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            time = itemView.findViewById(R.id.time);
            description = itemView.findViewById(R.id.sdetails);
            image = itemView.findViewById(R.id.galleryImage);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view , getAdapterPosition());

        }
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)  {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_posts , parent , false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position)  {
        holder.title.setText(articlesList.get(position).getTitle());
        holder.author.setText(articlesList.get(position).getAuthor());
        holder.description.setText(articlesList.get(position).getDescription());
        holder.time.setText(articlesList.get(position).getPublishedAt());
        /*holder.image.setText(articlesList.get(position).getDescription());*/

        String imageUrl = articlesList.get(position).getUrlToImage();

        Picasso.get().load(imageUrl).into(holder.image);


    }


    @Override
    public int getItemCount() {
        return articlesList.size();
    }


    public interface RecyclerViewListener{
        void onClick(View v , int position);
    }
}
