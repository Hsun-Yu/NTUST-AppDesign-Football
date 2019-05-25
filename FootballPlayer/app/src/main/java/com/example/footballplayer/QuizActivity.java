package com.example.footballplayer;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footballplayer.data.FootballContentProvider;
import com.example.footballplayer.data.FootballContract;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Random;

public class QuizActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor>{
    Cursor mCursor;
    private static final int PLAYER_LOADER_ID = 0;
    int position = 0;

    int answer;

    TextView mQuestionCountTextView;
    TextView mQuestionTextView;
    RadioButton mRadioButtons[];
    Button mConfirmButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionCountTextView = findViewById(R.id.text_view_question_count);
        mQuestionTextView = findViewById(R.id.text_view_question);
        mRadioButtons = new RadioButton[3];

        mRadioButtons[0] = findViewById(R.id.radio_button_question1);
        mRadioButtons[1] = findViewById(R.id.radio_button_question2);
        mRadioButtons[2] = findViewById(R.id.radio_button_question3);

        mConfirmButton = findViewById(R.id.button_confirm_next);
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  int myAns = 0;
                  for(int i = 0; i < 3; i++)
                  {
                      if(mRadioButtons[i].isChecked())
                      {
                          myAns = i;
                          break;
                      }
                  }

                  if(myAns == answer)
                  {
                      ((ImageView)findViewById(R.id.image_wrong)).setVisibility(View.INVISIBLE);
                      if(++position >= mCursor.getCount())
                      {
                          Toast.makeText(getBaseContext(), "You have done every questions!! Good job!!", Toast.LENGTH_LONG).show();
                          finish();
                      }
                      else
                          processCursor();
                  }
                  else
                  {
                      ((ImageView)findViewById(R.id.image_wrong)).setVisibility(View.VISIBLE);
                  }

              }

        });
    }

    @Nullable
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new AsyncTaskLoader<Cursor>(this) {

            // Initialize a Cursor, this will hold all the task data
            Cursor mPlayerData = null;

            // onStartLoading() is called when a loader first starts loading data
            @Override
            protected void onStartLoading() {
                if (mPlayerData != null) {
                    // Delivers any previously loaded data immediately
                    deliverResult(mPlayerData);
                } else {
                    // Force a new load
                    forceLoad();
                }
            }

            // loadInBackground() performs asynchronous loading of data
            @Override
            public Cursor loadInBackground() {
                try {
                        return getContentResolver().query(FootballContract.FootballEntry.CONTENT_URI,
                                null,
                                null,
                                null,
                                null);

                } catch (Exception e) {
//                    Log.e(TAG, "Failed to asynchronously load data.");
                    e.printStackTrace();
                    return null;
                }
            }

            // deliverResult sends the result of the load, a Cursor, to the registered listener
            public void deliverResult(Cursor data) {
                mPlayerData = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        mCursor = cursor;
        position = 0;
        processCursor();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().restartLoader(PLAYER_LOADER_ID, null, this);
    }

    void processCursor()
    {
        // Indices for the _id, description, and priority columns
        int idIndex = mCursor.getColumnIndex(FootballContract.FootballEntry._ID);
        int nameIndex = mCursor.getColumnIndex(FootballContract.FootballEntry.COLUMN_NAME);
        int teamIndex = mCursor.getColumnIndex(FootballContract.FootballEntry.COLUMN_TEAM);
        int startIndex = mCursor.getColumnIndex(FootballContract.FootballEntry.COLUMN_START);
        int numIndex = mCursor.getColumnIndex(FootballContract.FootballEntry.COLUMN_NUM);

        mCursor.moveToPosition(position); // get to the right location in the cursor

        // Determine the values of the wanted data
        final int id = mCursor.getInt(idIndex);
        String name = mCursor.getString(nameIndex);
        String team = mCursor.getString(teamIndex);
        int num = mCursor.getInt(numIndex);
//        int start = mCursor.getInt(startIndex);

        mQuestionCountTextView.setText("Question: " + (position + 1) + "/" + mCursor.getCount());
        mQuestionTextView.setText("What is " + name + "'s number");

        Random r = new Random();
        answer = r.nextInt(3);

        mRadioButtons[answer].setText("" + num);

        for(int i = 1; i <= 2; i++)
            mRadioButtons[(answer + i) % 3].setText("" + (num + (r.nextInt(10) + 1)));
    }
}
