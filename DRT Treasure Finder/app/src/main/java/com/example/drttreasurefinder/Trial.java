package com.example.drttreasurefinder;

import java.util.ArrayList;
import java.util.logging.Level;

public class Trial {
    public int trialNum;
    public ArrayList<Level> levels;
    public String date;

    public int getTrialNum() {
        return trialNum;
    }

    public void setTrialNum(int trialNum) {
        this.trialNum = trialNum;
    }

    public ArrayList<Level> getLevels() {
        return levels;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Trial() {}

    public Trial(int trialNum) {
        levels = new ArrayList<>();
        setTrialNum(trialNum);
    }
}
