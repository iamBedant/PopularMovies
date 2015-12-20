package com.example.bebi2.popularmovies.Config;

/**
 * Created by bebi2 on 12/19/2015.
 */
public class Config {

    /*
     ---------------- API Urls -------------------
     */
    public static final String API_BASE_URL = "http://api.themoviedb.org/3/discover/movie?";
    public static final String API_GET_MOVIE_LIST = "";
    public static final String API_KEY="api_key=466533c74b7492ae259d6e77554bb96a";
    public static final String API_IMAGE_BASE_URL_POSTER="http://image.tmdb.org/t/p/w185";
    public static final String API_IMAGE_BASE_URL_BACKDROP="http://image.tmdb.org/t/p/w342";




    /*
     ---------------- BUNDLE Strings -------------------
     */

    public static final String BUNDLE_MOVIES ="_movies";
    public static final String BUNDLE_SINGLE_MOVIES ="_single_movie";
    public static final String BUNDLE_SORT_ORDER ="_sort_order";


    /*
     ---------------- SORT ORDER Strings -------------------
     */
    public static final String SORT_BY_RATING="sort_by=vote_average.desc&";
    public static final String SORT_BY_POPULARITY="sort_by=popularity.desc&";


}
