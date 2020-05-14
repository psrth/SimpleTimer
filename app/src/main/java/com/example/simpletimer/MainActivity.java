package com.example.simpletimer;

import androidx.appcompat.app.AppCompatActivity;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    int time = 0;
    MediaPlayer mediaPlayer;
    TextView timerTextView;
    boolean isActive = false;
    CountDownTimer countDownTimer;
    SeekBar timerSeekBar;
    Button restartButton;
    Button pauseButton;
    boolean pause;
    int millisec;


    public void onClick (View view) {
        if (!isActive) {
            isActive = true;
            restartButton.setText("RESET");
            timerSeekBar.setEnabled(false);
            pauseButton.setVisibility(View.VISIBLE);
            pause = false;

            final int millisInFuture = time * 1000;
            Log.i("Info", Integer.toString(millisInFuture));

            countDownTimer = new CountDownTimer(millisInFuture, 1000) {
                public void onTick(long millisecondsUntilDone) {
                    int milli = (int) millisecondsUntilDone / 1000;
                    Log.i("Seconds left", Long.toString(millisecondsUntilDone / 1000));

                    int minutes = milli / 60;
                    int seconds = milli - (minutes * 60);

                    String secondsString;

                    if (seconds == 0) {
                        secondsString = "00";
                    } else if (seconds < 10) {
                        secondsString = "0".concat(Integer.toString(seconds));
                    } else {
                        secondsString = Integer.toString(seconds);
                    }

                    timerTextView.setText(Integer.toString(minutes) + ":" + secondsString);

                    millisec = (int) millisecondsUntilDone;
                }

                @Override
                public void onFinish() {
                    mediaPlayer.start();
                }
            }.start();

        } else if (isActive || pause) {
            isActive = false;
            timerTextView.setText("0:30");
            countDownTimer.cancel();
            timerSeekBar.setEnabled(true);
            timerSeekBar.setProgress(30);
            restartButton.setText("GO!");
            pauseButton.setVisibility(View.INVISIBLE);
        }
    }

    public void onClickPause (View view) {
        if (!pause) {
            pause = true;
            countDownTimer.cancel();
            pauseButton.setText("RESUME");
        } else {
            pause = false;

            countDownTimer = new CountDownTimer(millisec, 1000) {
                public void onTick(long millisecondsUntilDone) {
                    int milli = (int) millisecondsUntilDone / 1000;
                    Log.i("Seconds left", Long.toString(millisecondsUntilDone / 1000));

                    int minutes = milli / 60;
                    int seconds = milli - (minutes * 60);

                    String secondsString;

                    if (seconds == 0) {
                        secondsString = "00";
                    } else if (seconds < 10) {
                        secondsString = "0".concat(Integer.toString(seconds));
                    } else {
                        secondsString = Integer.toString(seconds);
                    }

                    timerTextView.setText(Integer.toString(minutes) + ":" + secondsString);

                    millisec = (int) millisecondsUntilDone;
                }

                @Override
                public void onFinish() {
                    mediaPlayer.start();
                }
            }.start();
        }
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.alarm);
        restartButton =  (Button) findViewById(R.id.restartButton);
        pauseButton = (Button) findViewById(R.id.pauseButton);

        timerSeekBar = (SeekBar) findViewById(R.id.seekBar);
        timerTextView = (TextView) findViewById(R.id.textView);

        timerSeekBar.setMax(300);
        timerSeekBar.setProgress(60);
        time = 60;

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                time = i;
                int minutes = i / 60;
                int seconds = i - (minutes * 60);

                String secondsString;

                if (seconds == 0) {
                    secondsString = "00";
                } else if (seconds < 10) {
                    secondsString = "0".concat(Integer.toString(seconds));
                } else {
                    secondsString = Integer.toString(seconds);
                }

                timerTextView.setText(Integer.toString(minutes)+":"+secondsString);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar){}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar){}
        });

    }
}
