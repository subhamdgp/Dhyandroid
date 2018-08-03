package com.humosys.dhyandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ReflexOutput extends AppCompatActivity {

     int fastestTime,slowestTime,points,level,state4=0;
    TextView last_fastest,last_slowest,last_count;


    public SharedPreferences prefs,prefs1,prefs2;
    public SharedPreferences.Editor editor,editor1,editor2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflex_output);

                Intent intent = getIntent();

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        fastestTime = intent.getExtras().getInt("FastestTime");
        slowestTime = intent.getExtras().getInt("SlowestTime");
        points = intent.getExtras().getInt("Points");
        level = intent.getExtras().getInt("level");
        state4=intent.getExtras().getInt("state");

        if(state4==1)
        {
            prefs1=getSharedPreferences("beforeMeditation", Context.MODE_PRIVATE);
            editor1=prefs1.edit();
            editor1.putFloat("reflexTime",fastestTime);
            editor1.commit();
        }

        else if(state4==2)
        {
            prefs2=getSharedPreferences("afterMeditation", Context.MODE_PRIVATE);
            editor2=prefs2.edit();
            editor2.putFloat("reflexTime",fastestTime);
            editor2.commit();
        }

        TextView fastest = (TextView)findViewById(R.id.fastesttimevalue);
        TextView slowest = (TextView)findViewById(R.id.slowesttimevalue);
        TextView counts = (TextView)findViewById(R.id.pointsvalue) ;

        fastest.setText(fastestTime+ " milisecs");
        slowest.setText(slowestTime+" milisecs");
        counts.setText(points+ " pts");

        if(points==0){
            fastest.setText("N/A");
            slowest.setText("N/A");
        }
//        if(slowestTime==0){
//            slowest.setText("N/A");
//        }
//        if(points==1){
//            fastest.setText(slowestTime + " milisecs");
//        }
        //saving current data
        if(level==1) {
            setPastValues("SA_fastest1","SA_slowest1","SA_counts1");
        }
        if(level==2) {
            setPastValues("SA_fastest2","SA_slowest2","SA_counts2");
        }if(level==3) {
            setPastValues("SA_fastest3","SA_slowest3","SA_counts3");
        }if(level==4) {
            setPastValues("SA_fastest4","SA_slowest4","SA_counts4");
        }
    }

    public void setPastValues(String s1, String s2, String s3){
        int SA_fastest = prefs.getInt(s1,0);
        int SA_slowest = prefs.getInt(s2, 0);
        int SA_counts = prefs.getInt(s3, 0);
        printLastValues(SA_fastest,SA_slowest,SA_counts);

        editor = prefs.edit();
        editor.putInt(s1, fastestTime);
        editor.putInt(s2, slowestTime);
        editor.putInt(s3, points);
        editor.commit();

    }

    public void printLastValues(int n1, int n2, int n3){
        last_fastest = (TextView)findViewById(R.id.lastfastestvalue);
        last_slowest = (TextView)findViewById(R.id.lastslowestvalue);
        last_count = (TextView)findViewById(R.id.lastpointvalue);

        last_fastest.setText("Previous Fastest = " + n1);
        last_slowest.setText("Previous Slowest = " + n2);
        last_count.setText("Previous Counts = " + n3);

        if(n3==0){
            last_fastest.setText("Previous Fastest = N/A");
            last_slowest.setText("Previous Slowest = N/A");
        }
//        if(n1==1000){
//            last_fastest.setText("N/A");
//        }
//        if(n2==0){
//            last_slowest.setText("N/A");
//        }
//        if(n3==1){
//            last_fastest.setText(slowestTime + " milisecs");
//        }

    }
}
