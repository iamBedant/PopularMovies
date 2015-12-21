package com.example.bebi2.popularmovies.ui.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.util.Util;
import com.example.bebi2.popularmovies.Application.AppController;
import com.example.bebi2.popularmovies.Config.Config;
import com.example.bebi2.popularmovies.Helper.CheckJson;
import com.example.bebi2.popularmovies.Helper.Utils;
import com.example.bebi2.popularmovies.Network.CustomRequest;
import com.example.bebi2.popularmovies.Pojo.Movie;
import com.example.bebi2.popularmovies.R;
import com.example.bebi2.popularmovies.ui.MovieListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MovieList extends AppCompatActivity {


    RecyclerView mRecyclerView;
    MovieListAdapter mAdapter;
    ArrayList<Movie> mMovieList = new ArrayList<Movie>();
    Context mContext;
    private SwipeRefreshLayout mSwipeContainer;
    private String SORT_ORDER = Config.SORT_BY_POPULARITY;
    private static final int DESIRED_GRID_COLUMN_WIDTH_DP = 300;
    String TAG = "MovieListActivity";
    // Parcelable layoutManagerSavedState;
    GridLayoutManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        mContext = this;
        if (savedInstanceState != null) {
            SORT_ORDER = savedInstanceState.getString(Config.BUNDLE_SORT_ORDER);
            mMovieList = savedInstanceState.getParcelableArrayList(Config.BUNDLE_MOVIES);
            //layoutManagerSavedState = savedInstanceState.getParcelable(Config.BUNDLE_SAVED_LAYOUT_MANAGER);
            // manager.onRestoreInstanceState(layoutManagerSavedState);

        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SetUpRecyclerView();
        if (mMovieList.isEmpty()) {
            getMovieList();
        }

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }


    private void showMovies(ArrayList<Movie> movies) {

        if (mSwipeContainer.isRefreshing()) {

            mSwipeContainer.setRefreshing(false);
        }
        mMovieList.clear();
        mMovieList.addAll(movies);
        mAdapter.notifyDataSetChanged();

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putString(Config.BUNDLE_SORT_ORDER, SORT_ORDER);
        outState.putParcelableArrayList(Config.BUNDLE_MOVIES, mMovieList);
        //outState.putParcelable(Config.BUNDLE_SAVED_LAYOUT_MANAGER, manager.onSaveInstanceState());
        super.onSaveInstanceState(outState);
    }

    private void SetUpRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.movies_list);
        mSwipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);


        int screenWidth = Utils.getScreenWidth(mContext);
        final int optimalColumnCount = Math.round(screenWidth / DESIRED_GRID_COLUMN_WIDTH_DP);


        mSwipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMovieList();
            }
        });
        mSwipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        mAdapter = new MovieListAdapter(mContext, mMovieList);


//        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
//        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);

        manager = new GridLayoutManager(mContext, optimalColumnCount, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        stopRefreshing();
        AppController.getInstance().getRequestQueue().cancelAll(TAG);
        super.onStop();
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "OnPause Called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "OnResume Called");

    }

    public void stopRefreshing() {
        mSwipeContainer.setRefreshing(false);
    }


    private void getMovieList() {

        String url = Config.API_BASE_URL + SORT_ORDER + Config.API_KEY;
        CustomRequest getMovieList = new CustomRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                parseJson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }
        };
        getMovieList.setTag(TAG);
        AppController.getInstance().addToRequestQueue(getMovieList);

    }


    private void parseJson(JSONObject response) {
        try {
            ArrayList<Movie> mTempMovieList = new ArrayList<>();
            if (CheckJson.contains(response, "results")) {
                JSONArray movies = response.getJSONArray("results");
                for (int i = 0; i < movies.length(); i++) {
                    JSONObject _movie = movies.getJSONObject(i);
                    Movie movie = new Movie();

                    if (CheckJson.contains(_movie, "title")) {
                        movie.setTitle(_movie.getString("title"));
                    }
                    if (CheckJson.contains(_movie, "backdrop_path")) {
                        movie.setBackdrop(Config.API_IMAGE_BASE_URL_BACKDROP + _movie.getString("backdrop_path"));
                    }
                    if (CheckJson.contains(_movie, "vote_average")) {
                        movie.setRating((float) _movie.getDouble("vote_average"));
                    }
                    if (CheckJson.contains(_movie, "poster_path")) {
                        movie.setPosterImage(Config.API_IMAGE_BASE_URL_POSTER + _movie.getString("poster_path"));
                    }
                    if (CheckJson.contains(_movie, "overview")) {
                        movie.setSynopsis(_movie.getString("overview"));
                    }
                    if (CheckJson.contains(_movie, "release_date")) {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Date date = formatter.parse(_movie.getString("release_date"));
                            movie.setReleaseDate(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                    if (movie.getTitle() != null && !movie.getTitle().isEmpty()) {
                        mTempMovieList.add(movie);
                    }


                }
                showMovies(mTempMovieList);
            }
        } catch (JSONException e) {
            Log.d(TAG, "Parsing Error");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_sort_popularity:
                SORT_ORDER = Config.SORT_BY_POPULARITY;
                getMovieList();
                return true;
            case R.id.action_sort_rating:
                SORT_ORDER = Config.SORT_BY_RATING;
                getMovieList();
                return true;
            case R.id.action_about:

                Intent intent = new Intent(mContext, About.class);
                mContext.startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
