package com.example.simpletimer;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
{

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar timerSeekBar = (SeekBar) findViewById(R.id.seekBar);
        final TextView timerTextView = (TextView) findViewById(R.id.textView);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
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
