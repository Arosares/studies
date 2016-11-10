package com.example.aro.cowcounter;

/**
 * Created by aro on 04.11.16.
 */

public class Cow {
    private int breed;
    private int cowID;

    public Cow(int breed, int cowID){
        this.breed = breed;
        this.cowID = cowID;
    }

    public int getBreed() {
        return breed;
    }

    public int getCowID() {
        return cowID;
    }
}
