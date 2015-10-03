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
    Context context;
    public ReactionTimer(Context context) {
        this.context=context;
    }

    double startTime;
    double endTime;
    double lag;
    double displayLag;



    CountDownTimer ctimer;
    CountDownTimer againTimer;
    Random random = new Random();

    boolean early = false;
    boolean timeUp = false;
    boolean start = false;

    int randomTime;
    boolean displaying = false;


    public void startGame() {
        randomTime = random.nextInt(2000) + 10;
        ctimer = new CountDownTimer(randomTime, 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                displaying = false;
                TextView displayedResult = (TextView) ((Activity)context).findViewById(R.id.reaction_time_display);
                displayedResult.setText("Wait");
                timeUp = false;
            }

            @Override
            public void onFinish() {
                if (timeUp == false) {
                    TextView displayedResult = (TextView) ((Activity)context).findViewById(R.id.reaction_time_display);
                    displayedResult.setText("CLICK NOW!");
                }
                timeUp = true;
                setStartTimeStamp();
            }
        }.start();
    }

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
                clearResult();
                startGame();
            }
        }.start();
    }

    public void checkReflex() {
        if (timeUp == false && start == true) {
            early = true;
            ctimer.cancel();
            startGame();
            TextView displayedResult = (TextView) ((Activity)context).findViewById(R.id.reaction_time_display);
            displayedResult.setText("Wait");
        } else if (timeUp == true && displaying == false && start == true) {
            setEndTimeStamp();
            getLag();
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
