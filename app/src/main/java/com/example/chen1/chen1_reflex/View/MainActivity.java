package com.example.chen1.chen1_reflex.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.chen1.chen1_reflex.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    //got to singleUser Activity is the single user mode button is clicked
    public void singleUser(View view){
        Intent intent = new Intent(MainActivity.this, SingleUserActivity.class);
        startActivity(intent);
    }

    //go to displayStat Activity if the statistics button is clicked
    public void displayStat(View view){
        Intent intent = new Intent(MainActivity.this, StatisticsActivity.class);
        startActivity(intent);
    }

    //reference: http://stackoverflow.com/questions/12244297/how-to-add-multiple-buttons-on-a-single-alertdialog
    //if the gameshow buzzer is clicked, the pop up the dialog to let user choose the number of players
    public void chooseNumOfPlayer(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Choose Number of Players");
        builder.setItems(new CharSequence[]
                        {"2 Players", "3 Players", "4 Players"},
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            //go to according activity by clicked the button of players
                            case 0:
                                Intent intent = new Intent(MainActivity.this, TwoPlayers.class);
                                startActivity(intent);
                                break;
                            case 1:
                                Intent intent1 = new Intent(MainActivity.this, ThreePlayers.class);
                                startActivity(intent1);
                                break;
                            case 2:
                                Intent intent2 = new Intent(MainActivity.this, FourPlayers.class);
                                startActivity(intent2);
                                break;
                        }
                    }
                });
        builder.create().show();
    }


}
