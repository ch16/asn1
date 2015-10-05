package com.example.chen1.chen1_reflex.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen1.chen1_reflex.Module.ReactionTimer;
import com.example.chen1.chen1_reflex.Module.SaveLoadFiles;
import com.example.chen1.chen1_reflex.Module.StatisticsListStorage;
import com.example.chen1.chen1_reflex.R;

import java.util.Random;

public class SingleUserActivity extends Activity {

    TextView displayedResult;


    ReactionTimer reactionTimer;
    SaveLoadFiles saveLoadFiles = new SaveLoadFiles();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_user);
        displayedResult = (TextView) findViewById(R.id.reaction_time_display);
        saveLoadFiles.loadFromFile(SingleUserActivity.this);
        reactionTimer = new ReactionTimer(SingleUserActivity.this);

        AlertDialog alertDialog = new AlertDialog.Builder(SingleUserActivity.this).create();
        alertDialog.setTitle("Introduction");
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setMessage("This game is to test your reaction. DO NOT Click when \"Wait\" is shown, click AS SOON AS you see \"CLICK NOW!\" ");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        reactionTimer.setStart(true);
                        reactionTimer.startTimer();
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

    @Override
    protected void onStart() {
        super.onStart();
        saveLoadFiles.loadFromFile(SingleUserActivity.this);
        reactionTimer = new ReactionTimer(SingleUserActivity.this);
    }

    //if buzz is clicked, then check if the time is up, if it's up, then record the lag into single statistics list
    public void singleClick(View view){
        reactionTimer.checkReflex();
        if (reactionTimer.getTimeUp() == true) {
            saveLoadFiles.saveInFileSingle(SingleUserActivity.this);
        }
    }


}
