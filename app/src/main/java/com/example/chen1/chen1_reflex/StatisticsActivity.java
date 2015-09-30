package com.example.chen1.chen1_reflex;

import android.app.Activity;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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




    TextView displayStatistics;
    double lastTen = 0;
    double lastHundred = 0;
    double averageTen = 0;
    double averageHundred = 0;
    double averageAll = 0;
    double medianTen = Double.NaN;
    double medianHundred = Double.NaN;
    double medianAll = Double.NaN;
    double all = 0;

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
    ArrayList statistics = getSingleStatistics();
    ArrayList twoPlayerStatistics = getTwoPlayerStatistics();
    ArrayList threePlayerStatistics = getThreePlayerStatistics();
    ArrayList fourPlayerStatistics = getFourPlayerStatistics();


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

        StatisticsListController.setSingleStatistics(statistics);
        displayStatistics = (TextView) findViewById(R.id.statistics_text);
        ArrayList temp;
        temp = statistics;

        lastTen = 0;
        for (int i = 1; i < 11; i++) {
            if ((statistics.size() - i) >= 0) {
                lastTen = lastTen + (double) statistics.get(statistics.size() - i);}
        }

        lastHundred = 0;
        for (int i = 1; i < 101; i++) {
            if ((statistics.size() - i) >= 0) {
                lastHundred = lastHundred + (double) statistics.get(statistics.size() - i);}
        }

        if (statistics.size() < 10) {
            averageTen = lastTen / (statistics.size() * 1000);}
        if (statistics.size() >= 10) {
            averageTen = lastTen / 10000;}
        if (statistics.size() < 100) {
            averageHundred = lastHundred / (statistics.size() * 1000);}
        if (statistics.size() >= 100) {
            averageHundred = lastHundred / 100000;
        }
        DecimalFormat formatter = new DecimalFormat("#0.0000");

        all = 0;

        for (int i = 1; i <= temp.size(); i++) {
            all = all + (double) temp.get(temp.size() - i);
        }
        averageAll = all / (temp.size() * 1000);

        double maxTen = findMax(statistics, 10) / 1000;
        double minTen = findMin(statistics, 10) / 1000;
        double maxHundred = findMax(statistics, 100) / 1000;
        double minHundred = findMin(statistics, 100) / 1000;
        double maxAll = findMax(statistics, statistics.size()) / 1000;
        double minAll = findMin(statistics, statistics.size()) / 1000;

        if (statistics.size() == 0) {
            maxTen = Double.NaN;
            minTen = Double.NaN;
            maxHundred = Double.NaN;
            minHundred = Double.NaN;
            minAll = Double.NaN;
            maxAll = Double.NaN;
        }

        for (int i = 1; i <= 10; i++) {
            if (statistics.size() - i >= 0) {
                lastTenList.add(statistics.get(statistics.size() - i));
            }
        }


        for (int i = 1; i <= 100; i++) {
            if (statistics.size() - i >= 0) {
                lastHundredList.add(statistics.get(statistics.size() - i));
            }
        }


        Collections.sort(lastTenList);

        if (lastTenList.size() % 2 == 1) {
            medianTen = (double) lastTenList.get((int) (lastTenList.size() / 2)) / 1000;
        } else if (lastTenList.size() % 2 == 0 && lastTenList.size() != 0) {
            medianTen = (double) lastTenList.get((int) (lastTenList.size() / 2) - 1) + (double) lastTenList.get((int) lastTenList.size() / 2);
            medianTen = medianTen / 2000;
        } else if (lastTenList.size() == 0) {
            medianTen = Double.NaN;
        }


        Collections.sort(lastHundredList);

        if (lastHundredList.size() % 2 == 1) {
            medianHundred = (double) lastHundredList.get((int) (lastHundredList.size() / 2)) / 1000;
        } else if (lastHundredList.size() % 2 == 0 && lastHundredList.size() != 0) {
            medianHundred = (double) lastHundredList.get((int) (lastHundredList.size() / 2) - 1) + (double) lastHundredList.get((int) lastHundredList.size() / 2);
            medianHundred = medianHundred / 2000;
        } else if (lastHundredList.size() == 0) {
            medianHundred = Double.NaN;
        }

        Collections.sort(temp);

        if (temp.size() % 2 == 1) {
            medianAll = (double) temp.get((int) (temp.size() / 2)) / 1000;
        } else if (temp.size() % 2 == 0 && temp.size() != 0) {
            medianAll = (double) temp.get((int) (temp.size() / 2) - 1) + (double) temp.get((int) temp.size() / 2);
            medianAll = medianAll / 2000;
        } else if (temp.size() == 0) {
            medianAll = Double.NaN;
        }



        for (int i = 1; i <= twoPlayerStatistics.size(); i++) {
            if (twoPlayerStatistics.get(twoPlayerStatistics.size() - i) == 1) {
                twoPlayerOne++;
            }
            if (twoPlayerStatistics.get(twoPlayerStatistics.size() - i) == 2) {
                twoPlayerTwo++;
            }
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
                        + "\nBuzz Count: " + "\n2 Players: Player 1 buzzes: " + twoPlayerOne + " times | Player 2 buzzes: " + twoPlayerTwo + " times"
                        + "\n3 Players: Player 1 buzzes: " + threePlayerOne + " times | Player 2 buzzes: " + threePlayerTwo + " times | Player 3 buzzes: " + threePlayerThree + " times"
                        + "\n4 Players: Player 1 buzzes: " + fourPlayerOne + " times | Player 2 buzzes: " + fourPlayerTwo + " times | Player 3 buzzes: " + fourPlayerThree + " times | Player 4 buzzes: " + fourPlayerFour + " times"
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


    protected void sendEmail() {

        String to = "chenhong1211@gmail.com";
        String subject = "Statistics";
        String message = displayStatistics.getText().toString();

        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);

        // need this to prompts email client only
        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, "Choose an Email client"));

    }

    private void loadFromFile() {
        //chagne all the string arraylist into double arraylist
        try {
            FileInputStream fis = openFileInput(FILENAME);
            FileInputStream fis2 = openFileInput(FILENAME2);
            FileInputStream fis3 = openFileInput(FILENAME3);
            FileInputStream fis4 = openFileInput(FILENAME4);

            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            BufferedReader in2 = new BufferedReader(new InputStreamReader(fis2));
            BufferedReader in3 = new BufferedReader(new InputStreamReader(fis3));
            BufferedReader in4 = new BufferedReader(new InputStreamReader(fis4));


            Gson gson = new Gson();
            Type arraylistType = new TypeToken<ArrayList<Double>>() {
            }.getType();
            statistics = gson.fromJson(in, arraylistType);
            String line = in.readLine();
            while (line != null) {
                statistics.add(new Double(line));
                line = in.readLine();
            }

            Gson gson2 = new Gson();
            Type arraylistType2 = new TypeToken<ArrayList<Double>>() {
            }.getType();
            twoPlayerStatistics = gson2.fromJson(in2, arraylistType2);
            String line2 = in2.readLine();
            while (line2 != null) {
                twoPlayerStatistics.add(new Double(line2));
                line2 = in2.readLine();
            }


            Gson gson3 = new Gson();
            Type arraylistType3 = new TypeToken<ArrayList<Double>>() {
            }.getType();
            threePlayerStatistics = gson3.fromJson(in3, arraylistType3);
            String line3 = in3.readLine();
            while (line3 != null) {
                threePlayerStatistics.add(new Double(line3));
                line3 = in3.readLine();
            }


            Gson gson4 = new Gson();
            Type arraylistType4 = new TypeToken<ArrayList<Double>>() {
            }.getType();
            fourPlayerStatistics = gson4.fromJson(in4, arraylistType4);
            String line4 = in4.readLine();
            while (line4 != null) {
                fourPlayerStatistics.add(new Double(line4));
                line4 = in4.readLine();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            statistics = new ArrayList<Double>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    public void saveInFile(){

        try {
            FileOutputStream fos = openFileOutput(FILENAME, MODE_PRIVATE);
            FileOutputStream fos2 = openFileOutput(FILENAME2, MODE_PRIVATE);
            FileOutputStream fos3 = openFileOutput(FILENAME3, MODE_PRIVATE);
            FileOutputStream fos4 = openFileOutput(FILENAME4, MODE_PRIVATE);

            Gson gson = new Gson();
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            BufferedWriter out2 = new BufferedWriter(new OutputStreamWriter(fos2));
            BufferedWriter out3 = new BufferedWriter(new OutputStreamWriter(fos3));
            BufferedWriter out4 = new BufferedWriter(new OutputStreamWriter(fos4));

            gson.toJson(getSingleStatistics(), out);
            gson.toJson(getTwoPlayerStatistics(), out2);
            gson.toJson(getThreePlayerStatistics(), out3);
            gson.toJson(getFourPlayerStatistics(), out4);

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
}