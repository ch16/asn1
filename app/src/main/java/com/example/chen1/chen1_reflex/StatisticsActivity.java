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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        sLFiles.loadFromFile(StatisticsActivity.this);
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
        sLFiles.loadFromFile(StatisticsActivity.this);
    }

    public void calculateStatistics() {

        sLFiles.loadFromFile(StatisticsActivity.this);
        displayStatistics = (TextView) findViewById(R.id.statistics_text);
        displayStatistics.setMovementMethod(new ScrollingMovementMethod());

        sCal.calAll();
        displayStatistics.setText(sCal.getStatString());

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


}