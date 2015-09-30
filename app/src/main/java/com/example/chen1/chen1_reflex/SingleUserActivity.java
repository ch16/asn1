package com.example.chen1.chen1_reflex;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;

public class SingleUserActivity extends Activity {

    TextView displayedResult;
    CountDownTimer ctimer;
    CountDownTimer againTimer;
    Random random = new Random();
    String temp;

    boolean early = false;
    boolean timeUp = false;
    boolean start = false;
    double startTime;
    double endTime;

    int randomTime;
    boolean displaying = false;
    static final String FILENAME = "file.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_user);
        displayedResult = (TextView)findViewById(R.id.reaction_time_display);
        AlertDialog alertDialog = new AlertDialog.Builder(SingleUserActivity.this).create();
        alertDialog.setTitle("Introduction");
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setMessage("This game is to test your reaction. DO NOT Click when \"Wait\" is shown, click AS SOON AS you see \"CLICK NOW!\" ");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        start = true;
                        dialog.dismiss();
                        startGame();
                    }
                });
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_user, menu);
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

    public void startGame(){
        randomTime = random.nextInt(2000)+10;
        ctimer = new CountDownTimer(randomTime,10) {
            @Override
            public void onTick(long millisUntilFinished) {
                displaying = false;
                displayedResult.setText("Wait");
                timeUp = false;
            }

            @Override
            public void onFinish() {
                if (timeUp == false){
                displayedResult.setText("CLICK NOW!");}
                timeUp = true;
                startTime = System.currentTimeMillis();
            }
        }.start();


    }

    public void startAgain(){
        againTimer = new CountDownTimer(1000,1){

            @Override
            public void onTick(long millisUntilFinished) {
                displaying = true;
                displayedResult.setText("the lag is " + temp + " seconds" + "\nThe game will start again");
            }

            @Override
            public void onFinish() {
                displayedResult.setText("Wait");
                startGame();
            }
        }.start();
    }


    public void checkReflex(View view){
        if (timeUp == false && start == true){
            Toast.makeText(this,"You clicked early! Timer Now start again!",Toast.LENGTH_SHORT).show();
            early = true;
            ctimer.cancel();
            startGame();
            displayedResult.setText("Wait");
        }else if (timeUp == true && displaying == false && start == true){

            endTime = System.currentTimeMillis();
            double duration = endTime - startTime;
            temp = Double.toString(duration/1000);

            StatisticsListController.getSingleStatistics().add(duration);
            saveInSinleFile();
            startAgain();
        }else if (start == false){return;}
    }

    public void saveInSinleFile(){

        try {
            FileOutputStream fos = openFileOutput(FILENAME,MODE_PRIVATE);
            Gson gson = new Gson();
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            gson.toJson(StatisticsListController.getSingleStatistics(), out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

}
