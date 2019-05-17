package com.example.studentanalytics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void move_login(View v)
    {
        Intent i= new Intent(MainActivity2.this,MainActivity3.class);
        startActivity(i);



    }
    public void move_login1(View v)
    {
        Intent i= new Intent(MainActivity2.this,logintchr.class);
        startActivity(i);
    }
}
