package com.humosys.dhyandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class ResultLogic extends AppCompatActivity {
    Button backLogic;
    TextView scoreView,timeView,rightView,wrongView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_logic);

        scoreView = (TextView)findViewById(R.id.scoreLogic);
        timeView =(TextView)findViewById(R.id.timeTakenLogic);
        rightView =(TextView)findViewById(R.id.rightAnswersLogic);
        wrongView=(TextView)findViewById(R.id.wrongAnswersLogic);
        Intent data = getIntent();

        Long timetaken =data.getExtras().getLong("time");
        timetaken=timetaken/1000;

        int score = data.getExtras().getInt("Score");
        int wrong = data.getExtras().getInt("Wrong");
        int right = data.getExtras().getInt("Right");
        scoreView.setText(""+score+"/100");
        timeView.setText("Time Taken = "+timetaken+" sec" );
        rightView.setText("Right Answers = "+right);
        wrongView.setText("Wrong Answers = "+wrong);
    }

}
