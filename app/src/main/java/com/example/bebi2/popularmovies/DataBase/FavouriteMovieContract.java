package com.example.bebi2.popularmovies.DataBase;

import android.provider.BaseColumns;

/**
 * Created by bebi2 on 12/22/2015.
 */
public class FavouriteMovieContract {

    public static final class FavouriteMovieEntry implements BaseColumns {

        public static final String TABLE_NAME = "favourive_movie";

        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_MOVIE_TITLE = "movie_title";
        public static final String COLUMN_MOVIE_POSTER = "movie_poster";
        public static final String COLUMN_MOVIE_BACKDROP = "movie_backdrop";
        public static final String COLUMN_MOVIE_RATING = "movie_rating";
        public static final String COLUMN_MOVIE_SYNOPSIS = "movie_synopsos";
        public static final String COLUMN_MOVIE_RELEASE_DATE = "movie_release_date";

    }

    public static final class FavouriteMovieReview implements BaseColumns {
        public static final String TABLE_NAME = "movie_review";

        public static final String COLUMN_REVIEW_ID = "review_id";
        public static final String COLUMN_REVIEW_AUTHOR = "review_author";
        public static final String COLUMN_REVIEW_CONTENT = "review_content";
        public static final String COLUMN_REVIEW_URL = "review_url";

    }

    public static final class FavouriteMovieTrailer implements BaseColumns {
        public static final String TABLE_NAME = "movie_trailer";

        public static final String COLUMN_TRAILER_ID = "trailer_id";
        public static final String COLUMN_TRAILER_TITLE = "trailer_title";
        public static final String COLUMN_TRAILER_TYPE = "trailer_type";
        public static final String COLUMN_TRAILER_KEY = "trailer_key";

    }

}
