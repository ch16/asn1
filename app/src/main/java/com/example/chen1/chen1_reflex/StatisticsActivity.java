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


    TextView displayStatistics;
    StatisticsCalculator sCal = new StatisticsCalculator();
    SaveLoadFiles sLFiles = new SaveLoadFiles();


    static final String FILENAME = "file.sav";
    static final String FILENAME2 = "file2.sav";
    static final String FILENAME3 = "file3.sav";
    static final String FILENAME4 = "file4.sav";


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

    @Override
    protected void onStart(){
        super.onStart();
        loadFromFile();
    }

    public void calculateStatistics() {

        loadFromFile();
        displayStatistics = (TextView) findViewById(R.id.statistics_text);
        displayStatistics.setMovementMethod(new ScrollingMovementMethod());

        sCal.calAll();
        displayStatistics.setText(sCal.getStatString());

        sLFiles.saveInAllFile(StatisticsActivity.this);
    }


    public void clearReactionStat(View view) {
        sCal.clearAll();
        sLFiles.saveInAllFile(StatisticsActivity.this);
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
        emailIntent.putExtra(Intent.EXTRA_TEXT, sCal.getStatString());

        try {
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        finish();
    }catch (android.content.ActivityNotFoundException ex) {
        Toast.makeText(StatisticsActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
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
            StatisticsListController.singleStatistics = gson.fromJson(in, arraylistType);
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
            Type arraylistType3 = new TypeToken<ArrayList<Integer>>() {}.getType();
            StatisticsListController.threePlayerBuzz = gson3.fromJson(in3, arraylistType3);
            String line3 = in3.readLine();
            while (line3 != null) {
                StatisticsListController.threePlayerBuzz.add(new Integer(line3));
                line3 = in3.readLine();
            }


            FileInputStream fis4 = openFileInput(FILENAME4);
            BufferedReader in4 = new BufferedReader(new InputStreamReader(fis4));
            Gson gson4 = new Gson();
            Type arraylistType4 = new TypeToken<ArrayList<Integer>>() {
            }.getType();
            StatisticsListController.fourPlayerBuzz = gson4.fromJson(in4, arraylistType4);
            String line4 = in4.readLine();
            while (line4 != null) {
                StatisticsListController.fourPlayerBuzz.add(new Integer(line4));
                line4 = in4.readLine();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            StatisticsListController.singleStatistics = new ArrayList<Double>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

}