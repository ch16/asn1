package com.example.chen1.chen1_reflex.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.chen1.chen1_reflex.Module.SaveLoadFiles;
import com.example.chen1.chen1_reflex.Module.TwoPlayerManager;
import com.example.chen1.chen1_reflex.R;

public class TwoPlayers extends Activity {

    TwoPlayerManager twoPlayerManager = new TwoPlayerManager();
    SaveLoadFiles saveLoadFiles = new SaveLoadFiles();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_players);
        saveLoadFiles.loadFromFile(TwoPlayers.this);

        AlertDialog alertDialog = new AlertDialog.Builder(TwoPlayers.this).create();
        alertDialog.setMessage("The Player Who Clicks Faster Wins");
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Start ",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_two_players, menu);
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
        saveLoadFiles.loadFromFile(TwoPlayers.this);
    }

    /*if player one clicks, call the method from the TwoPlayerManager, note that player one has clicks, 
    increament the clicked time by one, only if it's one, then call manager and check the result and go to the dialog*/
    public void playerOne(View view){
        twoPlayerManager.notePlayerOne();
        if (twoPlayerManager.getClickedTimes() == 1) {
            twoPlayerManager.checkTwoResults();
            saveLoadFiles.saveInFile2(TwoPlayers.this);
            goToDialog();
        }
    }

    //if player two clicks, call the method from TwoPlayerManager, note that player two has clicks
    public void playerTwo(View view){
        twoPlayerManager.notePlayerTwo();
        if (twoPlayerManager.getClickedTimes() == 1) {
            twoPlayerManager.checkTwoResults();
            saveLoadFiles.saveInFile2(TwoPlayers.this);
            goToDialog();
        }
    }

    //display the result of who wins and start the next round after dismissing
    public void goToDialog(){
    AlertDialog alertDialog = new AlertDialog.Builder(TwoPlayers.this).create();
        alertDialog.setMessage(twoPlayerManager.getText());
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Start Again",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        twoPlayerManager.clearTwoResults();
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
