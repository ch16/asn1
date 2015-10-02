package com.example.chen1.chen1_reflex;

import android.app.Activity;
import android.content.Context;

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

import static com.example.chen1.chen1_reflex.StatisticsListController.getFourPlayerStatistics;
import static com.example.chen1.chen1_reflex.StatisticsListController.getSingleStatistics;
import static com.example.chen1.chen1_reflex.StatisticsListController.getThreePlayerStatistics;
import static com.example.chen1.chen1_reflex.StatisticsListController.getTwoPlayerStatistics;
import static com.example.chen1.chen1_reflex.StatisticsListController.singleStatistics;
import static com.example.chen1.chen1_reflex.StatisticsListController.twoPlayerBuzz;

/**
 * Created by chen1 on 10/2/15.
 */
public class SaveLoadFiles extends Activity{
    public SaveLoadFiles() {
    }

    static final String FILENAME = "file.sav";
    static final String FILENAME2 = "file2.sav";
    static final String FILENAME3 = "file3.sav";
    static final String FILENAME4 = "file4.sav";


    public void saveInFileSingle(Context context){

        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, MODE_PRIVATE);
            Gson gson = new Gson();
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            gson.toJson(getSingleStatistics(), out);
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

    public void saveInFile2(Context context){

        try {
            FileOutputStream fos2 = context.openFileOutput(FILENAME2, MODE_PRIVATE);
            Gson gson2 = new Gson();
            BufferedWriter out2 = new BufferedWriter(new OutputStreamWriter(fos2));
            gson2.toJson(getTwoPlayerStatistics(), out2);
            out2.flush();
            fos2.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    public void saveInFile3(Context context){

        try {
            FileOutputStream fos3 = context.openFileOutput(FILENAME3, MODE_PRIVATE);
            Gson gson3 = new Gson();
            BufferedWriter out3 = new BufferedWriter(new OutputStreamWriter(fos3));
            gson3.toJson(getThreePlayerStatistics(), out3);
            out3.flush();
            fos3.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    public void saveInFile4(Context context){

        try {
            FileOutputStream fos4 = context.openFileOutput(FILENAME4, MODE_PRIVATE);
            Gson gson4 = new Gson();
            BufferedWriter out4 = new BufferedWriter(new OutputStreamWriter(fos4));
            gson4.toJson(getFourPlayerStatistics(), out4);

            out4.flush();

            fos4.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }


    public void saveInAllFile(Context context){

        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, MODE_PRIVATE);
            FileOutputStream fos2 = context.openFileOutput(FILENAME2, MODE_PRIVATE);
            FileOutputStream fos3 = context.openFileOutput(FILENAME3, MODE_PRIVATE);
            FileOutputStream fos4 = context.openFileOutput(FILENAME4, MODE_PRIVATE);

            Gson gson = new Gson();
            Gson gson2 = new Gson();
            Gson gson3 = new Gson();
            Gson gson4 = new Gson();

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            BufferedWriter out2 = new BufferedWriter(new OutputStreamWriter(fos2));
            BufferedWriter out3 = new BufferedWriter(new OutputStreamWriter(fos3));
            BufferedWriter out4 = new BufferedWriter(new OutputStreamWriter(fos4));

            gson.toJson(getSingleStatistics(), out);
            gson2.toJson(getTwoPlayerStatistics(), out2);
            gson3.toJson(getThreePlayerStatistics(), out3);
            gson4.toJson(getFourPlayerStatistics(), out4);

            out.flush();
            out2.flush();
            out3.flush();
            out4.flush();

            fos.close();
            fos2.close();
            fos3.close();
            fos4.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }


    public void loadFromFile(Context context) {
        //chagne all the string arraylist into double arraylist
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type arraylistType = new TypeToken<ArrayList<Double>>() {
            }.getType();
            StatisticsListController.singleStatistics = gson.fromJson(in, arraylistType);
            String line = in.readLine();
            while (line != null) {
                StatisticsListController.singleStatistics.add(new Double(line));
                line = in.readLine();
            }

            FileInputStream fis2 = context.openFileInput(FILENAME2);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(fis2));
            Gson gson2 = new Gson();
            Type arraylistType2 = new TypeToken<ArrayList<Integer>>() {
            }.getType();
            StatisticsListController.twoPlayerBuzz = gson2.fromJson(in2, arraylistType2);
            String line2 = in2.readLine();
            while (line2 != null) {
                StatisticsListController.twoPlayerBuzz.add(new Integer(line2));
                line2 = in2.readLine();
            }

            FileInputStream fis3 = context.openFileInput(FILENAME3);
            BufferedReader in3 = new BufferedReader(new InputStreamReader(fis3));
            Gson gson3 = new Gson();
            Type arraylistType3 = new TypeToken<ArrayList<Integer>>() {}.getType();
            StatisticsListController.threePlayerBuzz = gson3.fromJson(in3, arraylistType3);
            String line3 = in3.readLine();
            while (line3 != null) {
                StatisticsListController.threePlayerBuzz.add(new Integer(line3));
                line3 = in3.readLine();
            }


            FileInputStream fis4 = context.openFileInput(FILENAME4);
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
            StatisticsListController.singleStatistics = new ArrayList<Double>();
            StatisticsListController.twoPlayerBuzz = new ArrayList<Integer>();
            StatisticsListController.threePlayerBuzz = new ArrayList<Integer>();
            StatisticsListController.fourPlayerBuzz = new ArrayList<Integer>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }




}
