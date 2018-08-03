package com.humosys.dhyandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AttentionOutput extends AppCompatActivity {

    long time;
    int attempts;
    float attempt_avg;
    int state1=0;
    EditText et;
    double time_entered,time_actual;  //for getting entered double values then change into int time_entered
    TextView perceived_time, actual_time,last_perceived_time,last_actual_time;

    public SharedPreferences prefs,prefs1,prefs2;
    public SharedPreferences.Editor editor,editor1,editor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention_output);

        Intent intent = getIntent();
        time = intent.getExtras().getLong("time");
        attempts = intent.getExtras().getInt("attempts");
        state1=intent.getExtras().getInt("state");
        //previous data access
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        float ATT_Attempts_per_set = prefs.getFloat("ATT_Attempts",0);

        time_actual = time/1000.0;
        time_actual = Math.round(time_actual*10.0)/10.0;  //up to 1 decimal digit

        attempt_avg = (float)(attempts)/5;  //attempts per set
        TextView tv1 = (TextView)findViewById(R.id.attention_attempts_value);
        TextView last_attemts = (TextView)findViewById(R.id.last_attempts_value);
        tv1.setText(attempt_avg + "");                                              //current game data
        last_attemts.setText("(Previous Attempts/set = " + ATT_Attempts_per_set+")");  //previous game's data

    }

    public void timeResult(View view) {

        et = (EditText)findViewById(R.id.enter_time);
        String st = et.getText().toString();

        perceived_time = (TextView) findViewById(R.id.entered_time);
        actual_time = (TextView) findViewById(R.id.timetakenid);
        last_perceived_time = (TextView) findViewById(R.id.last_entered_time);
        last_actual_time = (TextView) findViewById(R.id.last_timetakenid);


        if (st.matches("")) {
            Toast.makeText(this, "You didn't enter any time value", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            time_entered = Double.parseDouble(st);

            //access previous data (for time)
            float ATT_actual_time = prefs.getFloat("ATT_act_time",0);
            float ATT_perceived_time = prefs.getFloat("ATT_input_time",0);

            perceived_time.setText("Perceived Time = " + time_entered + " secs");
            actual_time.setText("Actual Time = " + time_actual + " secs");
            last_perceived_time.setText("(Previously Perceived Time = "+ ATT_perceived_time+")");
            last_actual_time.setText("(Previous Actual Time = "+ ATT_actual_time+")");
            et.setEnabled(false);

        }

        //saving current data
        editor = prefs.edit();
        editor.putFloat("ATT_Attempts", attempt_avg );
        editor.putFloat("ATT_act_time",(float)time_actual );
        editor.putFloat("ATT_input_time",(float)time_entered );
        editor.commit();

        if(state1==1){
            prefs2=getSharedPreferences("beforeMeditation", Context.MODE_PRIVATE);
            editor2=prefs2.edit();
            editor2.putFloat("scoreAttention",(float)time_actual);
            editor2.commit();
        }
        else if(state1==2){
            prefs1=getSharedPreferences("afterMeditation", Context.MODE_PRIVATE);
            editor1=prefs1.edit();
            editor1.putFloat("scoreAttention",(float)time_actual);
            editor1.commit();
        }
    }

}
