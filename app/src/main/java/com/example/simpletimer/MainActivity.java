package com.example.simpletimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    boolean timerState = false;

    public void setTimer(int i)
    {
        TextView timerValue = (TextView) findViewById(R.id.textView);
        int minutes = i/6000;
        int seconds = (i%6000)/100;
        Log.i("hi", "whats the issu1e");

        timerValue.setText(minutes+":"+seconds);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar timeSeekBar = (SeekBar) findViewById(R.id.seekBar);
        timeSeekBar.setMax(20);

        Log.i("hi", "whats the issu2e");
        timeSeekBar.setProgress(10);
        setTimer(10);

        timeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                setTimer(i*3000);
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
