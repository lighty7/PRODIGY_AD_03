package com.example.stopwatchapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int minutes = 0;
    private int seconds = 0;
    private int milliseconds = 0;
    private boolean isRunning = false;
    private Handler handler;

    private TextView minutesTextView, secondsTextView, millisecondsTextView;
    private Button startButton, pauseButton, resetButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        minutesTextView = findViewById(R.id.minutesTextView);
        secondsTextView = findViewById(R.id.secondsTextView);
        millisecondsTextView = findViewById(R.id.millisecondsTextView);

        startButton = findViewById(R.id.startButton);
        pauseButton = findViewById(R.id.pauseButton);
        resetButton = findViewById(R.id.resetButton);

        handler = new Handler();
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = true;
                startButton.setEnabled(false);
                pauseButton.setEnabled(true);
                resetButton.setEnabled(false);
                startTimer();
            }
        });
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = false;
                startButton.setEnabled(true);
                pauseButton.setEnabled(false);
                resetButton.setEnabled(true);
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = false;
                startButton.setEnabled(true);
                pauseButton.setEnabled(false);
                resetButton.setEnabled(false);
                minutes = 0;
                seconds = 0;
                milliseconds = 0;
                updateDisplay();
            }
        });
    }
    private void startTimer() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (isRunning) {
                    milliseconds++;
                    if (milliseconds == 100) {
                        milliseconds = 0;
                        seconds++;
                        if (seconds == 60) {
                            seconds = 0;
                            minutes++;
                        }
                    }
                    updateDisplay();
                    handler.postDelayed(this, 10); // Update every 10 milliseconds
                }
            }
        });
    }
    private void updateDisplay() {
        minutesTextView.setText(String.format("%02d", minutes));
        secondsTextView.setText(String.format("%02d", seconds));
        millisecondsTextView.setText(String.format("%02d", milliseconds));
    }
}