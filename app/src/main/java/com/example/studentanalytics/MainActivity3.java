package com.example.studentanalytics;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity3 extends AppCompatActivity {
ProgressBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        bar = findViewById(R.id.progress_bar);
    }
    public  void move_next(View v)
    {
        bar.setVisibility(View.VISIBLE);
        EditText et_1 = findViewById(R.id.et_1);
        EditText et_2 = findViewById(R.id.et_2);
        EditText et_3 = findViewById(R.id.et_3);
        final String email = et_1.getText().toString();
        final String enterclass = et_2.getText().toString();
        final String id = et_3.getText().toString();


        if( email.trim().equals("")||enterclass.trim().equals("")||id.trim().equals("") )

        {
            Toast.makeText(MainActivity3.this, "empty field try again" , Toast.LENGTH_SHORT).show();
            bar.setVisibility(View.INVISIBLE);
            return;

        }


        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference("students").child(id);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {

                    addstudentmodel data = dataSnapshot.getValue(addstudentmodel.class);

                    if (data.class1.equals(enterclass) && data.name.equals(email)) {

                        SharedPreferences.Editor sp = getSharedPreferences("student_info", MODE_PRIVATE).edit();

                        sp.putString("id", data.id);
                        sp.putString("class" , data.class1);

                        sp.commit();
                        Toast.makeText(MainActivity3.this, "log in successful", Toast.LENGTH_SHORT).show();


                        Intent i = new Intent(MainActivity3.this, select.class);
                        startActivity(i);
                        finish();
                    }

                    else {
                        Toast.makeText(MainActivity3.this, "invalid data" , Toast.LENGTH_SHORT).show();

                    }
                }

                    else
                    {
                        Toast.makeText(MainActivity3.this, "invalid id" , Toast.LENGTH_SHORT).show();
                    }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }






}
