package com.example.chen1.chen1_reflex;

import android.app.Activity;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import static com.example.chen1.chen1_reflex.StatisticsListController.*;

public class StatisticsActivity extends Activity {
    static final String FILENAME = "file.sav";
    static final String FILENAME2 = "file2.sav";
    static final String FILENAME3 = "file3.sav";
    static final String FILENAME4 = "file4.sav";


    String Message;
    TextView displayStatistics;
    StatisticsCalculator sCal = new StatisticsCalculator();



    double medianTen = Double.NaN;
    double medianHundred = Double.NaN;
    double medianAll = Double.NaN;

    ArrayList lastTenList = new ArrayList<Double>();
    ArrayList lastHundredList = new ArrayList<Double>();

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

        loadFromFile();

        ArrayList statistics = StatisticsListController.getSingleStatistics();
        ArrayList twoPlayerStatistics = StatisticsListController.getTwoPlayerStatistics();
        ArrayList threePlayerStatistics = StatisticsListController.getThreePlayerStatistics();
        ArrayList fourPlayerStatistics = StatisticsListController.getFourPlayerStatistics();
        displayStatistics = (TextView) findViewById(R.id.statistics_text);
        displayStatistics.setMovementMethod(new ScrollingMovementMethod());

        ArrayList temp;
        temp = statistics;


        double averageTen = sCal.findAverageOfLastN(statistics,10);
        double averageHundred = sCal.findAverageOfLastN(statistics,100);
        double averageAll = sCal.findAverageOfLastN(statistics,statistics.size());

        DecimalFormat formatter = new DecimalFormat("#0.0000");


        double maxTen = sCal.findMax(statistics, 10) / 1000;
        double minTen = sCal.findMin(statistics, 10) / 1000;
        double maxHundred = sCal.findMax(statistics, 100) / 1000;
        double minHundred = sCal.findMin(statistics, 100) / 1000;
        double maxAll = sCal.findMax(statistics, statistics.size()) / 1000;
        double minAll = sCal.findMin(statistics, statistics.size()) / 1000;

        for (int i = 1; i <= 10; i++) {
            if (statistics.size() - i >= 0) {
                lastTenList.add(statistics.get(statistics.size() - i));}}

        for (int i = 1; i <= 100; i++) {
            if (statistics.size() - i >= 0) {
                lastHundredList.add(statistics.get(statistics.size() - i));}}

        Collections.sort(lastTenList);
        medianTen = sCal.findMedian(lastTenList);
        Collections.sort(lastHundredList);
        medianHundred = sCal.findMedian(lastHundredList);
        Collections.sort(temp);
        medianAll = sCal.findMedian(temp);


        for (int i = 1; i <= twoPlayerStatistics.size(); i++) {
            if (twoPlayerStatistics.get(twoPlayerStatistics.size() - i) == 1) {
                twoPlayerOne++;}
            if (twoPlayerStatistics.get(twoPlayerStatistics.size() - i) == 2) {
                twoPlayerTwo++;}
        }


        for (int i = 1; i <= threePlayerStatistics.size(); i++) {
            if (threePlayerStatistics.get(threePlayerStatistics.size() - i) == 1) {
                threePlayerOne++;}
            if (threePlayerStatistics.get(threePlayerStatistics.size() - i) == 2) {
                threePlayerTwo++;}
            if (threePlayerStatistics.get(threePlayerStatistics.size() - i) == 3) {
                threePlayerThree++;}
        }


        for (int i = 1; i <= fourPlayerStatistics.size(); i++) {
            if (fourPlayerStatistics.get(fourPlayerStatistics.size() - i) == 1) {
                fourPlayerOne++;}
            if (fourPlayerStatistics.get(fourPlayerStatistics.size() - i) == 2) {
                fourPlayerTwo++;}
            if (fourPlayerStatistics.get(fourPlayerStatistics.size() - i) == 3) {
                fourPlayerThree++;}
            if (fourPlayerStatistics.get(fourPlayerStatistics.size() - i) == 4) {
                fourPlayerFour++;}
        }


        displayStatistics.setText("Single Player Stat(IN SECONDS):\nAverage of last ten: " + formatter.format(averageTen)
                        + "\nAverage of last hundred: " + formatter.format(averageHundred) + "\n"
                        + "Average of all: " + formatter.format(averageAll)
                        + "\nMedian of last ten: " + medianTen
                        + "\nMedian of last hundred: " + medianHundred
                        + " \nMedian of all: " + medianAll
                        + "\nMinimum of last ten: " + minTen + "\nMaximum of last ten: "
                        + maxTen + "\nMinimum of last hundred: " + minHundred + "\nMaximum of last hundred: " + maxHundred
                        + "\nMinimum of all: " + minAll + "\nMaximum of all: " + maxAll
                        + "\n-----------------"
                        + "\nBuzz Count: " + "\n2 Players: Player One buzzes: " + twoPlayerOne + " times  |  Player Two buzzes: " + twoPlayerTwo + " times"
                        + "\n3 Players: Player One buzzes: " + threePlayerOne + " times  |  Player Two buzzes: " + threePlayerTwo + " times  |  Player Three buzzes: " + threePlayerThree + " times"
                        + "\n4 Players: Player One buzzes: " + fourPlayerOne + " times  |  Player Two buzzes: " + fourPlayerTwo + " times  |  Player Three buzzes: " + fourPlayerThree + " times  |  Player Four buzzes: " + fourPlayerFour + " times"
        );

