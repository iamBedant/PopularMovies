package com.example.bebi2.popularmovies.ui.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bebi2.popularmovies.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieListFragment extends Fragment {

    public MovieListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_list, container, false);
    }
}