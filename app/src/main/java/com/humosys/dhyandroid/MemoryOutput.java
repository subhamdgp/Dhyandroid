package com.humosys.dhyandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MemoryOutput extends AppCompatActivity {

    int state4=0;
    int finalscore,totalattempts,wrong;  //present data
    long time;
    EditText et;
    double time_entered,time_actual;
    TextView perceived_time,actual_time;
    TextView last_score,last_attempts,last_perceived_time,last_actual_time;

    public SharedPreferences prefs,prefs1,prefs2,statePref;
    public SharedPreferences.Editor editor,editor1,editor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_output);

        statePref=getSharedPreferences("State",Context.MODE_PRIVATE);
        state4= statePref.getInt("state",0);
        Log.i("state", "onCreate: State "+ state4);


        Intent intent = getIntent();
        finalscore = intent.getExtras().getInt("finalscore");
        totalattempts = intent.getExtras().getInt("totalattempts");
        time = intent.getExtras().getLong("totaltime");

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int WM_score = prefs.getInt("WM_score", 0);
        int WM_Attempts = prefs.getInt("WM_Attempts", 0);


        time_actual = time / 1000.0 + 21.0;
        time_actual = Math.round(time_actual * 10.0) / 10.0;  //up to 1 decimal digit

        wrong = totalattempts - 20;

        TextView tv1 = (TextView) findViewById(R.id.finalscorevalue);
        TextView tv2 = (TextView) findViewById(R.id.total_attempts_value);
        last_score = (TextView) findViewById(R.id.lastscorevalue);
        last_attempts = (TextView) findViewById(R.id.last_attempts_value);

        tv1.setText(new DecimalFormat("###.##").format(finalscore) + "/20"); //no need to put decimal format ...it's just to remind how to do it
        tv2.setText(totalattempts + "");

        last_score.setText("(Previous Score = " + WM_score + "/20)");
        last_attempts.setText("(Previous Attempts = " + WM_Attempts + ")");

        if (state4 == 2) {
            prefs1 = getSharedPreferences("afterMeditation", Context.MODE_PRIVATE);
            editor1 = prefs1.edit();
            editor1.putInt("memoryScore", finalscore);
            editor1.commit();
        }
        else if (state4 == 1) {
            prefs2 = getSharedPreferences("beforeMeditation", Context.MODE_PRIVATE);
            editor2 = prefs2.edit();
            editor2.putInt("memoryScore", finalscore);
            editor2.commit();

        }
    }
    public void timeResult(View view) {
        et = (EditText) findViewById(R.id.enter_time);
        String st = et.getText().toString();

        perceived_time = (TextView) findViewById(R.id.entered_time);
        actual_time = (TextView) findViewById(R.id.timetakenid);
        last_perceived_time = (TextView) findViewById(R.id.last_entered_time);
        last_actual_time = (TextView) findViewById(R.id.last_timetakenid);

        if (st.matches("")) {
            Toast.makeText(this, "You didn't enter any time value", Toast.LENGTH_SHORT).show();
            return;
        } else {
            time_entered = Double.parseDouble(st);

            //access previous data (for time)
            float WM_actual_time = prefs.getFloat("WM_act_time",0);
            float WM_perceived_time = prefs.getFloat("WM_input_time",0);

            perceived_time.setText("Perceived Time = " + time_entered);
            actual_time.setText("Actual Time = " + time_actual);
            last_perceived_time.setText("(Previously Perceived Time = "+ WM_perceived_time+")");
            last_actual_time.setText("(Previous Actual Time = "+ WM_actual_time+")");
            et.setEnabled(false);
        }

        //saving current data
            editor = prefs.edit();
            editor.putInt("WM_score", finalscore);
            editor.putInt("WM_Attempts", totalattempts);
            editor.putFloat("WM_act_time", (float) time_actual);
            editor.putFloat("WM_input_time", (float) time_entered);
            editor.commit();


        }
    }

