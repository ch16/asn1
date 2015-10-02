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

public class FourPlayers extends Activity {

    static final String FILENAME4 = "file4.sav";
    FourPlayerManager fourPlayerManager = new FourPlayerManager();
    SaveLoadFiles saveLoadFiles = new SaveLoadFiles();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_players);

        loadFromFile4();
        AlertDialog alertDialog = new AlertDialog.Builder(FourPlayers.this).create();
        alertDialog.setMessage("The Player Who Clicks Faster Wins");
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Start ",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        fourPlayerManager.setDisplaying(false);
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_four_players, menu);
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
        loadFromFile4();
    }

    public void playerOne(View view){
        fourPlayerManager.notePlayerOne();
        if (fourPlayerManager.getClickedTimes() == 1) {
            fourPlayerManager.checkResult();
            saveLoadFiles.saveInFile4(FourPlayers.this);
            goToDialog();
        }
    }

    public void playerTwo(View view){
        fourPlayerManager.notePlayerTwo();
        if (fourPlayerManager.getClickedTimes() == 1) {
            fourPlayerManager.checkResult();
            saveLoadFiles.saveInFile4(FourPlayers.this);
            goToDialog();
        }

    }

    public void playerThree(View view){
        fourPlayerManager.notePlayerThree();
        if (fourPlayerManager.getClickedTimes() == 1) {
            fourPlayerManager.checkResult();
            saveLoadFiles.saveInFile4(FourPlayers.this);
            goToDialog();
        }
    }

    public void playerFour(View view){
        fourPlayerManager.notePlayerFour();
        if (fourPlayerManager.getClickedTimes() == 1) {
            fourPlayerManager.checkResult();
            saveLoadFiles.saveInFile4(FourPlayers.this);
            goToDialog();
        }

    }


    public void goToDialog(){
        AlertDialog alertDialog = new AlertDialog.Builder(FourPlayers.this).create();
        alertDialog.setMessage(fourPlayerManager.getText());
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Start Again",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        fourPlayerManager.clearResult();
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


    public void loadFromFile4() {
        //chagne all the string arraylist into double arraylist
        try {
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
            StatisticsListController.fourPlayerBuzz = new ArrayList<Integer>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

}
