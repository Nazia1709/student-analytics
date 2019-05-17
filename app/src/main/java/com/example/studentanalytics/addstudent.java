package com.example.studentanalytics;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addstudent extends AppCompatActivity {

    FirebaseDatabase database;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;
    EditText editText6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudent);
        database = FirebaseDatabase.getInstance();


        editText1 = findViewById(R.id.name_std);
        editText2 = findViewById(R.id.cls_std);
        editText3 = findViewById(R.id.std_id);
        editText4 = findViewById(R.id.prnt_std);
        editText5 = findViewById(R.id.phone_std);
        editText6 = findViewById(R.id.mail_std);
    }

    public void add_student(View v) {
        final String name = editText1.getText().toString();
        final String class1 = editText2.getText().toString();
        final String id = editText3.getText().toString();
        final String parentname = editText4.getText().toString();
        final String parentcontact = editText5.getText().toString();
        final String parentemail = editText6.getText().toString();


        if (name.trim().equals("") || class1.trim().equals("") || id.trim().equals("") || parentname.trim().equals("") || parentcontact.trim().equals("") || parentemail.trim().equals("")) {
            Toast.makeText(addstudent.this, "empty field try again", Toast.LENGTH_SHORT).show();

            return;

        }
        OnCompleteListener<Void> listener = new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                Boolean bb = task.isSuccessful();

                if (bb) {
                    Toast.makeText(addstudent.this, "successfuly registered", Toast.LENGTH_SHORT).show();



                }
                else {

                    Toast.makeText(addstudent.this , "error try again" , Toast.LENGTH_SHORT).show();



                }


            }
        };


        DatabaseReference myRef = database.getReference("students");


        DatabaseReference ref2 = myRef.child(id);


        addstudentmodel dataModel = new addstudentmodel(name, class1, id, parentname, parentcontact, parentemail);


        ref2.setValue(dataModel).addOnCompleteListener(listener);



        }
        public void update_student(View view)
        {
            final String name = editText1.getText().toString();
            final String class1 = editText2.getText().toString();
            final String id = editText3.getText().toString();
            final String parentname = editText4.getText().toString();
            final String parentcontact = editText5.getText().toString();
            final String parentemail = editText6.getText().toString();


            if (name.trim().equals("") || class1.trim().equals("") || id.trim().equals("") || parentname.trim().equals("") || parentcontact.trim().equals("") || parentemail.trim().equals("")) {
                Toast.makeText(addstudent.this, "empty field try again", Toast.LENGTH_SHORT).show();

                return;

            }
            OnCompleteListener<Void> listener = new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    Boolean bb = task.isSuccessful();

                    if (bb) {
                        Toast.makeText(addstudent.this, "successfuly registered", Toast.LENGTH_SHORT).show();



                    }
                    else {

                        Toast.makeText(addstudent.this , "error try again" , Toast.LENGTH_SHORT).show();



                    }


                }
            };
            DatabaseReference myRef = database.getReference("students");


            DatabaseReference ref2 = myRef.child(id);


            addstudentmodel dataModel = new addstudentmodel(name, class1, id, parentname, parentcontact, parentemail);


            ref2.setValue(dataModel).addOnCompleteListener(listener);

        }
}

