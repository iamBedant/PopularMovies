package com.example.bebi2.popularmovies.test;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

import com.example.bebi2.popularmovies.DataBase.FavouriteMovieContract;
import com.example.bebi2.popularmovies.DataBase.FavouriteMovieDbHelper;
import com.example.bebi2.popularmovies.DataBase.FavouriteMovieContract.FavouriteMovieTrailer;
import com.example.bebi2.popularmovies.DataBase.FavouriteMovieContract.FavouriteMovieReview;
import com.example.bebi2.popularmovies.DataBase.FavouriteMovieContract.FavouriteMovieEntry;

/**
 * Created by bebi2 on 12/22/2015.
 */
public class TestDb extends AndroidTestCase {

    String LOG_TAG = " Test Tag";

    public void testCreateDb() throws Throwable {
        mContext.deleteDatabase(FavouriteMovieDbHelper.DATABASE_NAME);
        SQLiteDatabase db = new FavouriteMovieDbHelper(
                this.mContext).getWritableDatabase();
        assertEquals(true, db.isOpen());
        db.close();
    }

    public void testInsertReadDB() {
        String testId = "1234";
        String testPoster = "http://cretechs.com/poster.jpg";
        String testTitle = "Test Title Name";
        String testSynopsis = "jas  ask  aikl  ais asi a s hd asjd   asd ;aspu as jljasl j  iksdflksd b sdk;quihsdh dklsdkh  aolkljasdyh";
        String testBackdrop = "http://cretechs.com/backdrop.jpg";
        String testReleaseDate = "12-04-15";
        float testRating = (float) 1.2;

        FavouriteMovieDbHelper dbHelper = new FavouriteMovieDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FavouriteMovieEntry.COLUMN_MOVIE_ID, testId);
        values.put(FavouriteMovieEntry.COLUMN_MOVIE_POSTER, testPoster);
        values.put(FavouriteMovieEntry.COLUMN_MOVIE_TITLE, testTitle);
        values.put(FavouriteMovieEntry.COLUMN_MOVIE_SYNOPSIS, testSynopsis);
        values.put(FavouriteMovieEntry.COLUMN_MOVIE_BACKDROP, testBackdrop);
        values.put(FavouriteMovieEntry.COLUMN_MOVIE_RELEASE_DATE, testReleaseDate);
        values.put(FavouriteMovieEntry.COLUMN_MOVIE_RATING, testRating);

        long favouriteMovieId;
        favouriteMovieId = db.insert(FavouriteMovieEntry.TABLE_NAME, null, values);

        assertTrue(favouriteMovieId != -1);
        Log.d(LOG_TAG, "New Row Id: " + favouriteMovieId);

        String[] columns = {
                FavouriteMovieEntry._ID,
                FavouriteMovieEntry.COLUMN_MOVIE_ID,
                FavouriteMovieEntry.COLUMN_MOVIE_POSTER,
                FavouriteMovieEntry.COLUMN_MOVIE_TITLE,
                FavouriteMovieEntry.COLUMN_MOVIE_SYNOPSIS,
                FavouriteMovieEntry.COLUMN_MOVIE_BACKDROP,
                FavouriteMovieEntry.COLUMN_MOVIE_RELEASE_DATE,
                FavouriteMovieEntry.COLUMN_MOVIE_RATING
        };


        Cursor cursor = db.query(
                FavouriteMovieEntry.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(FavouriteMovieEntry.COLUMN_MOVIE_ID);
            String movieId = cursor.getString(index);
            int nameIndex = cursor.getColumnIndex(FavouriteMovieEntry.COLUMN_MOVIE_TITLE);
            String movieName = cursor.getString(nameIndex);

            assertEquals(testId,movieId);
            assertEquals(testTitle,movieName);
        }

        else {
            fail("No Values returned :(");
        }

    }

}
