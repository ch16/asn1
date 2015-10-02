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

public class ThreePlayers extends Activity {

    double playerOneTime = Double.POSITIVE_INFINITY;
    double playerTwoTime = Double.POSITIVE_INFINITY;
    double playerThreeTime = Double.POSITIVE_INFINITY;

    CountDownTimer ctimer;
    boolean displaying = true;
    int clickedTimes = 0;
    static final String FILENAME3 = "file3.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_players);
        loadFromFile3();

        AlertDialog alertDialog = new AlertDialog.Builder(ThreePlayers.this).create();
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
        getMenuInflater().inflate(R.menu.menu_three_players, menu);
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

    public void playerOne(View view) {
        if (displaying == false) {
            clickedTimes++;
            playerOneTime = System.currentTimeMillis();
            if (clickedTimes == 1) {
                waitToCheck();
            }
        }
    }

    public void playerTwo(View view) {
        if (displaying == false) {
            clickedTimes++;
            playerTwoTime = System.currentTimeMillis();
            if (clickedTimes == 1) {
                waitToCheck();
            }
        }
    }

    public void playerThree(View view) {
        if (displaying == false) {
            clickedTimes++;
            playerThreeTime = System.currentTimeMillis();
            if (clickedTimes == 1) {
                waitToCheck();
            }
        }
    }

    public void waitToCheck() {
        ctimer = new CountDownTimer(200, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                checkResult();
            }
        }.start();
    }

    public void checkResult() {
        displaying = true;
        String resultText = "";
        if (playerOneTime != Double.POSITIVE_INFINITY) {
            resultText = resultText + "Player 1 Clicked\n";
        }
        if (playerTwoTime != Double.POSITIVE_INFINITY) {
            resultText = resultText + "Player 2 Clicked\n";
        }
        if (playerThreeTime != Double.POSITIVE_INFINITY) {
            resultText = resultText + "Player 3 Clicked\n";
        }

        if (playerOneTime > playerTwoTime && playerThreeTime > playerTwoTime) {
            resultText = resultText + "\nPlayer 2 Wins!";
            StatisticsListController.getThreePlayerStatistics().add(2);
        } else if (playerTwoTime > playerOneTime && playerThreeTime > playerOneTime) {
            resultText = resultText + "\nPlayer 1 Wins!";
            StatisticsListController.getThreePlayerStatistics().add(1);
        } else if (playerOneTime > playerThreeTime && playerTwoTime > playerThreeTime) {
            resultText = resultText + "\nPlayer 3 Wins!";
            StatisticsListController.getThreePlayerStatistics().add(3);
        }
        saveInThreePlayerFile();

        AlertDialog alertDialog = new AlertDialog.Builder(ThreePlayers.this).create();
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

    public void clearResult() {

        displaying = false;
        clickedTimes = 0;
        playerOneTime = Double.POSITIVE_INFINITY;
        playerTwoTime = Double.POSITIVE_INFINITY;
        playerThreeTime = Double.POSITIVE_INFINITY;
    }

    public void saveInThreePlayerFile(){

        try {
            FileOutputStream fos3 = openFileOutput(FILENAME3, MODE_PRIVATE);
            Gson gson3 = new Gson();
            BufferedWriter out3 = new BufferedWriter(new OutputStreamWriter(fos3));
            gson3.toJson(StatisticsListController.threePlayerBuzz, out3);
            out3.flush();
            fos3.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    public void loadFromFile3() {
        //chagne all the string arraylist into double arraylist
        try {
            FileInputStream fis3 = openFileInput(FILENAME3);
            BufferedReader in3 = new BufferedReader(new InputStreamReader(fis3));
            Gson gson3 = new Gson();
            Type arraylistType3 = new TypeToken<ArrayList<Integer>>() {
            }.getType();
            StatisticsListController.threePlayerBuzz = gson3.fromJson(in3, arraylistType3);
            String line3 = in3.readLine();
            while (line3 != null) {
                StatisticsListController.threePlayerBuzz.add(new Integer(line3));
                line3 = in3.readLine();
            }


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            StatisticsListController.threePlayerBuzz = new ArrayList<Integer>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }
}
