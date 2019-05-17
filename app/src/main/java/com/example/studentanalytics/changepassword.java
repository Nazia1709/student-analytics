package com.example.studentanalytics;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class changepassword extends AppCompatActivity {

    FirebaseUser user;
    String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
    }

    public void go_back_change_password(View view)
    {
        Intent intent= new Intent(changepassword.this,settingsdrawer.class);
        finish();
    }

    public void changepassword(View view) {
        EditText old_pass=findViewById(R.id.old_et);
        final EditText new_pass=findViewById(R.id.new_et);
        EditText confirm_pass=findViewById(R.id.confirm_et);


        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        String oldpass=old_pass.getText().toString();
        String newpass=new_pass.getText().toString();
        String confirmpass=confirm_pass.getText().toString();

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            Toast.makeText(changepassword.this, "enter your email", Toast.LENGTH_SHORT).show();
            return;
        }


        String pattern = "^(?=.*[0-9])(?=.*[a-z]).{8,15}$";




        if(!newpass.matches(pattern) || new_pass.length() < 8)
        {
            Toast.makeText(changepassword.this, "password must contain atleast one alphabet , digit ,  and length must be 8 character", Toast.LENGTH_SHORT).show();
            return;
        }
        if (! confirmpass.equals(newpass))
        {

            Toast.makeText( changepassword.this ,"password do not match", Toast.LENGTH_SHORT).show();
            return;

        }
        if (! newpass.equals(oldpass))
        {
            user = FirebaseAuth.getInstance().getCurrentUser();
            AuthCredential credential = (AuthCredential) EmailAuthProvider
                    .getCredential( email , old_pass.getText().toString());
            user.reauthenticate(credential)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                user.updatePassword(new_pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {
                                            Toast.makeText(changepassword.this, "UPDATED SUCESSFULLY",Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(changepassword.this,settingsdrawer.class);
                                            startActivity(i);
                                        } else {
                                            Toast.makeText(changepassword.this, "ERROR",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Log.d(TAG, "Error auth failed");
                                Toast.makeText(changepassword.this, "ERROR",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }else {

            Toast.makeText(changepassword.this,"Old Password is Incorrect",Toast.LENGTH_SHORT).show();
        }

    }

    public void cpback(View view) {
        finish();
    }
}



