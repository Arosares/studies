package com.example.aro.cowcounter;

import android.text.Editable;

/**
 * Created by aro on 04.11.16.
 */

public class Cow {
    private Editable breed;
    private Editable cowID;

    public Cow(Editable breed, Editable cowID){
        this.breed = breed;
        this.cowID = cowID;
    }

    public Editable getBreed() {
        return breed;
    }

    public Editable getCowID() {
        return cowID;
    }

    @Override
    public String toString(){
        return "Cow [breed: " + breed + ", id: " + cowID + "]";
    }
}
