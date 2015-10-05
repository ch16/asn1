package com.example.chen1.chen1_reflex.Module;

/**
 * Created by chen1 on 10/1/15.
 */
public class FourPlayerManager {
    public FourPlayerManager() {
    }
    // record the time of each player if the button is clicked
    private double playerOneTime = Double.POSITIVE_INFINITY;
    private double playerTwoTime = Double.POSITIVE_INFINITY;
    private double playerThreeTime = Double.POSITIVE_INFINITY;
    private double playerFourTime = Double.POSITIVE_INFINITY;
    //record how many players have clicked in each round
    private int clickedTimes = 0;
    //the result to show in the alertdiaglog
    private String resultText = "";

    public String getText(){
        return resultText;
    }

    public int getClickedTimes(){
        return clickedTimes;
    }

    //if player one clicks
    public void notePlayerOne() {
        clickedTimes++;
        playerOneTime = System.currentTimeMillis();
    }
    
    //if player two clicks
    public void notePlayerTwo() {
        clickedTimes++;
        playerTwoTime = System.currentTimeMillis();
    }

    //if player three clicks
    public void notePlayerThree() {
        clickedTimes++;
        playerThreeTime = System.currentTimeMillis();
    }

    //if player four clicks
    public void notePlayerFour() {
        clickedTimes++;
        playerFourTime = System.currentTimeMillis();
    }

    //check who clicks first and add it to the statistics list
    public void checkFourResults() {
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

        //add the winner to the list and the text to show up in the alert dialog
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

    //clear time recorded for the players for the new round
    public void clearFourResults(){
        clickedTimes = 0;
        playerOneTime = Double.POSITIVE_INFINITY;
        playerTwoTime = Double.POSITIVE_INFINITY;
        playerThreeTime = Double.POSITIVE_INFINITY;
        playerFourTime = Double.POSITIVE_INFINITY;
        resultText = "";

    }
}
