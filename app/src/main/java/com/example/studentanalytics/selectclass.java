package com.example.studentanalytics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class selectclass extends AppCompatActivity {



    private Spinner spinClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectclas);
        spinClass = (Spinner) findViewById(R.id.spin);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spin_layout, getResources()
                .getStringArray(R.array.class_array));//setting the country_array to spinner
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinClass.setAdapter(adapter);

//if you want to set any action you can do in this listener
        spinClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });




            }
            public void move_to_attendance(View view)
            {
                String move_to = getIntent().getStringExtra("move_to");

                if(move_to.equals("attendence"))
                {
                    Intent intent = new Intent(selectclass.this,recyclerview.class);

                    intent.putExtra("selected_class" , spinClass.getSelectedItem().toString() );

                    startActivity(intent);
                }

                if(move_to.equals("marks"))
                {
                    Intent intent = new Intent(selectclass.this, marks.class);

                    intent.putExtra("selected_class" , spinClass.getSelectedItem().toString() );


                    startActivity(intent);
                }
                if(move_to.equals("add remarks"))
                {
                    Intent intent = new Intent(selectclass.this, addremarks.class);

                    intent.putExtra("selected_class" , spinClass.getSelectedItem().toString() );


                    startActivity(intent);
                }

            }







}
