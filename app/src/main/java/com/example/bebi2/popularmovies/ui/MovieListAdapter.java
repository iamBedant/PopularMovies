package com.example.bebi2.popularmovies.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.bebi2.popularmovies.Pojo.Movie;
import com.example.bebi2.popularmovies.R;

import java.util.ArrayList;

/**
 * Created by bebi2 on 12/19/2015.
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    ArrayList<Movie> mDataset = new ArrayList<>();
    Context mContext;

    public MovieListAdapter(Context mContext, ArrayList<Movie> mDataset) {
        this.mContext=mContext;
        this.mDataset = mDataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_movie_item,parent,false);

        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Movie currentMovie = mDataset.get(position);

        Glide.with(mContext)
                .load(currentMovie.getPosterImage())
                //TODO: add placeholder and error image
                //.placeholder()
                //.error()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.poster);
        }
    }
}
