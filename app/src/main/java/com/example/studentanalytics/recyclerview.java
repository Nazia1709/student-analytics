package com.example.studentanalytics;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class recyclerview extends AppCompatActivity {
    List<addstudentmodel> students_list;

    RecyclerView recycler_list;

    FirebaseDatabase database;

    String selected_class ;
    String formattedDate;

    String year ;

    String month;

    String date ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        students_list = new ArrayList<>();

        database = FirebaseDatabase.getInstance();



        recycler_list = findViewById(R.id.recycler_list);

        recycler_list.setLayoutManager(new LinearLayoutManager(recyclerview.this, LinearLayoutManager.VERTICAL, false));



        selected_class = getIntent().getStringExtra("selected_class");

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        formattedDate = df.format(c);

        year = formattedDate.split("-")[2];

        month = formattedDate.split("-")[1];

        date = formattedDate.split("-")[0];

        get_data_firebase();


    }

    private void get_data_firebase() {



        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference("students");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    addstudentmodel model = snapshot.getValue(addstudentmodel.class);

                    if(model.class1.equals(selected_class)) {

                        students_list.add(model);
                    }

                }

                ListAdapter adapter = new ListAdapter();

                recycler_list.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    static class ListHolder extends RecyclerView.ViewHolder {

        TextView A1_text;
        RadioGroup attendence_radio_group;

        public ListHolder(View itemView) {
            super(itemView);

            A1_text = itemView.findViewById(R.id.A1_text);

            attendence_radio_group = itemView.findViewById(R.id.attendence_radio_group);
        }


    }

    public  class ListAdapter extends RecyclerView.Adapter<ListHolder> {


        @NonNull
        @Override
        public ListHolder onCreateViewHolder(@NonNull ViewGroup vi, int i) {

            View view = LayoutInflater.from(recyclerview.this).inflate(R.layout.list_cell, vi, false);


            ListHolder holder = new ListHolder(view);

            return holder;


        }

        @Override
        public void onBindViewHolder(@NonNull ListHolder listHolder, int i) {

            final addstudentmodel students_data = students_list.get(i);

            listHolder.A1_text.setText(students_data.name);

            listHolder.attendence_radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {

                    attendence_data_model attendenceDataModel = new attendence_data_model();

                    if(i == R.id.radio_present)
                    {
                        attendenceDataModel.status = "Present";
                    }

                    if(i == R.id.radio_absent)
                    {
                        attendenceDataModel.status = "Absent";
                    }

                    if(i == R.id.radio_leave)
                    {
                        attendenceDataModel.status = "Leave";
                    }

                    DatabaseReference reference = database.getReference("attendence").child(selected_class).child(students_data.id).child(year).child(month).child(date);


                    reference.setValue(attendenceDataModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful())
                            {
                                Toast.makeText(recyclerview.this , "saved successfully" , Toast.LENGTH_SHORT).show();
                            }
                        }
                    });



                }
            });


        }

        @Override
        public int getItemCount() {

            return students_list.size();

        }
    }
}
