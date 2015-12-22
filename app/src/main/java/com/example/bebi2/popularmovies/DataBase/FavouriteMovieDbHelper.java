package com.example.bebi2.popularmovies.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.bebi2.popularmovies.DataBase.FavouriteMovieContract.FavouriteMovieEntry;
import com.example.bebi2.popularmovies.DataBase.FavouriteMovieContract.FavouriteMovieTrailer;
import com.example.bebi2.popularmovies.DataBase.FavouriteMovieContract.FavouriteMovieReview;

/**
 * Created by bebi2 on 12/22/2015.
 */
public class FavouriteMovieDbHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "favourite_movie.db";

    public FavouriteMovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_FAVOURITE_MOVIE_TABLE =
                "CREATE TABLE " + FavouriteMovieEntry.TABLE_NAME + " (" +
                        FavouriteMovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        FavouriteMovieEntry.COLUMN_MOVIE_ID + " TEXT NOT NULL, " +
                        FavouriteMovieEntry.COLUMN_MOVIE_TITLE + " TEXT NOT NULL, " +
                        FavouriteMovieEntry.COLUMN_MOVIE_BACKDROP + " TEXT, " +
                        FavouriteMovieEntry.COLUMN_MOVIE_POSTER + " TEXT, " +
                        FavouriteMovieEntry.COLUMN_MOVIE_SYNOPSIS + " TEXT, " +
                        FavouriteMovieEntry.COLUMN_MOVIE_RATING + " REAL, " +
                        FavouriteMovieEntry.COLUMN_MOVIE_RELEASE_DATE + " TEXT, " +
                        "UNIQUE (" + FavouriteMovieEntry.COLUMN_MOVIE_ID + ") ON CONFLICT REPLACE);";

        final String SQL_CREATE_FAVOURITE_MOVIE_TRAILER_TABLE =
                "CREATE TABLE " + FavouriteMovieTrailer.TABLE_NAME + " (" +
                        FavouriteMovieTrailer._ID + " TEXT PRIMARY KEY, " +
                        FavouriteMovieTrailer.COLUMN_TRAILER_ID + " TEXT NOT NULL, " +
                        FavouriteMovieTrailer.COLUMN_TRAILER_KEY + " TEXT NOT NULL, " +
                        FavouriteMovieTrailer.COLUMN_TRAILER_TITLE + " TEXT, " +
                        FavouriteMovieTrailer.COLUMN_TRAILER_TYPE + " TEXT, " +

                        "FOREIGN KEY (" + FavouriteMovieTrailer.COLUMN_TRAILER_ID + ") REFERENCES " +
                        FavouriteMovieEntry.TABLE_NAME + "(" + FavouriteMovieEntry.COLUMN_MOVIE_ID + ")," +

                        "UNIQUE (" + FavouriteMovieTrailer._ID + ") ON CONFLICT IGNORE);";


        final String SQL_CREATE_FAVOURITE_MOVIE_REVIEW_TABLE =
                "CREATE TABLE " + FavouriteMovieReview.TABLE_NAME + " (" +
                        FavouriteMovieReview._ID + " TEXT PRIMARY KEY, " +
                        FavouriteMovieReview.COLUMN_REVIEW_ID + " TEXT NOT NULL, " +
                        FavouriteMovieReview.COLUMN_REVIEW_AUTHOR + " TEXT NOT NULL, " +
                        FavouriteMovieReview.COLUMN_REVIEW_CONTENT + " TEXT, " +
                        FavouriteMovieReview.COLUMN_REVIEW_URL + " TEXT, " +

                        "FOREIGN KEY (" + FavouriteMovieReview.COLUMN_REVIEW_ID + ") REFERENCES " +
                        FavouriteMovieEntry.TABLE_NAME + "(" + FavouriteMovieEntry.COLUMN_MOVIE_ID + ")," +
                        "UNIQUE (" + FavouriteMovieReview._ID + ") ON CONFLICT IGNORE);";


        db.execSQL(SQL_CREATE_FAVOURITE_MOVIE_TABLE);
        db.execSQL(SQL_CREATE_FAVOURITE_MOVIE_REVIEW_TABLE);
        db.execSQL(SQL_CREATE_FAVOURITE_MOVIE_TRAILER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ FavouriteMovieEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ FavouriteMovieReview.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ FavouriteMovieTrailer.TABLE_NAME);

        onCreate(db);
    }
}
