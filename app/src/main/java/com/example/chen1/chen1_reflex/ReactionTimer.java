package com.example.chen1.chen1_reflex;

import android.app.Activity;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

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
    public ReactionTimer() {
    }

        double startTime;
        double endTime;
        double lag;
        double displayLag;

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
    }
