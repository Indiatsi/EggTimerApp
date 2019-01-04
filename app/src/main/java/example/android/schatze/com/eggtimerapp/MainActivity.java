package example.android.schatze.com.eggtimerapp;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar sBar;
    TextView timer;
    Boolean isCounterActive = false;
    Button controlButton;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sBar = findViewById(R.id.seekBar);
        timer = findViewById(R.id.timer);
        controlButton = findViewById(R.id.controlTimer);

        sBar.setMax(600);
        sBar.setProgress(30);

        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void updateTimer(int secondsLeft) {
        int minutes = (int) secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String secondsString = Integer.toString(seconds);
        String minutesString = Integer.toString(minutes);

        if (minutesString.equalsIgnoreCase("0")) {
            minutesString = "00";
        } else if (minutes <= 9) {
            minutesString = "0" + minutesString;
        }

        if (secondsString.equalsIgnoreCase("0")) {
            secondsString = "00";
        } else if (seconds <= 9) {
            secondsString = "0" + secondsString;
        }

        timer.setText(minutesString + ":" + secondsString);
    }

    public void controlTimer(View view) {

        if (isCounterActive == false) {

            isCounterActive = true;
            sBar.setEnabled(false);
            controlButton.setText("STOP");

            countDownTimer = new CountDownTimer(sBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    timer.setText("00:00");
                    resetTimer();
                }
            }.start();
        } else {
            resetTimer();
        }
    }

    public void resetTimer() {
        timer.setText("00:30");
        sBar.setProgress(30);
        sBar.setEnabled(true);
        controlButton.setText("GO");
        countDownTimer.cancel();
        isCounterActive = false;
    }
}
