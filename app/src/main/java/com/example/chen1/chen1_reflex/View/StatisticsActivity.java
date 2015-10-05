package com.example.chen1.chen1_reflex.View;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen1.chen1_reflex.Module.SaveLoadFiles;
import com.example.chen1.chen1_reflex.Module.StatisticsCalculator;
import com.example.chen1.chen1_reflex.R;


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

    //calculate and display the statistics
    public void calculateStatistics() {
        //first load the statistics from the files
        sLFiles.loadFromFile(StatisticsActivity.this);
        displayStatistics = (TextView) findViewById(R.id.statistics_text);
        displayStatistics.setMovementMethod(new ScrollingMovementMethod());
        //after calculating, display the result in the Textview
        sCal.calAll();
        displayStatistics.setText(sCal.getStatString());

    }

    //clear the result if the clear button is clicked, and save the cleared lists in the files
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
