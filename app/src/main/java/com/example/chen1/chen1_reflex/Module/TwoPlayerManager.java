package com.example.chen1.chen1_reflex.Module;

/**
 * Created by chen1 on 10/1/15.
 */
public class TwoPlayerManager {
    public TwoPlayerManager() {
    }

    // record the time of each player if the button is clicked
    double playerOneTime = Double.POSITIVE_INFINITY;
    double playerTwoTime = Double.POSITIVE_INFINITY;
    //record how many players have clicked in each round
    int clickedTimes = 0;
    String resultText = "";

    public String getText(){
        return resultText;
    }

    public int getClickedTimes(){
        return clickedTimes;
    }

    //if player one clicks
    public void notePlayerOne(){
            clickedTimes ++;
            playerOneTime = System.currentTimeMillis();        
    }

    //if player two clicks
    public void notePlayerTwo(){
            clickedTimes ++;
            playerTwoTime = System.currentTimeMillis();
    }

    //check who clicks first and add it to the statistics list
    public void checkTwoResults() {
            if (playerOneTime != Double.POSITIVE_INFINITY) {
                resultText = resultText + "Player 1 Clicked\n";
            }
            if (playerTwoTime != Double.POSITIVE_INFINITY) {
                resultText = resultText + "Player 2 Clicked\n";
            }
            
            //add the winner to the list and the text to show up in the alert dialog
            if (playerOneTime > playerTwoTime) {
                resultText = resultText + "\nPlayer 2 Wins!";
                StatisticsListStorage.getTwoPlayerStatistics().add(2);
            } else if (playerTwoTime > playerOneTime) {
                resultText = resultText + "\nPlayer 1 Wins!";
                StatisticsListStorage.getTwoPlayerStatistics().add(1);
            }
    }
    
    //clear time recorded for the players for the new round
    public void clearTwoResults(){
        clickedTimes = 0;
        playerOneTime = Double.POSITIVE_INFINITY;
        playerTwoTime = Double.POSITIVE_INFINITY;
        resultText = "";

    }

}
