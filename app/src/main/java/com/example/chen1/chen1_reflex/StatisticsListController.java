package com.example.chen1.chen1_reflex;

import android.app.Activity;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by chen1 on 9/28/15.
 */
public class StatisticsListController{

    static ArrayList singleStatistics = new ArrayList<Double>();
    static ArrayList twoPlayerBuzz = new ArrayList<Integer>();
    static ArrayList threePlayerBuzz = new ArrayList<Integer>();
    static ArrayList fourPlayerBuzz = new ArrayList<Integer>();

    static final String FILENAME = "file.sav";
    static final String FILENAME2 = "file2.sav";
    static final String FILENAME3 = "file3.sav";
    static final String FILENAME4 = "file4.sav";

    public static ArrayList<Integer> getTwoPlayerStatistics(){
        return twoPlayerBuzz;
    }

    public static void clearTwoPlayerStatistics(){
        twoPlayerBuzz.clear();
    }

    public static ArrayList<Integer> getThreePlayerStatistics(){
        return threePlayerBuzz;
    }

    public static void clearThreePlayerStatistics(){
        threePlayerBuzz.clear();
    }

    public static ArrayList<Integer> getFourPlayerStatistics(){
        return fourPlayerBuzz;
    }

    public static void clearFourPlayerStatistics(){
        fourPlayerBuzz.clear();
    }

    public static ArrayList<Double> getSingleStatistics(){
        return singleStatistics;
    }

    public static void clearSinlgeStatistics(){
        singleStatistics.clear();
    }

    public static void setSingleStatistics(ArrayList<Double> singleArraylist){
        singleStatistics = singleArraylist;
    }

    public static void setTwoPlayerStatistics(ArrayList<Double> arraylist){
        twoPlayerBuzz = arraylist;
    }

    public static void setThreePlayerStatistics(ArrayList<Double> arraylist){
        threePlayerBuzz = arraylist;
    }

    public static void setFourPlayerStatistics(ArrayList<Double> arraylist){
        fourPlayerBuzz = arraylist;
    }



}
