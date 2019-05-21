package com.example.footballplayer;

import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.footballplayer.data.FootballContract;

public class activity_add_player extends AppCompatActivity {
    private int mHowGood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
    }

    /**
     * onClickAddTask is called when the "ADD" button is clicked.
     * It retrieves user input and inserts that new task data into the underlying database.
     */
    public void onClickAddPlayer(View view) {
// Not yet implemented
        // COMPLETED (6) Check if EditText is empty, if not retrieve input and store it in a ContentValues object
        // If the EditText input is empty -> don't create an entry
        String name = ((EditText) findViewById(R.id.editTextPlayerName)).getText().toString();
        String team = ((EditText) findViewById(R.id.editTextPlayerTeam)).getText().toString();
        String num = ((EditText) findViewById(R.id.editTextPlayerNum)).getText().toString();
        if (name.length() == 0 || team.length() == 0 || num.length() == 0) {
            return;
        }

        // COMPLETED (7) Insert new task data via a ContentResolver
        // Create new empty ContentValues object
        ContentValues contentValues = new ContentValues();

        contentValues.put(FootballContract.FootballEntry.COLUMN_NAME, name);
        contentValues.put(FootballContract.FootballEntry.COLUMN_TEAM, team);
        contentValues.put(FootballContract.FootballEntry.COLUMN_NUM, num);
        contentValues.put(FootballContract.FootballEntry.COLUMN_START, mHowGood);

        Uri s = FootballContract.FootballEntry.CONTENT_URI;
        // Insert the content values via a ContentResolver
        Uri uri = getContentResolver().insert(FootballContract.FootballEntry.CONTENT_URI, contentValues);

        // COMPLETED (8) Display the URI that's returned with a Toast
        // [Hint] Don't forget to call finish() to return to MainActivity after this insert is complete
        if(uri != null) {
            Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
        }

        // Finish activity (this returns back to MainActivity)
        finish();

    }


    /**
     * onPrioritySelected is called whenever a priority button is clicked.
     * It changes the value of mPriority based on the selected button.
     */
    public void onPrioritySelected(View view) {
        if (((RadioButton) findViewById(R.id.radButton1)).isChecked()) {
            mHowGood = 1;
        } else if (((RadioButton) findViewById(R.id.radButton2)).isChecked()) {
            mHowGood = 2;
        } else if (((RadioButton) findViewById(R.id.radButton3)).isChecked()) {
            mHowGood = 3;
        }
    }
}
