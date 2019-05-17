package com.example.studentanalytics;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addremarks extends AppCompatActivity {

    Button addremarks;
    EditText remarkset;
    EditText student_id;

    String _class;

    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addremarks);

        addremarks = findViewById(R.id.button_remarks);
        remarkset = findViewById(R.id.remarks_et);
        student_id = findViewById(R.id.id_et);

        database= FirebaseDatabase.getInstance();

        _class = getIntent().getStringExtra("selected_class");

        addremarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String remarks = remarkset.getText().toString();
                String id = student_id.getText().toString();

                if (remarks.trim().equals("") || id.trim().equals("") ) {


                    Toast.makeText(addremarks.this, "empty field try again", Toast.LENGTH_SHORT).show();

                    return;

                }
                OnCompleteListener<Void> listener = new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Boolean bb = task.isSuccessful();

                        if (bb) {
                            Toast.makeText(addremarks.this, "remark added", Toast.LENGTH_SHORT).show();


                        } else {

                            Toast.makeText(addremarks.this, "error try again", Toast.LENGTH_SHORT).show();


                        }
                    }

                };

                DatabaseReference myRef = database.getReference("remarks");


                DatabaseReference ref2 = myRef.child(_class).child(id);

                remarksdatamodel dataModel = new remarksdatamodel(id,remarks);

                ref2.setValue(dataModel).addOnCompleteListener(listener);








    }
        });
    }
        }

