package com.example.bebi2.popularmovies.ui.Fragments;

import android.media.Image;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bebi2.popularmovies.Config.Config;
import com.example.bebi2.popularmovies.Pojo.Movie;
import com.example.bebi2.popularmovies.R;

import java.text.SimpleDateFormat;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailsFragment extends Fragment {

    ImageView poster;
    TextView title,releaseDate,rating,synopsis;



    public MovieDetailsFragment() {
    }

    public static MovieDetailsFragment newInstance(Movie movie) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(Config.BUNDLE_SINGLE_MOVIES, movie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mFragmentView = inflater.inflate(R.layout.fragment_movie_details, container, false);
        Movie movie = getArguments().getParcelable(Config.BUNDLE_SINGLE_MOVIES);
        if (movie == null) {
            throw new IllegalStateException("No movie given!");
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        title = (TextView) mFragmentView.findViewById(R.id.title);
        releaseDate = (TextView) mFragmentView.findViewById(R.id.release_date);
        rating = (TextView) mFragmentView.findViewById(R.id.rating);
        synopsis = (TextView) mFragmentView.findViewById(R.id.synopsis);
        poster = (ImageView) mFragmentView.findViewById(R.id.poster);



        if(movie.getPosterImage()==null){

            poster.setImageResource(R.drawable.not_available);

        }
        else {
            Glide.with(getActivity())
                    .load(movie.getPosterImage())
                    .error(R.drawable.loading_error)
                    .placeholder(R.drawable.placeholder)
                    .into(poster);
        }

         title.setText(movie.getTitle());

        if(movie.getReleaseDate() != null){
            releaseDate.setText(formatter.format(movie.getReleaseDate()));
        }
        else {
            releaseDate.setText("Not Available");
            //releaseDate.setTextColor();
        }

        if(movie.getRating()!=0.0){
            rating.setText(movie.getRating()+"");
        }
        else {
            rating.setText("NA");
        }

        if(movie.getRating()!=0.0){
            synopsis.setText(movie.getSynopsis());
        }
        else {
            synopsis.setText("Overview not found");
        }



        return mFragmentView;
    }
}
