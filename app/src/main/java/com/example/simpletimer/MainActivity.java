package com.example.simpletimer;

import androidx.appcompat.app.AppCompatActivity;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
{
    int time = 0;
    MediaPlayer mediaPlayer;

    public void onClick (View view)
    {
        int millisInFuture = time*1000;
        Log.i("Info", Integer.toString(millisInFuture));
        new CountDownTimer(millisInFuture, 1000)
        {
            public void onTick(long millisecondsUntilDone)
            {
                Log.i("Seconds left", Long.toString(millisecondsUntilDone/1000));
            }

            @Override
            public void onFinish()
            {
                mediaPlayer.start();
            }
        }.start();
    }

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.alarm);

        SeekBar timerSeekBar = (SeekBar) findViewById(R.id.seekBar);
        final TextView timerTextView = (TextView) findViewById(R.id.textView);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);
        time = 30;

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                time = i;
                int minutes = i / 60;
                int seconds = i - (minutes * 60);

                String secondsString;

                if (seconds == 0)
                {
                    secondsString = "00";
                }
                else if (seconds < 10)
                {
                    secondsString = "0".concat(Integer.toString(seconds));
                }
                else
                {
                    secondsString = Integer.toString(seconds);
                }


                timerTextView.setText(Integer.toString(minutes)+":"+secondsString);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

    }
}
