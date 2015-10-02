package com.example.chen1.chen1_reflex;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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

public class TwoPlayers extends Activity {

    double playerOneTime = Double.POSITIVE_INFINITY;
    double playerTwoTime = Double.POSITIVE_INFINITY;
    StatisticsActivity a = new StatisticsActivity();

    CountDownTimer ctimer;
    boolean displaying = true;
    int clickedTimes = 0;
    static final String FILENAME2 = "file2.sav";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_players);
        loadFromTwoPlayerFile();
        AlertDialog alertDialog = new AlertDialog.Builder(TwoPlayers.this).create();
        alertDialog.setMessage("The Player Who Clicks Faster Wins");
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Start ",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        displaying = false;
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_two_players, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void playerOne(View view){
        if (displaying == false){
            clickedTimes ++;
            playerOneTime = System.currentTimeMillis();
            if (clickedTimes == 1) {
                waitToCheck();
            }
        }
    }

    public void playerTwo(View view){
        if (displaying == false){
            clickedTimes ++;
            playerTwoTime = System.currentTimeMillis();
            if (clickedTimes == 1) {
                waitToCheck();
            }
        }
    }

    public void waitToCheck(){
        ctimer = new CountDownTimer(200,1) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                checkResult();
            }
        }.start();
    }

    public void checkResult(){
        displaying = true;
        String resultText = "";
        if (playerOneTime != Double.POSITIVE_INFINITY){
            resultText = resultText + "Player 1 Clicked\n";
        }
        if (playerTwoTime != Double.POSITIVE_INFINITY){
            resultText = resultText + "Player 2 Clicked\n";
        }

        if (playerOneTime>playerTwoTime){
            resultText = resultText + "\nPlayer 2 Wins!";
            StatisticsListController.getTwoPlayerStatistics().add(2);
            saveInTwoPlayerFile();
        }else if (playerTwoTime>playerOneTime){
            resultText = resultText + "\nPlayer 1 Wins!";
            StatisticsListController.getTwoPlayerStatistics().add(1);
            saveInTwoPlayerFile();
        }

        AlertDialog alertDialog = new AlertDialog.Builder(TwoPlayers.this).create();
        alertDialog.setMessage(resultText);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Start Again",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        clearResult();
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void clearResult(){

        displaying = false;
        clickedTimes = 0;
        playerOneTime = Double.POSITIVE_INFINITY;
        playerTwoTime = Double.POSITIVE_INFINITY;
    }

    public void saveInTwoPlayerFile(){

        try {
            FileOutputStream fos2 = openFileOutput(FILENAME2,MODE_PRIVATE);
            Gson gson2 = new Gson();
            BufferedWriter out2 = new BufferedWriter(new OutputStreamWriter(fos2));
            gson2.toJson(StatisticsListController.getTwoPlayerStatistics(), out2);
            out2.flush();
            fos2.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    public void loadFromTwoPlayerFile() {

        //chagne all the string arraylist into double arraylist
        try {
            FileInputStream fis2 = openFileInput(FILENAME2);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(fis2));
            Gson gson2 = new Gson();
            Type arraylistType2 = new TypeToken<ArrayList<Integer>>() {
            }.getType();
            StatisticsListController.singleStatistics = gson2.fromJson(in2, arraylistType2);
            String line2 = in2.readLine();
            while (line2 != null) {
                StatisticsListController.singleStatistics.add(new Integer(line2));
                line2 = in2.readLine();
            }


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            StatisticsListController.singleStatistics = new ArrayList<Double>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }


}
