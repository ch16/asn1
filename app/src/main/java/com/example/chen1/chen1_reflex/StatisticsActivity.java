package com.example.chen1.chen1_reflex;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class StatisticsActivity extends ActionBarActivity {

    TextView displayStatistics;
    double lastTen = 0;
    double lastHundred = 0;
    double averageTen = 0;
    double averageHundred = 0;

    int twoPlayerOne = 0;
    int twoPlayerTwo = 0;
    int threePlayerOne = 0;
    int threePlayerTwo = 0;
    int threePlayerThree = 0;
    int fourPlayerOne = 0;
    int fourPlayerTwo = 0;
    int fourPlayerThree = 0;
    int fourPlayerFour = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        calculateStatistics();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statistics, menu);
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


    public void calculateStatistics() {

        ArrayList statistics = StatisticsListController.getSingleStatistics();
        displayStatistics = (TextView) findViewById(R.id.statistics_text);

        lastTen = 0;
        for (int i = 1; i < 11; i++) {
            if ((statistics.size() - i) >= 0) {
                lastTen = lastTen + (double) statistics.get(statistics.size() - i);
            }
        }

        lastHundred = 0;
        for (int i = 1; i < 101; i++) {
            if ((statistics.size() - i) >= 0) {
                lastHundred = lastHundred + (double) statistics.get(statistics.size() - i);
            }
        }

        if (statistics.size() < 10) {
            averageTen = lastTen / (statistics.size() * 1000);
        }
        if (statistics.size() >= 10) {
            averageTen = lastTen / 10000;
        }
        if (statistics.size() < 100) {
            averageHundred = lastHundred / (statistics.size() * 1000);
        }
        if (statistics.size() >= 100) {
            averageHundred = lastHundred / 100000;
        }

        DecimalFormat formatter = new DecimalFormat("#0.0000");


        double maxTen = findMax(statistics, 10) / 1000;
        double minTen = findMin(statistics, 10) / 1000;
        double maxHundred = findMax(statistics, 100) / 1000;
        double minHundred = findMin(statistics, 100) / 1000;

        if (statistics.size() == 0) {
            maxTen = Double.NaN;
            minTen = Double.NaN;
            maxHundred = Double.NaN;
            minHundred = Double.NaN;
        }


        ArrayList twoPlayerStatistics = StatisticsListController.getTwoPlayerStatistics();

        for (int i = 1; i <= twoPlayerStatistics.size(); i++) {
            if (twoPlayerStatistics.get(twoPlayerStatistics.size() - i) == 1) {
                twoPlayerOne++;
            }
            if (twoPlayerStatistics.get(twoPlayerStatistics.size() - i) == 2) {
                twoPlayerTwo++;
            }
        }

        ArrayList threePlayerStatistics = StatisticsListController.getThreePlayerStatistics();

        for (int i = 1; i <= threePlayerStatistics.size(); i++) {
            if (threePlayerStatistics.get(threePlayerStatistics.size() - i) == 1) {
                threePlayerOne++;
            }
            if (threePlayerStatistics.get(threePlayerStatistics.size() - i) == 2) {
                threePlayerTwo++;
            }
            if (threePlayerStatistics.get(threePlayerStatistics.size() - i) == 3) {
                threePlayerThree++;
            }
        }

        ArrayList fourPlayerStatistics = StatisticsListController.getFourPlayerStatistics();

        for (int i = 1; i <= fourPlayerStatistics.size(); i++) {
            if (fourPlayerStatistics.get(fourPlayerStatistics.size() - i) == 1) {
                fourPlayerOne++;
            }
            if (fourPlayerStatistics.get(fourPlayerStatistics.size() - i) == 2) {
                fourPlayerTwo++;
            }
            if (fourPlayerStatistics.get(fourPlayerStatistics.size() - i) == 3) {
                fourPlayerThree++;
            }
            if (fourPlayerStatistics.get(fourPlayerStatistics.size() - i) == 4) {
                fourPlayerFour++;
            }
        }



        displayStatistics.setText("Single Player Stat:\nAverage of last ten: " + formatter.format(averageTen)
                        + " seconds\nAverage of last hundred: " + formatter.format(averageHundred) + " seconds\n"
                        + "Minimum of last ten: " + minTen + " seconds" + "\nMaximum of last ten: "
                        + maxTen + " seconds" + "\nMinimum of last hundred: " + minHundred + " seconds" + "\nMaximum of last hundred: " + maxHundred + " seconds"
                        + "\n-----------------"
                        + "\nBuzz Count:\n" + "2 Players: \nPlayer 1 buzzes: " + twoPlayerOne + " times | Player 2 buzzes: " + twoPlayerTwo + " times"
                        + "\n3 Players: \nPlayer 1 buzzes: " + threePlayerOne + " times | Player 2 buzzes: " + threePlayerTwo + " times | Player 3 buzzes: " + threePlayerThree + " times"
                        + "\n4 Players: \nPlayer 1 buzzes: " + fourPlayerOne + " times | Player 2 buzzes: " + fourPlayerTwo + " times | Player 3 buzzes: " + fourPlayerThree + " times | Player 4 buzzes: " + fourPlayerFour + " times"
        );
    }


    public double findMax(ArrayList stastistics, int tenHundred) {
        double max = 0;
        double temp = 0;
        for (int i = 1; i <= tenHundred; i++) {
            if (stastistics.size() - i >= 0) {
                temp = (double) stastistics.get(stastistics.size() - i);
                if (temp > max) {
                    max = temp;
                }
            }
        }
        return max;
    }

    public double findMin(ArrayList stastistics, int tenHundred) {
        double min = Double.POSITIVE_INFINITY;
        double temp = 0;
        for (int i = 1; i <= tenHundred; i++) {
            if (stastistics.size() - i >= 0) {
                temp = (double) stastistics.get(stastistics.size() - i);
                if (temp < min) {
                    min = temp;
                }
            }
        }
        return min;
    }

    public void clearReactionStat(View view) {
        StatisticsListController.clearSinlgeStatistics();
        StatisticsListController.clearTwoPlayerStatistics();
        StatisticsListController.clearThreePlayerStatistics();
        StatisticsListController.clearFourPlayerStatistics();
        twoPlayerOne = 0;
        twoPlayerTwo = 0;
        threePlayerOne = 0;
        threePlayerTwo = 0;
        threePlayerThree = 0;
        fourPlayerOne = 0;
        fourPlayerTwo = 0;
        fourPlayerThree = 0;
        fourPlayerFour = 0;
        calculateStatistics();
    }



    protected void sendEmail() {

        String to = "chenhong1211@gmail.com";
        String subject = "Statistics";
        String message = displayStatistics.getText().toString();

        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[] { to });
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);

        // need this to prompts email client only
        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, "Choose an Email client"));


    }

}
