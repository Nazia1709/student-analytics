package com.example.studentanalytics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class appinfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appinfo);
    }
    public void go_back(View v )
    {
        Intent intent =  new Intent(appinfo.this,settingsdrawer.class);

        finish();
    }
}
