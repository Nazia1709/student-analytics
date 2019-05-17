package com.example.studentanalytics;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profiletchr extends AppCompatActivity {

    TextView textViewname;
    TextView textViewclass;
    TextView textViewqualification;
    TextView textViewemail;
    TextView textViewcontact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiletchr);


         textViewname = findViewById(R.id.txt_name);
         textViewclass = findViewById(R.id.txt_class);
         textViewqualification = findViewById(R.id.txt_qualification);
         textViewemail = findViewById(R.id.txt_emailid);
         textViewcontact = findViewById(R.id.txt_contact);
        fetch_from_firebase();

    }



    private void fetch_from_firebase()
    {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        FirebaseAuth auth = FirebaseAuth.getInstance();

        String email = auth.getCurrentUser().getEmail().replace("." ,"");

        DatabaseReference reference = database.getReference("signuptchr").child(email);
        textViewemail.setText(email);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                tchrdatamodel dataModel = dataSnapshot.getValue(tchrdatamodel.class);
                textViewname.setText(dataModel.name);
                textViewclass.setText(dataModel.cls);
                textViewcontact.setText(dataModel.phone);
                textViewqualification.setText(dataModel.qualification);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
