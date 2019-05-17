package com.example.studentanalytics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class attendancelist extends AppCompatActivity {


    TextView textView1;
    TextView textView2;
    TextView textView3;

    float jan_attendence = 0;
    float feb_attendence = 0;
    float march_attendence = 0;
    float april_attendence = 0;
    float may_attendence = 0;
    float june_attendence = 0;
    float july_attendence = 0;
    float august_attendence = 0;
    float sept_attendence = 0;
    float oct_attendence = 0;
    float nov_attendence = 0;
    float dec_attendence = 0;

    float ABSENT_DAYS = 0;

    float LEAVE_DAYS = 0;

    TextView present;
    TextView absent;
    TextView leave;


    TextView percent_1;
    TextView percent_2;
    TextView percent_3;
    TextView percent_4;
    TextView percent_5;
    TextView percent_6;
    TextView percent_7;
    TextView percent_8;
    TextView percent_9;
    TextView percent_10;
    TextView percent_11;
    TextView percent_12;

    BarChart chart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendancelist);


        textView1 = findViewById(R.id.student_txt_view);
        textView2 = findViewById(R.id.class_txt_view);
        textView3 = findViewById(R.id.id_txt);


        percent_1 = findViewById(R.id.percent_1);
        percent_2 = findViewById(R.id.percent_2);
        percent_3 = findViewById(R.id.percent_3);
        percent_4 = findViewById(R.id.percent_4);
        percent_5 = findViewById(R.id.percent_5);
        percent_6 = findViewById(R.id.percent_6);
        percent_7 = findViewById(R.id.percent_7);
        percent_8 = findViewById(R.id.percent_8);
        percent_9 = findViewById(R.id.percent_9);
        percent_10 = findViewById(R.id.percent_10);
        percent_11 = findViewById(R.id.percent_11);
        percent_12 = findViewById(R.id.percent_12);

        present = findViewById(R.id.present_opposite_textview);
        absent = findViewById(R.id.absent_text_view);
        leave  = findViewById(R.id.leave_text_view);

        chart = findViewById(R.id.attendance_bar_chart);

        fetch_from_firebase();

        get_attendance();



    }

    private void fetch_from_firebase() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        SharedPreferences sharedPreferences = getSharedPreferences("student_info", MODE_PRIVATE);

        String id = sharedPreferences.getString("id" , "");


        DatabaseReference reference = database.getReference("students").child(id);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                addstudentmodel dataModel = dataSnapshot.getValue(addstudentmodel.class);
                textView1.setText(dataModel.name);
                textView2.setText(dataModel.class1);
                textView3.setText(dataModel.id);



           }
        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });

}

private void get_attendance()
{

    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    SharedPreferences sharedPreferences = getSharedPreferences("student_info", MODE_PRIVATE);

    String id = sharedPreferences.getString("id" , "");

    String _class = sharedPreferences.getString("class" , "");


    DatabaseReference reference = database.getReference("attendence").child(_class).child(id).child("2019");

    reference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

           for(DataSnapshot snapshot : dataSnapshot.getChildren())
           {
               for(DataSnapshot snapshot1 : snapshot.getChildren())
               {
                   attendence_data_model data_model = snapshot1.getValue(attendence_data_model.class);

                   if(data_model.status.equals("Present"))
                   {
                       System.out.println("********************** "+snapshot.getKey());

                       if( snapshot.getKey().equals("01") )
                       {
                           jan_attendence ++;
                       }

                       if( snapshot.getKey().equals("02") )
                       {
                           feb_attendence ++;
                       }

                       if( snapshot.getKey().equals("03") )
                       {
                           march_attendence ++;
                       }


                       if( snapshot.getKey() .equals("04") )
                       {
                           april_attendence ++;
                       }

                       if( snapshot.getKey() .equals("05") )
                       {
                           may_attendence ++;
                       }
                       if( snapshot.getKey() .equals("06") )
                   {
                       june_attendence ++;
                   }

                       if( snapshot.getKey() .equals("07") )
                       {
                           july_attendence ++;
                       }
                       if( snapshot.getKey() .equals("08") )
                       {
                           august_attendence ++;
                       }

                       if( snapshot.getKey() .equals("09") )
                       {
                           sept_attendence ++;
                       }

                       if( snapshot.getKey() .equals("10") )
                   {
                       oct_attendence ++;
                   }

                   if( snapshot.getKey() .equals("11") )
                   {
                       nov_attendence ++;
                   }

                       if( snapshot.getKey() .equals("12") )
                       {
                           dec_attendence ++;
                       }


                   }

                   if(data_model.status.equals("Absent"))
                   {
                       ABSENT_DAYS ++;
                   }

                   if(data_model.status.equals("Leave"))
                   {
                       LEAVE_DAYS ++;
                   }


               }
           }

            make_bar_chart();

           percent_1.setText(String.valueOf(jan_attendence));
           percent_2.setText(String.valueOf(feb_attendence));
           percent_3.setText(String.valueOf(march_attendence));
           percent_4.setText(String.valueOf(april_attendence));
           percent_5.setText(String.valueOf(may_attendence));
           percent_6.setText(String.valueOf(june_attendence));
           percent_7.setText(String.valueOf(july_attendence));
           percent_8.setText(String.valueOf(august_attendence));
           percent_9.setText(String.valueOf(sept_attendence));
           percent_10.setText(String.valueOf(oct_attendence));
           percent_11.setText(String.valueOf(nov_attendence));
           percent_12.setText(String.valueOf(dec_attendence));


           float TOTAL_PRESENT = jan_attendence + feb_attendence + march_attendence+ april_attendence+ may_attendence+ june_attendence+ july_attendence+ august_attendence+sept_attendence+ oct_attendence+nov_attendence+dec_attendence ;

           present.setText( String.valueOf(TOTAL_PRESENT) );
           absent.setText(String.valueOf(ABSENT_DAYS));
           leave.setText(String.valueOf(LEAVE_DAYS));




        }
        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });

}

private void make_bar_chart()
{
    ArrayList<BarEntry> BarEntry = new ArrayList<>();



        BarEntry.add(new BarEntry(jan_attendence, 0));
        BarEntry.add(new BarEntry(feb_attendence, 1));
        BarEntry.add(new BarEntry(march_attendence, 2));
        BarEntry.add(new BarEntry(april_attendence, 3));
        BarEntry.add(new BarEntry(may_attendence, 4));
        BarEntry.add(new BarEntry(june_attendence, 5));
        BarEntry.add(new BarEntry(july_attendence, 6));
        BarEntry.add(new BarEntry(august_attendence, 7));
        BarEntry.add(new BarEntry(sept_attendence, 8));
        BarEntry.add(new BarEntry(oct_attendence, 9));
        BarEntry.add(new BarEntry(nov_attendence, 10));
        BarEntry.add(new BarEntry(dec_attendence, 11));

        BarDataSet dataSet = new BarDataSet(BarEntry, "Projects");




    ArrayList<String> labels = new ArrayList<>();

    labels.add("J");
    labels.add("F");
    labels.add("M");
    labels.add("A");
    labels.add("M");
    labels.add("J");
    labels.add("J");
    labels.add("A");
    labels.add("S");
    labels.add("O");
    labels.add("N");
    labels.add("D");



    BarData data = new BarData( labels , dataSet);

    chart.setData(data);
    dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
}

}
