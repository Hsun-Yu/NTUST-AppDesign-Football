package com.example.footballplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class PlayerDetailActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_detail);


        Intent intent = getIntent();
        String playerName = intent.getStringExtra(CustomCursorAdapter.PLAYER_NAME);
        String playerTeam = intent.getStringExtra(CustomCursorAdapter.PLAYER_TEAM);
        int playerNum = intent.getIntExtra(CustomCursorAdapter.PLAYER_NUM, 0);
        int playerStart = intent.getIntExtra(CustomCursorAdapter.PLAYER_START, 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(playerName);

        setSupportActionBar(toolbar);

        ((RatingBar)findViewById(R.id.start_detail_ratingbar)).setRating(playerStart);
        ((TextView)findViewById(R.id.player_team_detail_textview)).setText(playerTeam);
        ((TextView)findViewById(R.id.player_num_detail_textview)).setText("" + playerNum);
        if(playerStart == 1)
            ((TextView)findViewById(R.id.player_start_detail_view)).setText("He is not a Good player");
        else if(playerStart == 2)
            ((TextView)findViewById(R.id.player_start_detail_view)).setText("He is a good player");
        else if(playerStart == 3)
            ((TextView)findViewById(R.id.player_start_detail_view)).setText("He is awesome!!!");
    }
}
