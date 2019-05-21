package com.example.footballplayer.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class FootballContract {
    // The authority, which is how your code knows which Content Provider to access
    public static final String AUTHORITY = "com.example.footballplayer";

    // The base content URI = "content://" + <authority>
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    // Define the possible paths for accessing data in this contract
    // This is the path for the "tasks" directory
    public static final String PATH_PLAYERS = "players";



    public static final class FootballEntry implements BaseColumns {

        // TaskEntry content URI = base content URI + path
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PLAYERS).build();


        // Task table and column names
        public static final String TABLE_NAME = "players";

        // Since TaskEntry implements the interface "BaseColumns", it has an automatically produced
        // "_ID" column in addition to the two below
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_TEAM = "team";
        public static final String COLUMN_NUM = "num";
        public static final String COLUMN_START = "start";


        /*
        The above table structure looks something like the sample table below.
        With the name of the table and columns on top, and potential contents in rows

        Note: Because this implements BaseColumns, the _id column is generated automatically

        players
         - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        | _id  |    Name     |    Team   |    No   |    Start  |
         - - - - - - - - - - - - - - - - - - - - - - - - - - - -

         */

    }
}
