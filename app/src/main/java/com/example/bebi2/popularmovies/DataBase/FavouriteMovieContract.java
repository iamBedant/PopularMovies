package com.example.bebi2.popularmovies.DataBase;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by bebi2 on 12/22/2015.
 */
public class FavouriteMovieContract {


    public static final String CONTENT_AUTHORITY = "com.example.bebi2.popularmovies";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);


    public static final String PATH_FAVOURITE_MOVIES = "favourite_movies";
    public static final String PATH_FAVOURITE_MOVIE_REVIEW = "movie_review";
    public static final String PATH_FAVOURITE_MOVIE_TRAILER = "movie_trailer";

    public static final class FavouriteMovieEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVOURITE_MOVIES).build();

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_FAVOURITE_MOVIES;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_FAVOURITE_MOVIES;

        public static final String TABLE_NAME = "favourive_movie";

        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_MOVIE_TITLE = "movie_title";
        public static final String COLUMN_MOVIE_POSTER = "movie_poster";
        public static final String COLUMN_MOVIE_BACKDROP = "movie_backdrop";
        public static final String COLUMN_MOVIE_RATING = "movie_rating";
        public static final String COLUMN_MOVIE_SYNOPSIS = "movie_synopsos";
        public static final String COLUMN_MOVIE_RELEASE_DATE = "movie_release_date";

        public static Uri buildMovieUri(String id) {
            return CONTENT_URI.buildUpon().appendPath(id).build();
        }

    }

    public static final class FavouriteMovieReview implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVOURITE_MOVIE_REVIEW).build();

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_FAVOURITE_MOVIE_REVIEW;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_FAVOURITE_MOVIE_REVIEW;

        public static final String TABLE_NAME = "movie_review";

        public static final String COLUMN_REVIEW_ID = "review_id";
        public static final String COLUMN_REVIEW_AUTHOR = "review_author";
        public static final String COLUMN_REVIEW_CONTENT = "review_content";
        public static final String COLUMN_REVIEW_URL = "review_url";

        public static Uri buildMovieReviewUri(String id) {
            return CONTENT_URI.buildUpon().appendPath(id).build();
        }

        public static Uri buildSingleMovieReviewUri(String id, String review_id){

            return CONTENT_URI.buildUpon().appendPath(id).appendPath(review_id).build();
        }


    }

    public static final class FavouriteMovieTrailer implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVOURITE_MOVIE_TRAILER).build();

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_FAVOURITE_MOVIE_TRAILER;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_FAVOURITE_MOVIE_TRAILER;

        public static final String TABLE_NAME = "movie_trailer";

        public static final String COLUMN_TRAILER_ID = "trailer_id";
        public static final String COLUMN_TRAILER_TITLE = "trailer_title";
        public static final String COLUMN_TRAILER_TYPE = "trailer_type";
        public static final String COLUMN_TRAILER_KEY = "trailer_key";


        public static Uri buildMovieTrailerUri(String id) {
            return CONTENT_URI.buildUpon().appendPath(id).build();
        }

        public static Uri buildSingleMovieTrailerUri(String id, String trailer_id){

            return CONTENT_URI.buildUpon().appendPath(id).appendPath(trailer_id).build();
        }

    }

}
