package com.example.studentanalytics;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class marks extends AppCompatActivity {
    List<addstudentmodel> students_list;
    FirebaseDatabase database;
    String selected_class ;

    RecyclerView recycler_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);
        students_list = new ArrayList<>();

        database = FirebaseDatabase.getInstance();

        selected_class = getIntent().getStringExtra("selected_class");



        recycler_list = findViewById(R.id.marks_recycler);

        recycler_list.setLayoutManager(new LinearLayoutManager(marks.this, LinearLayoutManager.VERTICAL, false));

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


    private class ListHolder extends RecyclerView.ViewHolder {

        TextView AA1_text;
        TextView sub_1;
        TextView marks_1;
        EditText engmarks;
        EditText mathmarks;
        EditText punjmarks;
        EditText hindimarks;
        EditText compmarks;
        EditText histmarks;
        EditText geomarks;
        EditText physicsmarks;
        EditText chemmarks;
        EditText biomarks;
        ImageView imageView;



        public ListHolder(View itemView) {
            super(itemView);

            AA1_text = itemView.findViewById(R.id.marks_txt_A1);
            sub_1 = itemView.findViewById(R.id.sub_txt_marks);
            marks_1 = itemView.findViewById(R.id.marks_recycler);
            engmarks = itemView.findViewById(R.id.english_et);
            mathmarks = itemView.findViewById(R.id.maths_et);
            punjmarks = itemView.findViewById(R.id.punjabi_et);
            hindimarks = itemView.findViewById(R.id.hindi_et);
            compmarks = itemView.findViewById(R.id.comp_et);
            histmarks = itemView.findViewById(R.id.history_et);
            geomarks = itemView.findViewById(R.id.geo_et);
            physicsmarks = itemView.findViewById(R.id.physics_et);
            chemmarks = itemView.findViewById(R.id.chem_et);
            biomarks = itemView.findViewById(R.id.bio_et);

            imageView = itemView.findViewById(R.id.add_image_view);

        }


    }

    private class ListAdapter extends RecyclerView.Adapter<ListHolder> {


        @NonNull
        @Override
        public ListHolder onCreateViewHolder(@NonNull ViewGroup vi, int i) {

            View view = LayoutInflater.from(marks.this).inflate(R.layout.listcell_marks, vi, false);


            ListHolder holder = new ListHolder(view);

            return holder;


        }

        @Override
        public void onBindViewHolder(@NonNull final ListHolder listHolder, int i) {


            final addstudentmodel students_ = students_list.get(i);

            listHolder.AA1_text.setText(students_.name);

            listHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String emgmarks= listHolder.engmarks.getText().toString();
                    String mathmarks= listHolder.mathmarks.getText().toString();
                    String punjmarks= listHolder.punjmarks.getText().toString();
                    String hindimarks= listHolder.hindimarks.getText().toString();
                    String compmarks= listHolder.compmarks.getText().toString();
                    String histmarks= listHolder.histmarks.getText().toString();
                    String geomarks= listHolder.geomarks.getText().toString();
                    String physicsmarks= listHolder.physicsmarks.getText().toString();
                    String chemmarks= listHolder.chemmarks.getText().toString();
                    String biomarks= listHolder.biomarks.getText().toString();

                    marksdatamodel marksdatamodel = new marksdatamodel();

                    marksdatamodel.english = emgmarks;
                    marksdatamodel.mathematics = mathmarks;
                    marksdatamodel.punjabi = punjmarks;
                    marksdatamodel.hindi = hindimarks;
                    marksdatamodel.computer = compmarks;
                    marksdatamodel.history = histmarks;
                    marksdatamodel.geography = geomarks;
                    marksdatamodel.physics = physicsmarks;
                    marksdatamodel.chemistry = chemmarks;
                    marksdatamodel.biology = biomarks;

                    if (emgmarks.trim().equals("") || mathmarks.trim().equals("") ) {
                        Toast.makeText(marks.this, " Please fill all the essential fields", Toast.LENGTH_SHORT).show();

                        return;

                    }


                    DatabaseReference reference = database.getReference("marks").child(selected_class).child(students_.id);


                    reference.setValue(marksdatamodel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful())
                            {
                                Toast.makeText(marks.this , "saved successfully" , Toast.LENGTH_SHORT).show();
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

