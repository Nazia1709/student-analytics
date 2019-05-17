package com.example.studentanalytics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class settingsdrawer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingsdrawer);
    }

    public void open_appinfo(View view)
    {
        Intent intent = new Intent(settingsdrawer.this,appinfo.class);
        startActivity(intent);
    }
    public void share_app(View view)
    {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey checkout this student analytics application");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    public void open_help(View view)
    {
        Intent intent = new Intent(settingsdrawer.this,help.class);
        startActivity(intent);
    }
    public void open_change_password(View view)
    {
        Intent intent = new Intent(settingsdrawer.this,changepassword.class);
        startActivity(intent);
    }
}
