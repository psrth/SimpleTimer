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

    // variables to keep track of time left at different points
    int time = 0;
    int millisec;

    // variables to keep track of the states of the timer
    boolean isActive = false;
    boolean pause;

    // creating new objects for the different elements
    MediaPlayer mediaPlayer;
    TextView timerTextView;
    CountDownTimer countDownTimer;
    SeekBar timerSeekBar;
    Button restartButton;
    Button pauseButton;


    // method run when go/restart button is clicked
    public void onClick (View view) {

        // if the timer is not running
        if (!isActive) {
            isActive = true;
            restartButton.setText("RESET");
            timerSeekBar.setEnabled(false);
            pauseButton.setVisibility(View.VISIBLE);
            pause = false;

            final int millisInFuture = time * 1000;
            Log.i("Info", Integer.toString(millisInFuture));

            // countdown started
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

                    // text view that displays the time left
                    timerTextView.setText(Integer.toString(minutes) + ":" + secondsString);

                    // stores the last countdown value in case user pauses
                    millisec = (int) millisecondsUntilDone;
                }

                // once countdown finishes, the alarm is played
                @Override
                public void onFinish() {
                    mediaPlayer.start();
                }
            }.start();

        // in case the timer is active or the timer is paused — and then reset
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

    // method run when the user clicks the pause/play button
    public void onClickPause (View view) {

        // if the timer was currently not paused
        if (!pause) {
            pause = true;
            countDownTimer.cancel();
            pauseButton.setText("RESUME");

        // if the timer was paused
        } else {
            pause = false;

            // a new countdown is created with the last recorded value
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


    // on create method initialises all the objects for the various elements used
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.alarm);
        restartButton =  (Button) findViewById(R.id.restartButton);
        pauseButton = (Button) findViewById(R.id.pauseButton);

        timerSeekBar = (SeekBar) findViewById(R.id.seekBar);
        timerTextView = (TextView) findViewById(R.id.textView);

        // the default value of the seekbar is one minute, max value is 5 minutes
        timerSeekBar.setMax(300);
        timerSeekBar.setProgress(60);
        time = 60;

        // listener for whenever the timer seek bar is changed
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

                // text changes responding to the user's scrubbing
                timerTextView.setText(Integer.toString(minutes)+":"+secondsString);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar){}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar){}
        });

    }
}

// thanks for reading till here, i appreciate it
// a: parth sharma, v: 1.5, d: 14-05-20 ————— SIMPLE TIMER APP