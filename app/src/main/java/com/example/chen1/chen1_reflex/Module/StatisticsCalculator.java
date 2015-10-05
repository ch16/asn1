package com.example.chen1.chen1_reflex.Module;

import android.app.Activity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by chen1 on 10/1/15.
 */
public class StatisticsCalculator extends Activity{
    public StatisticsCalculator() {
    }

    private int twoPlayerOne = 0;
    private int twoPlayerTwo = 0;
    private int threePlayerOne = 0;
    private int threePlayerTwo = 0;
    private int threePlayerThree = 0;
    private int fourPlayerOne = 0;
    private int fourPlayerTwo = 0;
    private int fourPlayerThree = 0;
    private int fourPlayerFour = 0;

    private double maxTen;
    private double minTen;
    private double maxHundred;
    private double minHundred;
    private double maxAll;
    private double minAll;

    private double averageTen;
    private double averageHundred ;
    private double averageAll;

    private double medianTen = Double.NaN;
    private double medianHundred = Double.NaN;
    private double medianAll = Double.NaN;


    private ArrayList statistics;

    private ArrayList lastTenList = new ArrayList<Double>();
    private ArrayList lastHundredList = new ArrayList<Double>();
    private DecimalFormat formatter = new DecimalFormat("#0.000");

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
        if (stastistics.size() == 0) {
            min = 0;
        }
        return min;
    }

    public double findMedian(ArrayList alist) {
        double median = 0;
        if (alist.size() % 2 == 1) {
            median = (double) alist.get((int) (alist.size() / 2)) / 1000;
        } else if (alist.size() % 2 == 0 && alist.size() != 0) {
            median = (double) alist.get((int) (alist.size() / 2) - 1) + (double) alist.get((int) alist.size() / 2);
            median = median / 2000;
        } else if (alist.size() == 0) {
            median = 0;
        }
        return median;
    }

    public double findAverageOfLastN(ArrayList blist, int num) {
        double lastN = 0;
        for (int i = 1; i <= num; i++) {
            if ((blist.size() - i) >= 0) {
                lastN = lastN + (double) blist.get(blist.size() - i);
            }
        }

        double average = 0;
        if (blist.size() < num) {
            average = lastN / (blist.size() * 1000);
        } else if (blist.size() >= num) {
            average = lastN/(num*1000);
        }

        if(blist.size()==0){
            average = 0;
        }

        return average;
    }

    public void calAll(){

        statistics = StatisticsListStorage.getSingleStatistics();
        ArrayList temp;
        temp = statistics;

        for (int i = 1; i <= StatisticsListStorage.twoPlayerBuzz.size(); i++) {
            if (StatisticsListStorage.twoPlayerBuzz.get(StatisticsListStorage.twoPlayerBuzz.size() - i) == 1) {
                twoPlayerOne++;}
            if (StatisticsListStorage.twoPlayerBuzz.get(StatisticsListStorage.twoPlayerBuzz.size() - i) == 2) {
                twoPlayerTwo++;}
        }


        for (int i = 1; i <= StatisticsListStorage.threePlayerBuzz.size(); i++) {
            if (StatisticsListStorage.threePlayerBuzz.get(StatisticsListStorage.threePlayerBuzz.size() - i) == 1) {
                threePlayerOne++;}
            if (StatisticsListStorage.threePlayerBuzz.get(StatisticsListStorage.threePlayerBuzz.size() - i) == 2) {
                threePlayerTwo++;}
            if (StatisticsListStorage.threePlayerBuzz.get(StatisticsListStorage.threePlayerBuzz.size() - i) == 3) {
                threePlayerThree++;}
        }


        for (int i = 1; i <= StatisticsListStorage.fourPlayerBuzz.size(); i++) {
            if (StatisticsListStorage.fourPlayerBuzz.get(StatisticsListStorage.fourPlayerBuzz.size() - i) == 1) {
                fourPlayerOne++;}
            if (StatisticsListStorage.fourPlayerBuzz.get(StatisticsListStorage.fourPlayerBuzz.size() - i) == 2) {
                fourPlayerTwo++;}
            if (StatisticsListStorage.fourPlayerBuzz.get(StatisticsListStorage.fourPlayerBuzz.size() - i) == 3) {
                fourPlayerThree++;}
            if (StatisticsListStorage.fourPlayerBuzz.get(StatisticsListStorage.fourPlayerBuzz.size() - i) == 4) {
                fourPlayerFour++;}
        }

        maxTen = findMax(statistics, 10) / 1000;
        minTen = findMin(statistics, 10) / 1000;
        maxHundred = findMax(statistics, 100) / 1000;
        minHundred = findMin(statistics, 100) / 1000;
        maxAll = findMax(statistics, statistics.size()) / 1000;
        minAll = findMin(statistics, statistics.size()) / 1000;

        averageTen = findAverageOfLastN(statistics,10);
        averageHundred = findAverageOfLastN(statistics,100);
        averageAll = findAverageOfLastN(statistics,statistics.size());

        for (int i = 1; i <= 10; i++) {
            if (statistics.size() - i >= 0) {
                lastTenList.add(statistics.get(statistics.size() - i));}}

        for (int i = 1; i <= 100; i++) {
            if (statistics.size() - i >= 0) {
                lastHundredList.add(statistics.get(statistics.size() - i));}}



        Collections.sort(lastTenList);
        medianTen = findMedian(lastTenList);
        Collections.sort(lastHundredList);
        medianHundred = findMedian(lastHundredList);
        Collections.sort(temp);
        medianAll = findMedian(temp);

    }


    public String getStatString(){
        String statString = "Single Player Stat(IN SECONDS):\nAverage of last ten: " + formatter.format(averageTen)
                + "\nAverage of last hundred: " + formatter.format(averageHundred) + "\n"
                + "Average of all: " + formatter.format(averageAll)
                + "\nMedian of last ten: " + medianTen
                + "\nMedian of last hundred: " + medianHundred
                + " \nMedian of all: " + medianAll
                + "\nMinimum of last ten: " + minTen + "\nMaximum of last ten: "
                + maxTen + "\nMinimum of last hundred: " + minHundred + "\nMaximum of last hundred: " + maxHundred
                + "\nMinimum of all: " + minAll + "\nMaximum of all: " + maxAll
                + "\n-----------------"
                + "\nBuzz Count: " + "\n2 Players: Player One buzzes: " + twoPlayerOne + " times  |  Player Two buzzes: " + twoPlayerTwo+ " times"
                + "\n3 Players: Player One buzzes: " + threePlayerOne + " times  |  Player Two buzzes: " + threePlayerTwo + " times  |  Player Three buzzes: " + threePlayerThree+ " times"
                + "\n4 Players: Player One buzzes: " + fourPlayerOne + " times  |  Player Two buzzes: " + fourPlayerTwo + " times  |  Player Three buzzes: " + fourPlayerThree + " times  |  Player Four buzzes: " + fourPlayerFour + " times";
        return statString;
    }


    public void clearAll(){
        twoPlayerOne = 0;
        twoPlayerTwo = 0;
        threePlayerOne = 0;
        threePlayerTwo = 0;
        threePlayerThree = 0;
        fourPlayerOne = 0;
        fourPlayerTwo = 0;
        fourPlayerThree = 0;
        fourPlayerFour = 0;
        StatisticsListStorage.clearSinlgeStatistics();
        StatisticsListStorage.clearTwoPlayerStatistics();
        StatisticsListStorage.clearThreePlayerStatistics();
        StatisticsListStorage.clearFourPlayerStatistics();
        lastTenList.clear();
        lastHundredList.clear();
        medianTen = Double.NaN;
        medianHundred = Double.NaN;
    }



}
