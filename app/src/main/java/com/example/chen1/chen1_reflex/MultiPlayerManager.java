package com.example.chen1.chen1_reflex;

/**
 * Created by chen1 on 10/2/15.
 */
public class MultiPlayerManager {
    public MultiPlayerManager() {
    }

    boolean displaying = true;
    int clickedTimes = 0;

    String resultText = "";


    public void setDisplaying(boolean tf){
        displaying =tf;
    }

    public String getText(){
        return resultText;
    }

    public int getClickedTimes(){
        return clickedTimes;
    }


}
