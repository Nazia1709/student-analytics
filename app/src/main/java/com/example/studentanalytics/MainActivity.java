package com.example.studentanalytics;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.nio.charset.MalformedInputException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler handler = new Handler();

        Runnable r = new Runnable()
        {

            public void run(){
                Intent i = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(i);
                finish();


                // code you want to run after some time
            }

        };

        handler.postDelayed(r , 2000);
    }
}