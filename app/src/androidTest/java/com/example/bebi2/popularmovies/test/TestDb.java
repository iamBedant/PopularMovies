package com.example.bebi2.popularmovies.test;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.example.bebi2.popularmovies.DataBase.FavouriteMovieDbHelper;

/**
 * Created by bebi2 on 12/22/2015.
 */
public class TestDb extends AndroidTestCase {
    public void testCreateDb() throws Throwable {
        mContext.deleteDatabase(FavouriteMovieDbHelper.DATABASE_NAME);
        SQLiteDatabase db = new FavouriteMovieDbHelper(
                this.mContext).getWritableDatabase();
        assertEquals(true,db.isOpen());
        db.close();
    }
}
