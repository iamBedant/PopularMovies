package com.example.bebi2.popularmovies.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.bebi2.popularmovies.Config.Config;
import com.example.bebi2.popularmovies.Pojo.Movie;
import com.example.bebi2.popularmovies.R;
import com.example.bebi2.popularmovies.ui.Activity.MovieDetails;

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
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Movie currentMovie = mDataset.get(position);

        Glide.with(mContext)
                .load(currentMovie.getPosterImage())
                //TODO: add placeholder and error image
                //.placeholder()
                //.error()
                .into(holder.imageView);


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MovieDetails.class);
                intent.putExtra(Config.BUNDLE_SINGLE_MOVIES,currentMovie);
                int currentapiVersion = android.os.Build.VERSION.SDK_INT;
                if (currentapiVersion >= Build.VERSION_CODES.JELLY_BEAN) {
                    Pair<View, String> p1 = Pair.create((View) holder.imageView, "poster");
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation((Activity) mContext, p1);
                    mContext.startActivity(intent, options.toBundle());
                } else {
                    mContext.startActivity(intent);
                }
            }
        });
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
