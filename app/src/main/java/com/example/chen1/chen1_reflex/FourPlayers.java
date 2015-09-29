package com.example.chen1.chen1_reflex;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class FourPlayers extends ActionBarActivity {

    double playerOneTime = Double.POSITIVE_INFINITY;
    double playerTwoTime = Double.POSITIVE_INFINITY;
    double playerThreeTime = Double.POSITIVE_INFINITY;
    double playerFourTime = Double.POSITIVE_INFINITY;

    CountDownTimer ctimer;
    boolean displaying = true;
    int clickedTimes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_players);

        AlertDialog alertDialog = new AlertDialog.Builder(FourPlayers.this).create();
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
        getMenuInflater().inflate(R.menu.menu_four_players, menu);
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
        if (displaying == false) {
            clickedTimes++;
            playerTwoTime = System.currentTimeMillis();
            if (clickedTimes == 1) {
                waitToCheck();
            }
        }
    }

    public void playerThree(View view){
        if (displaying == false) {
            clickedTimes++;
            playerThreeTime = System.currentTimeMillis();
            if (clickedTimes == 1) {
                waitToCheck();
            }
        }
    }

    public void playerFour(View view){
        if(displaying == false) {
            clickedTimes++;
            playerFourTime = System.currentTimeMillis();
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
            resultText = resultText + "Player 1 clicked\n";
        }
        if (playerTwoTime != Double.POSITIVE_INFINITY) {
            resultText = resultText + "Player 2 clicked\n";
        }
        if (playerThreeTime != Double.POSITIVE_INFINITY) {
            resultText = resultText + "Player 3 clicked\n";
        }
        if (playerFourTime != Double.POSITIVE_INFINITY) {
            resultText = resultText + "Player 4 clicked\n";
        }

        if (playerOneTime > playerTwoTime && playerThreeTime > playerTwoTime && playerFourTime > playerTwoTime) {
            resultText = resultText + "\nPlayer 2 Wins!";
            StatisticsListController.getFourPlayerStatistics().add(2);
        } else if (playerTwoTime > playerOneTime && playerThreeTime > playerOneTime && playerFourTime> playerOneTime) {
            resultText = resultText + "\nPlayer 1 Wins!";
            StatisticsListController.getFourPlayerStatistics().add(1);
        } else if (playerOneTime > playerThreeTime && playerTwoTime > playerThreeTime && playerFourTime > playerThreeTime) {
            resultText = resultText + "\nPlayer 3 Wins!";
            StatisticsListController.getFourPlayerStatistics().add(3);
        }else if (playerOneTime>playerFourTime && playerTwoTime> playerFourTime && playerThreeTime>playerFourTime){
            resultText = resultText + "\nPlayer 4 Wins!";
            StatisticsListController.getFourPlayerStatistics().add(4);
        }

        AlertDialog alertDialog = new AlertDialog.Builder(FourPlayers.this).create();
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
        playerFourTime = Double.POSITIVE_INFINITY;

    }

}
