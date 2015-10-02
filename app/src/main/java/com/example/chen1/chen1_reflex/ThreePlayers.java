package com.example.chen1.chen1_reflex;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;
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
import java.util.ArrayList;

public class ThreePlayers extends Activity {


    ThreePlayerManager threePlayerManager = new ThreePlayerManager();
    SaveLoadFiles saveLoadFiles = new SaveLoadFiles();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_players);
        saveLoadFiles.loadFromFile(ThreePlayers.this);

        AlertDialog alertDialog = new AlertDialog.Builder(ThreePlayers.this).create();
        alertDialog.setMessage("The Player Who Clicks Faster Wins");
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Start ",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        threePlayerManager.setDisplaying(false);
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_three_players, menu);
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
        saveLoadFiles.loadFromFile(ThreePlayers.this);
    }


    public void playerOne(View view){
        threePlayerManager.notePlayerOne();
        if (threePlayerManager.getClickedTimes() == 1) {
            threePlayerManager.checkResult();
            saveLoadFiles.saveInFile3(ThreePlayers.this);
            goToDialog();
        }
    }

    public void playerTwo(View view){
        threePlayerManager.notePlayerTwo();
        if (threePlayerManager.getClickedTimes() == 1) {
            threePlayerManager.checkResult();
            saveLoadFiles.saveInFile3(ThreePlayers.this);
            goToDialog();
        }

    }

    public void playerThree(View view){
        threePlayerManager.notePlayerThree();
        if (threePlayerManager.getClickedTimes() == 1) {
            threePlayerManager.checkResult();
            saveLoadFiles.saveInFile3(ThreePlayers.this);
            goToDialog();
        }

    }

    public void goToDialog(){
        AlertDialog alertDialog = new AlertDialog.Builder(ThreePlayers.this).create();
        alertDialog.setMessage(threePlayerManager.getText());
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Start Again",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        threePlayerManager.clearResult();
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

}
