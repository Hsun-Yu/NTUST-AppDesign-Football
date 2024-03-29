package com.example.footballplayer.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.footballplayer.data.FootballContract.FootballEntry;


public class FootballDBHelper  extends SQLiteOpenHelper {
    // The name of the database
    private static final String DATABASE_NAME = "footballplayerDb.db";

    // If you change the database schema, you must increment the database version
    private static final int VERSION = 2;


    // Constructor
    FootballDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    /**
     * Called when the tasks database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create tasks table (careful to follow SQL formatting rules)
        final String CREATE_TABLE = "CREATE TABLE "  + FootballEntry.TABLE_NAME + " (" +
                FootballEntry._ID                + " INTEGER PRIMARY KEY, " +
                FootballEntry.COLUMN_NAME        + " TEXT NOT NULL, " +
                FootballEntry.COLUMN_TEAM        + " TEXT NOT NULL," +
                FootballEntry.COLUMN_NUM         + " INTEGER NOT NULL," +
                FootballEntry.COLUMN_START       + " INTEGER NOT NULL);";

        db.execSQL(CREATE_TABLE);
    }


    /**
     * This method discards the old table of data and calls onCreate to recreate a new one.
     * This only occurs when the version number for this database (DATABASE_VERSION) is incremented.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FootballEntry.TABLE_NAME);
        onCreate(db);
    }
}
