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

public class signuptchr extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuptchr);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        bar = findViewById(R.id.pro_bar);
    }
    public void sign_up2(View v )
    {
        bar.setVisibility(View.VISIBLE);
        EditText name_et = findViewById(R.id.et_tchr);
        EditText email_et = findViewById(R.id.et_eid);
        EditText password_et = findViewById(R.id.et_pass);
        EditText phone_et = findViewById(R.id.et_phone);
        EditText cls_et = findViewById(R.id.et_cls);
        EditText qualification_et = findViewById(R.id.et_qualification);

        final String name = name_et.getText().toString();
        final String email = email_et.getText().toString();
        String password = password_et.getText().toString();
        final String phone = phone_et.getText().toString();
        final String cls = cls_et.getText().toString();
        final String qualification = qualification_et.getText().toString();


        if( email.trim().equals("")||name.trim().equals("")||password.trim().equals("")||phone.trim().equals("")||cls.trim().equals("")||qualification.trim().equals(""))

        {
            Toast.makeText(signuptchr.this, "empty field try again" , Toast.LENGTH_SHORT).show();

            return;

        }
        OnCompleteListener<AuthResult> listener = new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                Boolean bb =   task.isSuccessful();

                if( bb )
                {
                    Toast.makeText(signuptchr.this , "successfuly registered" , Toast.LENGTH_SHORT).show();
                    bar.setVisibility(View.INVISIBLE);
                    DatabaseReference myRef = database.getReference("signuptchr");


                    DatabaseReference ref2 = myRef.child(email.replace(".",""));


                    tchrdatamodel dataModel = new tchrdatamodel(name , cls, phone,qualification);


                    ref2.setValue(dataModel);

                    Intent intent = new Intent(signuptchr.this,hometchr.class);
                    startActivity(intent);
                    finish();
                }
                else {

                    Toast.makeText(signuptchr.this , "error try again" , Toast.LENGTH_SHORT).show();
                    bar.setVisibility(View.INVISIBLE);


                }


            }
        };
        mAuth.createUserWithEmailAndPassword(email , password).addOnCompleteListener(signuptchr.this , listener);




    }

}
