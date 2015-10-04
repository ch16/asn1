package com.example.chen1.chen1_reflex.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.chen1.chen1_reflex.Module.FourPlayerManager;
import com.example.chen1.chen1_reflex.Module.SaveLoadFiles;
import com.example.chen1.chen1_reflex.R;

public class FourPlayers extends Activity {

    FourPlayerManager fourPlayerManager = new FourPlayerManager();
    SaveLoadFiles saveLoadFiles = new SaveLoadFiles();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_players);

        saveLoadFiles.loadFromFile(FourPlayers.this);
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
        saveLoadFiles.loadFromFile(FourPlayers.this);
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

}