package com.example.chen1.chen1_reflex.Module;

/**
 * Created by chen1 on 10/1/15.
 */
public class TwoPlayerManager extends MultiPlayerManager{
    public TwoPlayerManager() {
    }

    SaveLoadFiles saveLoadFiles = new SaveLoadFiles();

    double playerOneTime = Double.POSITIVE_INFINITY;
    double playerTwoTime = Double.POSITIVE_INFINITY;

    public void notePlayerOne(){

        if (displaying == false){
            clickedTimes ++;
            playerOneTime = System.currentTimeMillis();
        }
    }

    public void notePlayerTwo(){

        if (displaying == false){
            clickedTimes ++;
            playerTwoTime = System.currentTimeMillis();

        }
    }

    public void checkResult() {
            displaying = true;
            if (playerOneTime != Double.POSITIVE_INFINITY) {
                resultText = resultText + "Player 1 Clicked\n";
            }
            if (playerTwoTime != Double.POSITIVE_INFINITY) {
                resultText = resultText + "Player 2 Clicked\n";
            }

            if (playerOneTime > playerTwoTime) {
                resultText = resultText + "\nPlayer 2 Wins!";
                StatisticsListStorage.getTwoPlayerStatistics().add(2);
            } else if (playerTwoTime > playerOneTime) {
                resultText = resultText + "\nPlayer 1 Wins!";
                StatisticsListStorage.getTwoPlayerStatistics().add(1);
            }

    }

    public void clearResult(){

        displaying = false;
        clickedTimes = 0;
        playerOneTime = Double.POSITIVE_INFINITY;
        playerTwoTime = Double.POSITIVE_INFINITY;
        resultText = "";

    }

}
