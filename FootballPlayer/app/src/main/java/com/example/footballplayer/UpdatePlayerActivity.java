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

        ((EditText)findViewById(R.id.editTextUpdatePlayerName)).setText(playerName);
        ((EditText)findViewById(R.id.editTextUpdatePlayerTeam)).setText(playerTeam);
        ((EditText)findViewById(R.id.editTextUpdatePlayerNum)).setText("" + playerNum);

        ((RatingBar)findViewById(R.id.update_ratingbar)).setRating(playerStart);


        Button updateButton = findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }
        );
    }
}
