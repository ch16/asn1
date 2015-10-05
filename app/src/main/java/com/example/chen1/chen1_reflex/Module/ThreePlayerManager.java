package com.example.chen1.chen1_reflex.Module;

/**
 * Created by chen1 on 10/1/15.
 */
public class ThreePlayerManager {
    public ThreePlayerManager() {
    }

    // record the time of each player if the button is clicked
    private double playerOneTime = Double.POSITIVE_INFINITY;
    private double playerTwoTime = Double.POSITIVE_INFINITY;
    private double playerThreeTime = Double.POSITIVE_INFINITY;    
    private int clickedTimes = 0;
    //record how many players have clicked in each round
    private String resultText = "";

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
    
    //if player three clicks
    public void notePlayerThree(){
            clickedTimes ++;
            playerThreeTime = System.currentTimeMillis();
    }

    //check who clicks first and add it to the statistics list
    public void checkThreeResults() {
        if (playerOneTime != Double.POSITIVE_INFINITY) {
            resultText = resultText + "Player 1 Clicked\n";
        }
        if (playerTwoTime != Double.POSITIVE_INFINITY) {
            resultText = resultText + "Player 2 Clicked\n";
        }
        if (playerThreeTime != Double.POSITIVE_INFINITY) {
            resultText = resultText + "Player 3 Clicked\n";
        }
        
        //add the winner to the list and the text to show up in the alert dialog
        if (playerOneTime > playerTwoTime && playerThreeTime > playerTwoTime) {
            resultText = resultText + "\nPlayer 2 Wins!";
            StatisticsListStorage.getThreePlayerStatistics().add(2);
        } else if (playerTwoTime > playerOneTime && playerThreeTime > playerOneTime) {
            resultText = resultText + "\nPlayer 1 Wins!";
            StatisticsListStorage.getThreePlayerStatistics().add(1);
        } else if (playerOneTime > playerThreeTime && playerTwoTime > playerThreeTime) {
            resultText = resultText + "\nPlayer 3 Wins!";
            StatisticsListStorage.getThreePlayerStatistics().add(3);
        }
    }
    
    //clear time recorded for the players for the new round
    public void clearThreeResults(){
        clickedTimes = 0;
        playerOneTime = Double.POSITIVE_INFINITY;
        playerTwoTime = Double.POSITIVE_INFINITY;
        playerThreeTime = Double.POSITIVE_INFINITY;
        resultText = "";

    }

}

