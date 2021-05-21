package com.example.drttreasurefinder;

public class Coordinate {
    private float x;
    private float y;
    private double seconds;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public double getSeconds() {
        return seconds;
    }

    public void setSeconds(double seconds) {
        this.seconds = seconds;
    }

    public Coordinate() {}

    public Coordinate(float x, float y, double seconds) {
        setX(x);
        setY(y);
        setSeconds(seconds);
    }
}
