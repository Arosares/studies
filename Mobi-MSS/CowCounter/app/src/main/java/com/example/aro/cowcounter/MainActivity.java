package com.example.aro.cowcounter;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Cow> cowList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final EditText textFieldBreed = (EditText) findViewById(R.id.editText);
        final EditText textFieldID = (EditText) findViewById(R.id.editText2);
        Button addButton = (Button) findViewById(R.id.button);
        addButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Editable breed = textFieldBreed.getText();
                Editable cowID = textFieldID.getText();


                Cow cow = new Cow(breed, cowID);
                cowList.add(cow);
                System.out.println("Created Cow:");
                System.out.println("Breed: " + cow.getBreed() + " ID: " + cow.getCowID());
                System.out.println("CowList: "+ cowList);
            }
        });

        Button rejButton = (Button) findViewById(R.id.button2);
        rejButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Editable breed = textFieldBreed.getText();
                Editable cowID = textFieldID.getText();

                Cow toDelete = new Cow(breed, cowID);
                List<Cow> found = new ArrayList<Cow>();
                for (Cow cow : cowList) {
                    if (cow.getBreed() == toDelete.getBreed() && cow.getCowID() == toDelete.getCowID()){
                        found.add(cow);
                        System.out.println(cow);
                    }
                }
                cowList.removeAll(found);
                System.out.println("Removed Cow(s)\n" + cowList);
                

            }
        });


    }


}
