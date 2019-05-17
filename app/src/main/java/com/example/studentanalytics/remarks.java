package com.example.studentanalytics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class remarks extends AppCompatActivity {

    TextView remarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remarks);

        remarks = findViewById(R.id.text_remarks);

        fetch_from_firebase();
    }

    public void go_back_remarks(View view) {
        Intent intent = new Intent(remarks.this, select.class);
        finish();
    }


    private void fetch_from_firebase() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        SharedPreferences sharedPreferences = getSharedPreferences("student_info", MODE_PRIVATE);

        String id = sharedPreferences.getString("id" , "");

        String _class = sharedPreferences.getString("class" , "");


        FirebaseAuth auth = FirebaseAuth.getInstance();


        DatabaseReference reference = database.getReference("remarks").child(_class).child(id);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists())
                {
                    remarksdatamodel datamodel = dataSnapshot.getValue(remarksdatamodel.class);
                    remarks.setText(datamodel.remarks);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}