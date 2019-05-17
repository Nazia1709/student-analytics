package com.example.studentanalytics;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class logintchr extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logintchr);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

    }

    public void sign_up(View v) {


        Intent i = new Intent(logintchr.this, signuptchr.class);
        startActivity(i);
    }

    public void open_drawer(View view) {

        EditText editText1 = findViewById(R.id.et_name);
        EditText editText2 = findViewById(R.id.et_email);
        EditText editText3 = findViewById(R.id.et_pswrd);


        final String name = editText1.getText().toString();
        final String email = editText2.getText().toString();
        final String pswrd = editText3.getText().toString();
        if (name.trim().equals("") || email.trim().equals("") || pswrd.trim().equals("")) {
            Toast.makeText(logintchr.this, "empty field try again", Toast.LENGTH_SHORT).show();
            return;

        }
        OnCompleteListener<AuthResult> listener = new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                Boolean bb = task.isSuccessful();

                if (bb) {
                    Toast.makeText(logintchr.this, "login successfull", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(logintchr.this, hometchr.class);
                    startActivity(intent);
                    finish();


                } else {

                    Toast.makeText(logintchr.this, "error try again", Toast.LENGTH_SHORT).show();


                }



            }




        };
        mAuth.signInWithEmailAndPassword(email , pswrd).addOnCompleteListener(logintchr.this , listener);





    }

    public  void open_forgot_password(View view)
    {
        Intent intent = new Intent(logintchr.this,resetpassword.class);
        startActivity(intent);
    }
}