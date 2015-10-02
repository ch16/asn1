package com.example.chen1.chen1_reflex;

/**
 * Created by chen1 on 10/1/15.
 */
public class ThreePlayerManager extends MultiPlayerManager{
    public ThreePlayerManager() {
    }


    double playerOneTime = Double.POSITIVE_INFINITY;
    double playerTwoTime = Double.POSITIVE_INFINITY;
    double playerThreeTime = Double.POSITIVE_INFINITY;


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

    public void notePlayerThree(){

        if (displaying == false){
            clickedTimes ++;
            playerThreeTime = System.currentTimeMillis();

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
    }

    public void clearResult(){
        displaying = false;
        clickedTimes = 0;
        playerOneTime = Double.POSITIVE_INFINITY;
        playerTwoTime = Double.POSITIVE_INFINITY;
        playerThreeTime = Double.POSITIVE_INFINITY;
        resultText = "";

    }

}

