package com.example.studentanalytics;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class resetpassword extends AppCompatActivity {

    TextView email;
    String email_s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

        email= findViewById(R.id.email_edit_text);
    }

    public  void  save_email(View view)
    {

        email_s=  email.getText().toString();
        FirebaseAuth.getInstance().sendPasswordResetEmail(email_s)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(resetpassword.this, "Email Sent!!!Check Email!!", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        else {
                            Toast.makeText(resetpassword.this ,"email does not exist" ,
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });


                    }
                }