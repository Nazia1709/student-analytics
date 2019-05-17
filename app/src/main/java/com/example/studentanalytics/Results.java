package com.example.studentanalytics;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Results extends AppCompatActivity {

    BarChart chart;

    TextView markeng;
    TextView markmath;
    TextView markpunjabi;
    TextView markhindi;
    TextView markhistory;
    TextView markgeo;
    TextView markcomp;
    TextView markphysics;
    TextView markchem;
    TextView markbio;

    float mark_sub1 = -1;
    float mark_sub2 = -1;
    float mark_sub3 = -1;
    float mark_sub4 = -1;
    float mark_sub5 = -1;
    float mark_sub6 = -1;
    float mark_sub7 = -1;
    float mark_sub8 = -1;
    float mark_sub9 = -1;
    float mark_sub10 = -1;

    private ImageView result_image ;

    private TextView result_remark_text , percentage_text ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        markeng = findViewById(R.id.markeng);
        markmath = findViewById(R.id.markmath);
        markpunjabi = findViewById(R.id.markpunjabi);
        markhindi = findViewById(R.id.markhindi);
        markhistory = findViewById(R.id.markhistory);
        markgeo = findViewById(R.id.markgeo);
        markcomp = findViewById(R.id.markcomp);
        markphysics = findViewById(R.id.markphysics);
        markchem = findViewById(R.id.markchem);
        markbio = findViewById(R.id.markbio);

        chart = findViewById(R.id.result_bar_chart);
        result_image = findViewById(R.id.result_image);

        result_remark_text = findViewById(R.id.result_remark);

        percentage_text = findViewById(R.id.percentage);

        get_result();

    }

    private void make_resultbar_chart()
    {
        ArrayList<BarEntry> BarEntry = new ArrayList<>();

        BarEntry.add(new BarEntry(mark_sub1, 0));
        BarEntry.add(new BarEntry(mark_sub2, 1));
        BarEntry.add(new BarEntry(mark_sub3, 2));
        BarEntry.add(new BarEntry(mark_sub4, 3));
        BarEntry.add(new BarEntry(mark_sub5, 4));
        BarEntry.add(new BarEntry(mark_sub6, 5));
        BarEntry.add(new BarEntry(mark_sub7, 6));
        BarEntry.add(new BarEntry(mark_sub8, 7));
        BarEntry.add(new BarEntry(mark_sub9, 8));
        BarEntry.add(new BarEntry(mark_sub10, 9));

        BarDataSet dataSet = new BarDataSet(BarEntry, "Projects");

        ArrayList<String> labels = new ArrayList<>();

        labels.add("eng");
        labels.add("math");
        labels.add("hindi");
        labels.add("punj");
        labels.add("comp");
        labels.add("phy");
        labels.add("chem");
        labels.add("bio");
        labels.add("hist");
        labels.add("geo");

        BarData data = new BarData( labels , dataSet);

        chart.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
    }

    private void get_result()
    {

        SharedPreferences sharedPreferences = getSharedPreferences("student_info", MODE_PRIVATE);

        String id = sharedPreferences.getString("id" , "");

        String _class = sharedPreferences.getString("class" , "");

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference("marks");

        reference.child(_class).child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if(dataSnapshot.exists())
                {
                    marksdatamodel datamodel = dataSnapshot.getValue(marksdatamodel.class);

                    if(!datamodel.english.equals(""))
                    {
                        markeng.setText(datamodel.english);

                        mark_sub1 = Float.parseFloat(datamodel.english);
                    }
                    if(!datamodel.mathematics.equals(""))
                    {
                        markmath.setText(datamodel.mathematics);

                        mark_sub2 = Float.parseFloat(datamodel.mathematics);
                    }
                    if(!datamodel.punjabi.equals(""))
                    {
                        markpunjabi.setText(datamodel.punjabi);

                        mark_sub3 = Float.parseFloat(datamodel.punjabi);
                    }
                    if(!datamodel.hindi.equals(""))
                    {
                        markhindi.setText(datamodel.hindi);

                        mark_sub4 = Float.parseFloat(datamodel.hindi);
                    }
                    if(!datamodel.history.equals(""))
                    {
                        markhistory.setText(datamodel.history);

                        mark_sub5 = Float.parseFloat(datamodel.history);
                    }
                    if(!datamodel.geography.equals(""))
                    {
                        markgeo.setText(datamodel.geography);

                        mark_sub6 = Float.parseFloat(datamodel.geography);
                    }
                    if(!datamodel.computer.equals(""))
                    {
                        markcomp.setText(datamodel.computer);

                        mark_sub7 = Float.parseFloat(datamodel.computer);
                    }
                    if(!datamodel.physics.equals(""))
                    {
                        markphysics.setText(datamodel.physics);

                        mark_sub8 = Float.parseFloat(datamodel.physics);
                    }
                    if(!datamodel.chemistry.equals(""))
                    {
                        markchem.setText(datamodel.chemistry);

                        mark_sub9 = Float.parseFloat(datamodel.chemistry);
                    }
                    if(!datamodel.biology.equals(""))
                    {
                        markbio.setText(datamodel.biology);

                        mark_sub10 = Float.parseFloat(datamodel.biology);
                    }



                }


                make_resultbar_chart();

                calculate_percentage();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void calculate_percentage()
    {

        float TOTAL = 0;

        float marks_obtained = 0;


        if(mark_sub1 != -1)
        {
            TOTAL += 100;

            marks_obtained += mark_sub1;
        }

        if(mark_sub2 != -1)
        {
            TOTAL += 100;

            marks_obtained += mark_sub2;
        }

        if(mark_sub3 != -1)
        {
            TOTAL += 100;

            marks_obtained += mark_sub3;
        }

        if(mark_sub4 != -1)
        {
            TOTAL += 100;

            marks_obtained += mark_sub4;
        }

        if(mark_sub5 != -1)
        {
            TOTAL += 100;

            marks_obtained += mark_sub5;
        }

        if(mark_sub6 != -1)
        {
            TOTAL += 100;

            marks_obtained += mark_sub6;
        }

        if(mark_sub7 != -1)
        {
            TOTAL += 100;

            marks_obtained += mark_sub7;
        }

        if(mark_sub8 != -1)
        {
            TOTAL += 100;

            marks_obtained += mark_sub8;
        }

        if(mark_sub9 != -1)
        {
            TOTAL += 100;

            marks_obtained += mark_sub9;
        }

        if(mark_sub10 != -1)
        {
            TOTAL += 100;

            marks_obtained += mark_sub10;
        }

        float percentage = (marks_obtained/ TOTAL) * 100;

        percentage_text.setText(String.valueOf(percentage));

        if(percentage > 80) {

            result_image.setImageDrawable(getResources().getDrawable(R.drawable.blessed));
            result_remark_text.setText("Excellent");
        }

         else if(percentage > 70) {
            result_remark_text.setText("Good");
            result_image.setImageDrawable(getResources().getDrawable(R.drawable.thumbsup2));
        }

         else if(percentage > 60) {
            result_remark_text.setText("Average");
            result_image.setImageDrawable(getResources().getDrawable(R.drawable.average));
        }

        else if(percentage < 60) {
            result_remark_text.setText("Poor");
            result_image.setImageDrawable(getResources().getDrawable(R.drawable.tensed));
        }


    }

}
