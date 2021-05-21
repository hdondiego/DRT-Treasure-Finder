package com.example.drttreasurefinder;

import java.util.ArrayList;

public class Level {
    private int levelNum;
    private ArrayList<Coordinate> coordinates;
    private float startXLocation;
    private float startYLocation;
    private float targetXLocation;
    private float targetYLocation;

    public int getLevelNum() {
        return levelNum;
    }

    public void setLevelNum(int levelNum) {
        this.levelNum = levelNum;
    }

    public ArrayList<Coordinate> getCoordinates() {
        return coordinates;
    }

    public float getStartXLocation() {
        return startXLocation;
    }

    public void setStartXLocation(float startXLocation) {
        this.startXLocation = startXLocation;
    }

    public float getStartYLocation() {
        return startYLocation;
    }

    public void setStartYLocation(float startYLocation) {
        this.startYLocation = startYLocation;
    }

    public float getTargetXLocation() {
        return targetXLocation;
    }

    public void setTargetXLocation(float targetXLocation) {
        this.targetXLocation = targetXLocation;
    }

    public float getTargetYLocation() {
        return targetYLocation;
    }

    public void setTargetYLocation(float targetYLocation) {
        this.targetYLocation = targetYLocation;
    }

    public Level() {}

    public Level(int levelNum, float startXLocation, float startYLocation,
                 float targetXLocation, float targetYLocation) {
        coordinates = new ArrayList<>();
        setLevelNum(levelNum);
        setStartXLocation(startXLocation);
        setStartYLocation(startYLocation);
        setTargetXLocation(targetXLocation);
        setTargetYLocation(targetYLocation);
    }
}
