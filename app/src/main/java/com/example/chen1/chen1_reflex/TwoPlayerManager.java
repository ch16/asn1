package com.example.chen1.chen1_reflex;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.view.View;

/**
 * Created by chen1 on 10/1/15.
 */
public class TwoPlayerManager {
    public TwoPlayerManager() {
    }

    double playerOneTime = Double.POSITIVE_INFINITY;
    double playerTwoTime = Double.POSITIVE_INFINITY;
    StatisticsActivity a = new StatisticsActivity();

    CountDownTimer ctimer;
    boolean displaying = true;
    int clickedTimes = 0;

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

    public void checkResult() {
        displaying = true;
        String resultText = "";
        if (playerOneTime != Double.POSITIVE_INFINITY) {
            resultText = resultText + "Player 1 Clicked\n";
        }
        if (playerTwoTime != Double.POSITIVE_INFINITY) {
            resultText = resultText + "Player 2 Clicked\n";
        }

        if (playerOneTime > playerTwoTime) {
            resultText = resultText + "\nPlayer 2 Wins!";
            StatisticsListController.getTwoPlayerStatistics().add(2);
        } else if (playerTwoTime > playerOneTime) {
            resultText = resultText + "\nPlayer 1 Wins!";
            StatisticsListController.getTwoPlayerStatistics().add(1);
        }
    }

        /*
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
    */

    public void clearResult(){

        displaying = false;
        clickedTimes = 0;
        playerOneTime = Double.POSITIVE_INFINITY;
        playerTwoTime = Double.POSITIVE_INFINITY;
    }

}
