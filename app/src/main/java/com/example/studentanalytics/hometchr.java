package com.example.studentanalytics;

import android.content.Intent;
import android.media.Image;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

public class hometchr extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hometchr);
    }
    public void swipe_left(View v)
    {
      DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
      drawerLayout.openDrawer(Gravity.START);

    }
    public void open_profile(View v)
    {
        Intent intent = new Intent(hometchr.this,profiletchr.class);
        startActivity(intent);
    }
    public void open_home(View v)
    {
        Intent i = new Intent(hometchr.this,hometchr.class);
        startActivity(i);
    }
    public void open_addclass(View v)
    {
        Intent i = new Intent(hometchr.this,addclass.class);
        startActivity(i);
    }
    public void open_addstud(View v)
    {
        Intent i = new Intent(hometchr.this,addstudent.class);
        startActivity(i);
    }
    public void open_recyclerattendance(View v)
    {
        Intent i = new Intent(hometchr.this,selectclass.class);

        i.putExtra("move_to" , "attendence");

        startActivity(i);
    }
    public void open_markslist(View v)
    {
        Intent i = new Intent(hometchr.this,selectclass.class);

        i.putExtra("move_to" , "marks");

        startActivity(i);
    }

    public  void add_remarks_drawer(View view)
    {

        Intent intent =  new Intent(hometchr.this,selectclass.class);


        intent.putExtra("move_to" , "add remarks");
        startActivity(intent);

    }

    public void open_settings_drawer(View v)
    {
        Intent i = new Intent(hometchr.this,settingsdrawer.class);
        startActivity(i);

    }

    public void open_logintchr(View v)
    {
        Intent i = new Intent(hometchr.this,logintchr.class);
        startActivity(i);
        finish();
    }



}
