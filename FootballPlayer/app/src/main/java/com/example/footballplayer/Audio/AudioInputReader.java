package com.example.footballplayer.Audio;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.os.Build;

import com.example.footballplayer.R;

public class AudioInputReader {
    private final Context mContext;
    private MediaPlayer mPlayer;

    public AudioInputReader(Context context) {
        this.mContext = context;
        initVisualizer();
    }

    private void initVisualizer() {
        // Setup media player
        mPlayer = MediaPlayer.create(mContext, R.raw.music);
        mPlayer.setLooping(true);

        // Setup the Visualizer
        // Connect it to the media player


        // Start everything
        mPlayer.start();
    }

    public void shutdown(boolean isFinishing) {

        if (mPlayer != null) {
            mPlayer.pause();
            if (isFinishing) {
                mPlayer.release();
                mPlayer = null;
            }
        }

    }

    public void restart() {

        if (mPlayer != null) {
            mPlayer.start();
        }
    }
}
