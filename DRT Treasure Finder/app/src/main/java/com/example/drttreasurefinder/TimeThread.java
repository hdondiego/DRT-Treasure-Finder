package com.example.drttreasurefinder;

public class TimeThread extends Thread{
    private long startTime, currentTime;
    private float seconds;

    @Override
    public void run(){
        for (int i = 0; i < 60; i++){
            try{
                //Thread.sleep(600);
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                System.out.print("Something went wrong...");
            }

            currentTime = System.currentTimeMillis() - startTime;
            seconds = (float)currentTime / (float)1000;
            System.out.printf("Elapsed Time: %f\n", seconds);
        }
    }

    public TimeThread(){
        this.startTime = System.currentTimeMillis();
        this.currentTime = 0;
    }
}
