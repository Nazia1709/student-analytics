package com.example.studentanalytics;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addclass extends AppCompatActivity {
    FirebaseDatabase database;
    EditText textname;
    EditText textclass;
    EditText textemail;
    EditText textphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addclass);

        database = FirebaseDatabase.getInstance();


        textname = findViewById(R.id.edit_txtname);
        textclass = findViewById(R.id.edit_txtclass);
        textemail = findViewById(R.id.edit_txtemail);
        textphone = findViewById(R.id.edit_txtphone);
    }

    public void submit_addclass(View view) {
        final String name = textname.getText().toString();
        final String class2 = textclass.getText().toString();
        final String emailid = textemail.getText().toString();
        final String phone = textphone.getText().toString();


        if (name.trim().equals("") || class2.trim().equals("") || emailid.trim().equals("") || phone.trim().equals("")) {


            Toast.makeText(addclass.this, "empty field try again", Toast.LENGTH_SHORT).show();

            return;

        }
        OnCompleteListener<Void> listener = new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                Boolean bb = task.isSuccessful();

                if (bb) {
                    Toast.makeText(addclass.this, "successfuly registered", Toast.LENGTH_SHORT).show();


                } else {

                    Toast.makeText(addclass.this, "error try again", Toast.LENGTH_SHORT).show();


                }
            }

        };

        DatabaseReference myRef = database.getReference("teacher");


        DatabaseReference ref2 = myRef.child(name);

        classdatamodel dataModel = new classdatamodel(name, class2, emailid, phone);


        ref2.setValue(dataModel).addOnCompleteListener(listener);
    }
}