        Message = displayStatistics.getText().toString();
        saveInFile();
    }



    public void clearReactionStat(View view) {
        clearSinlgeStatistics();
        clearTwoPlayerStatistics();
        clearThreePlayerStatistics();
        clearFourPlayerStatistics();
        lastTenList.clear();
        lastHundredList.clear();
        twoPlayerOne = 0;
        twoPlayerTwo = 0;
        threePlayerOne = 0;
        threePlayerTwo = 0;
        threePlayerThree = 0;
        fourPlayerOne = 0;
        fourPlayerTwo = 0;
        fourPlayerThree = 0;
        fourPlayerFour = 0;
        medianTen = Double.NaN;
        medianHundred = Double.NaN;
        saveInFile();
        calculateStatistics();
    }


    public void sendEmail(View view){
        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, Message);

        try {
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        finish();
    }catch (android.content.ActivityNotFoundException ex) {
        Toast.makeText(StatisticsActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
    }
    }



    public void saveInFile(){

        try {
            FileOutputStream fos = openFileOutput(FILENAME, MODE_PRIVATE);
            FileOutputStream fos2 = openFileOutput(FILENAME2, MODE_PRIVATE);
            FileOutputStream fos3 = openFileOutput(FILENAME3, MODE_PRIVATE);
            FileOutputStream fos4 = openFileOutput(FILENAME4, MODE_PRIVATE);

            Gson gson = new Gson();
            Gson gson2 = new Gson();
            Gson gson3 = new Gson();
            Gson gson4 = new Gson();

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            BufferedWriter out2 = new BufferedWriter(new OutputStreamWriter(fos2));
            BufferedWriter out3 = new BufferedWriter(new OutputStreamWriter(fos3));
            BufferedWriter out4 = new BufferedWriter(new OutputStreamWriter(fos4));

            gson.toJson(getSingleStatistics(), out);
            gson2.toJson(getTwoPlayerStatistics(), out2);
            gson3.toJson(getThreePlayerStatistics(), out3);
            gson4.toJson(getFourPlayerStatistics(), out4);

            out.flush();
            out2.flush();
            out3.flush();
            out4.flush();

            fos.close();
            fos2.close();
            fos3.close();
            fos4.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    public void loadFromFile() {
        //chagne all the string arraylist into double arraylist
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type arraylistType = new TypeToken<ArrayList<Double>>() {
            }.getType();
            singleStatistics = gson.fromJson(in, arraylistType);
            String line = in.readLine();
            while (line != null) {
                singleStatistics.add(new Double(line));
                line = in.readLine();
            }

            FileInputStream fis2 = openFileInput(FILENAME2);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(fis2));
            Gson gson2 = new Gson();
            Type arraylistType2 = new TypeToken<ArrayList<Integer>>() {
            }.getType();
            twoPlayerBuzz = gson2.fromJson(in2, arraylistType2);
            String line2 = in2.readLine();
            while (line2 != null) {
                twoPlayerBuzz.add(new Integer(line2));
                line2 = in2.readLine();
            }

            FileInputStream fis3 = openFileInput(FILENAME3);
            BufferedReader in3 = new BufferedReader(new InputStreamReader(fis3));
            Gson gson3 = new Gson();
            Type arraylistType3 = new TypeToken<ArrayList<Integer>>() {
            }.getType();
            threePlayerBuzz = gson3.fromJson(in3, arraylistType3);
            String line3 = in3.readLine();
            while (line3 != null) {
                threePlayerBuzz.add(new Integer(line3));
                line3 = in3.readLine();
            }

            FileInputStream fis4 = openFileInput(FILENAME4);
            BufferedReader in4 = new BufferedReader(new InputStreamReader(fis4));
            Gson gson4 = new Gson();
            Type arraylistType4 = new TypeToken<ArrayList<Integer>>() {
            }.getType();
            threePlayerBuzz = gson4.fromJson(in4, arraylistType4);
            String line4 = in4.readLine();
            while (line4 != null) {
                threePlayerBuzz.add(new Integer(line4));
                line4 = in4.readLine();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            singleStatistics = new ArrayList<Double>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

}