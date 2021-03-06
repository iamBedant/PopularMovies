package com.example.bebi2.popularmovies.DataBase;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.media.UnsupportedSchemeException;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.bebi2.popularmovies.DataBase.FavouriteMovieContract.FavouriteMovieEntry;
import com.example.bebi2.popularmovies.DataBase.FavouriteMovieContract.FavouriteMovieTrailer;
import com.example.bebi2.popularmovies.DataBase.FavouriteMovieContract.FavouriteMovieReview;

/**
 * Created by bebi2 on 12/23/2015.
 */
public class FavouriteMovieProvider extends ContentProvider {


    private static final int FAVOURITE_MOVIES = 100;
    private static final int FAVOURITE_MOVIE = 101;

    private static final int FAVOURITE_MOVIE_REVIEWS = 200;
    private static final int FAVOURITE_MOVIE_REVIEW = 201;

    private static final int FAVOURITE_MOVIE_TRAILERS = 300;
    private static final int FAVOURITE_MOVIE_TRAILER = 301;

    private FavouriteMovieDbHelper mOpenHelper;

    private static final UriMatcher sUriMatcher = buildUriMatcher();


    private static final SQLiteQueryBuilder sTrailersByMovieQueryBuilder;

    static {
        sTrailersByMovieQueryBuilder = new SQLiteQueryBuilder();
        sTrailersByMovieQueryBuilder.setTables(
                FavouriteMovieEntry.TABLE_NAME+ " INNER JOIN " +
                        FavouriteMovieTrailer.TABLE_NAME +
                        " ON " + FavouriteMovieEntry.TABLE_NAME +
                        "." + FavouriteMovieEntry.COLUMN_MOVIE_ID +
                        " = " + FavouriteMovieTrailer.TABLE_NAME +
                        "." + FavouriteMovieTrailer._ID);
    }

   



    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = FavouriteMovieContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, FavouriteMovieContract.PATH_FAVOURITE_MOVIES, FAVOURITE_MOVIES);
        matcher.addURI(authority, FavouriteMovieContract.PATH_FAVOURITE_MOVIES + "/*", FAVOURITE_MOVIE);

        matcher.addURI(authority, FavouriteMovieContract.PATH_FAVOURITE_MOVIE_REVIEW + "/*", FAVOURITE_MOVIE_REVIEWS);
        matcher.addURI(authority, FavouriteMovieContract.PATH_FAVOURITE_MOVIE_REVIEW + "/*/*", FAVOURITE_MOVIE_REVIEW);

        matcher.addURI(authority, FavouriteMovieContract.PATH_FAVOURITE_MOVIE_TRAILER + "/*", FAVOURITE_MOVIE_TRAILERS);
        matcher.addURI(authority, FavouriteMovieContract.PATH_FAVOURITE_MOVIE_TRAILER + "/*/*", FAVOURITE_MOVIE_TRAILER);

        return matcher;
    }


    @Override
    public boolean onCreate() {
        mOpenHelper = new FavouriteMovieDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        Cursor retCursor;

        switch (sUriMatcher.match(uri)) {
            case FAVOURITE_MOVIES:
                retCursor = mOpenHelper.getReadableDatabase().query(
                        FavouriteMovieEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case FAVOURITE_MOVIE:
                retCursor = null;
                break;
            case FAVOURITE_MOVIE_REVIEWS:
                retCursor = mOpenHelper.getReadableDatabase().query(
                        FavouriteMovieReview.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case FAVOURITE_MOVIE_REVIEW:
                retCursor = null;
                break;
            case FAVOURITE_MOVIE_TRAILERS:
                retCursor = mOpenHelper.getReadableDatabase().query(
                        FavouriteMovieTrailer.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case FAVOURITE_MOVIE_TRAILER:
                retCursor = null;
                break;
            default:
                throw new UnsupportedOperationException("Unknown Uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);

        return retCursor;
    }

    @Nullable


    @Override
    public String getType(Uri uri) {

        final int match = sUriMatcher.match(uri);

        switch (match) {
            case FAVOURITE_MOVIES:
                return FavouriteMovieContract.FavouriteMovieEntry.CONTENT_TYPE;
            case FAVOURITE_MOVIE:
                return FavouriteMovieContract.FavouriteMovieEntry.CONTENT_ITEM_TYPE;

            case FAVOURITE_MOVIE_REVIEWS:
                return FavouriteMovieContract.FavouriteMovieReview.CONTENT_TYPE;
            case FAVOURITE_MOVIE_REVIEW:
                return FavouriteMovieContract.FavouriteMovieReview.CONTENT_ITEM_TYPE;

            case FAVOURITE_MOVIE_TRAILERS:
                return FavouriteMovieContract.FavouriteMovieTrailer.CONTENT_TYPE;

            case FAVOURITE_MOVIE_TRAILER:
                return FavouriteMovieContract.FavouriteMovieTrailer.CONTENT_ITEM_TYPE;

            default:
                throw new UnsupportedOperationException("Unknown Uri: " + uri);


        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
