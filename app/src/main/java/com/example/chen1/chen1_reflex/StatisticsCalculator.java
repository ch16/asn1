package com.example.chen1.chen1_reflex;

import java.util.ArrayList;

/**
 * Created by chen1 on 10/1/15.
 */
public class StatisticsCalculator {
    public StatisticsCalculator() {
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

}
