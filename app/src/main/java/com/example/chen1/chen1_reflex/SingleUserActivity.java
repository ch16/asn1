package com.example.chen1.chen1_reflex;

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

import java.util.Random;

public class SingleUserActivity extends AppCompatActivity {

    TextView displayedResult;
    CountDownTimer ctimer;
    CountDownTimer againTimer;
    Random random = new Random();
    String temp;

    boolean timeUp = false;
    double startTime;
    double endTime;

    int randomTime;
    boolean displaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_user);
        displayedResult = (TextView)findViewById(R.id.reaction_time_display);
        AlertDialog alertDialog = new AlertDialog.Builder(SingleUserActivity.this).create();
        alertDialog.setTitle("Introduction");
        alertDialog.setMessage("This game is to test your reaction. DO NOT Click when \"Wait\" is shown, click AS SOON AS you see \"CLICK NOW!\" ");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
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
        ctimer = new CountDownTimer(randomTime,1) {
            @Override
            public void onTick(long millisUntilFinished) {
                displaying = false;
                displayedResult.setText("Wait");
                timeUp = false;
            }

            @Override
            public void onFinish() {
                displayedResult.setText("CLICK NOW!");
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
                startGame();
            }
        }.start();
    }



    public void checkReflex(View view){
        if (timeUp == false){
            displayedResult.setText("You clicked early! Now start again!");
            startGame();
        }else if (timeUp == true && displaying == false){

            endTime = System.currentTimeMillis();
            double duration = endTime - startTime;
            temp = Double.toString(duration/1000);

            StatisticsListController.getSingleStatistics().add(duration);

            startAgain();
        }
    }


}
