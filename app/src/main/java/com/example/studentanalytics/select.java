package com.example.studentanalytics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class select extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
    }
    public void open_attendancelist(View view)
    {
        Intent intent = new Intent(select.this,attendancelist.class);
        startActivity(intent);
    }
    public void open_resultlist(View view)
    {
        Intent intent = new Intent(select.this,Results.class);
        startActivity(intent);
    }
    public void open_remarks(View view)
    {
        Intent intent = new Intent(select.this,remarks.class);
        startActivity(intent);
    }
}
