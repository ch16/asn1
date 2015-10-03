package com.example.chen1.chen1_reflex.Module;

/**
 * Created by chen1 on 10/1/15.
 */
public class FourPlayerManager extends MultiPlayerManager{
    public FourPlayerManager() {
    }

    double playerOneTime = Double.POSITIVE_INFINITY;
    double playerTwoTime = Double.POSITIVE_INFINITY;
    double playerThreeTime = Double.POSITIVE_INFINITY;
    double playerFourTime = Double.POSITIVE_INFINITY;


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

    public void notePlayerFour(){

        if (displaying == false){
            clickedTimes ++;
            playerFourTime = System.currentTimeMillis();

        }
    }

    public void checkResult() {
        displaying = true;
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
            StatisticsListStorage.getFourPlayerStatistics().add(2);
        } else if (playerTwoTime > playerOneTime && playerThreeTime > playerOneTime && playerFourTime> playerOneTime) {
            resultText = resultText + "\nPlayer 1 Wins!";
            StatisticsListStorage.getFourPlayerStatistics().add(1);
        } else if (playerOneTime > playerThreeTime && playerTwoTime > playerThreeTime && playerFourTime > playerThreeTime) {
            resultText = resultText + "\nPlayer 3 Wins!";
            StatisticsListStorage.getFourPlayerStatistics().add(3);
        }else if (playerOneTime>playerFourTime && playerTwoTime> playerFourTime && playerThreeTime>playerFourTime){
            resultText = resultText + "\nPlayer 4 Wins!";
            StatisticsListStorage.getFourPlayerStatistics().add(4);
        }
    }

    public void clearResult(){
        displaying = false;
        clickedTimes = 0;
        playerOneTime = Double.POSITIVE_INFINITY;
        playerTwoTime = Double.POSITIVE_INFINITY;
        playerThreeTime = Double.POSITIVE_INFINITY;
        playerFourTime = Double.POSITIVE_INFINITY;
        resultText = "";

    }
}
