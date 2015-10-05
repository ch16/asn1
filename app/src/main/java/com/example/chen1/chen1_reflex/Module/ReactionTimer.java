package com.example.chen1.chen1_reflex.Module;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen1.chen1_reflex.R;
import com.example.chen1.chen1_reflex.View.SingleUserActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by chen1 on 10/1/15.
 */
public class ReactionTimer extends Activity {
    //pass in the context to change Textview in the SingleUserActivity
    Context context;
    public ReactionTimer(Context context) {
        this.context=context;
    }

    //record the time when the time is up and when the user clicks to calculate the lag
    private double startTime;
    private double endTime;
    private double lag;
    private double displayLag;

    //create two timers and random time between 10 and 2000 ms
    private CountDownTimer ctimer;
    private CountDownTimer againTimer;
    private Random random = new Random();

    //check if the user clicks early, if the time is up or if the game has started yet
    private boolean early = false;
    private boolean timeUp = false;
    private boolean start = false;
    //create a boolean diplaying, if it's displaying result then do not react when the user clicks 
    private int randomTime;
    private boolean displaying = false;

    //the main timer of each round of refex game
    //reference http://developer.android.com/reference/android/os/CountDownTimer.html
    public void startTimer() {
        randomTime = random.nextInt(2000) + 10;
        ctimer = new CountDownTimer(randomTime, 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                displaying = false;
                TextView displayedResult = (TextView) ((Activity)context).findViewById(R.id.reaction_time_display);
                //while the time is not up, display Wait
                displayedResult.setText("Wait");
                timeUp = false;
            }

            @Override
            public void onFinish() {
                if (timeUp == false) {
                    TextView displayedResult = (TextView) ((Activity)context).findViewById(R.id.reaction_time_display);
                    //when the time is up, display CLICK NOW
                    displayedResult.setText("CLICK NOW!");
                }
                timeUp = true;
                setStartTimeStamp();
            }
        }.start();
    }

    //display the result for a second with a timer and then start another round of main reflex game timer
    public void startAgain() {
        againTimer = new CountDownTimer(1000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                displaying = true;
                TextView displayedResult = (TextView) ((Activity)context).findViewById(R.id.reaction_time_display);
                displayedResult.setText(lagText());
            }

            @Override
            public void onFinish() {
                TextView displayedResult = (TextView) ((Activity)context).findViewById(R.id.reaction_time_display);
                displayedResult.setText("Wait");
                //when the time is up for displaying, clear the time recorded of last round and start the game again
                clearResult();
                startTimer();
            }
        }.start();
    }

    //if the user clicks early, then display the warning for 0.7 second before starting the main reflex game timer again
    public void disPlayEarlyWarning() {
        againTimer = new CountDownTimer(700, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                displaying = true;
                TextView displayedResult = (TextView) ((Activity)context).findViewById(R.id.reaction_time_display);
                displayedResult.setText("You Clicked Early! Now Start Again!");
            }

            @Override
            public void onFinish() {
                TextView displayedResult = (TextView) ((Activity)context).findViewById(R.id.reaction_time_display);
                displayedResult.setText("Wait");
                //when it finishes, clear the result of this round and start main timer again
                clearResult();
                startTimer();
            }
        }.start();
    }

    //if the user clicks, check if the time is up or the lag should be displayed and recorded
    public void checkReflex() {
        if (timeUp == false && start == true) {
            early = true;
            //if the user clicks early, then cancel the timer that is on right now and displaying warning
            ctimer.cancel();
	    disPlayEarlyWarning();
            TextView displayedResult = (TextView) ((Activity)context).findViewById(R.id.reaction_time_display);
            displayedResult.setText("Wait");
        } else if (timeUp == true && displaying == false && start == true) {
            setEndTimeStamp();
            getLag();
            //if the time is up record the time, display it and record in the list
            TextView displayedResult = (TextView) ((Activity)context).findViewById(R.id.reaction_time_display);
            displayedResult.setText(lagText());
            StatisticsListStorage.getSingleStatistics().add(lag());
            startAgain();
        } else if (start == false) {
            return;
        }
    }

        public void setStartTimeStamp(){
            startTime = System.currentTimeMillis();
        }


        public void setEndTimeStamp( ){
            endTime = System.currentTimeMillis();
        }

        public void getLag(){
            lag = endTime - startTime;
        }

        public double lag(){
            return lag;
        }

	//clear the time at the end of the time and when the user clicks of the current round
        public void clearResult(){
            startTime = 0;
            endTime = 0;
            lag = 0;
        }
        
        public String lagText(){
            displayLag = lag/1000;
            return "the lag is " + displayLag + " seconds" + "\nThe game will start again";
        }


    public boolean getTimeUp(){
        return timeUp;
    }

    public void setStart(boolean tf){
        start = tf;
    }
}
