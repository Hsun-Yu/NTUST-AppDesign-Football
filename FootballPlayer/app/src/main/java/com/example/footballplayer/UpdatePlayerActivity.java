package com.example.footballplayer;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footballplayer.data.FootballContract;

public class UpdatePlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_player);

        Intent intent = getIntent();
        String playerName = intent.getStringExtra(CustomCursorAdapter.PLAYER_NAME);
        String playerTeam = intent.getStringExtra(CustomCursorAdapter.PLAYER_TEAM);
        int playerNum = intent.getIntExtra(CustomCursorAdapter.PLAYER_NUM, 0);
        int playerStart = intent.getIntExtra(CustomCursorAdapter.PLAYER_START, 0);
        final int playerId = intent.getIntExtra(CustomCursorAdapter.PLAYER_ID, 0);
        ((EditText)findViewById(R.id.editTextUpdatePlayerName)).setText(playerName);
        ((EditText)findViewById(R.id.editTextUpdatePlayerTeam)).setText(playerTeam);
        ((EditText)findViewById(R.id.editTextUpdatePlayerNum)).setText("" + playerNum);

        ((RatingBar)findViewById(R.id.update_ratingbar)).setRating(playerStart);


        Button updateButton = findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = ((EditText) findViewById(R.id.editTextUpdatePlayerName)).getText().toString();
                    String team = ((EditText) findViewById(R.id.editTextUpdatePlayerTeam)).getText().toString();
                    String num = ((EditText) findViewById(R.id.editTextUpdatePlayerNum)).getText().toString();
                    int start =(int) ((RatingBar) findViewById(R.id.update_ratingbar)).getRating();
                    if (name.length() == 0 || team.length() == 0 || num.length() == 0) {
                        return;
                    }

                    // Insert new task data via a ContentResolver
                    // Create new empty ContentValues object
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(FootballContract.FootballEntry.COLUMN_NAME, name);
                    contentValues.put(FootballContract.FootballEntry.COLUMN_TEAM, team);
                    contentValues.put(FootballContract.FootballEntry.COLUMN_NUM, num);
                    contentValues.put(FootballContract.FootballEntry.COLUMN_START, start);

                    String stringId = Integer.toString(playerId);

                    Uri uri = FootballContract.FootballEntry.CONTENT_URI;
                    uri = uri.buildUpon().appendPath(stringId).build();
                    // Insert the content values via a ContentResolver
                    int updatedRowCount =
                            getContentResolver().update(uri, contentValues, null, null);

                    // COMPLETED (8) Display the URI that's returned with a Toast
                    // [Hint] Don't forget to call finish() to return to MainActivity after this insert is complete
//                    if(uri != null) {
//                        Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
//                    }

                    // Finish activity (this returns back to MainActivity)
                    finish();

                }
            }
        );
    }
}
