package com.humosys.dhyandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MoodResult extends AppCompatActivity {

    TextView mGrade, mFinalScore, mAdvice;
    Button mMeditate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_result);

        mGrade = (TextView)findViewById(R.id.gradeMood);
        mFinalScore = (TextView)findViewById(R.id.outOfMood);
        mAdvice = (TextView)findViewById(R.id.adviceMood);
        mMeditate=(Button)findViewById(R.id.button11);

        Bundle bundle = getIntent().getExtras();
        int score = bundle.getInt("finalScore");

        if(score < 50) {
            mGrade.setText("Try Hard");
            mFinalScore.setText("You are in a Really Bad Mood. Cheer Up!");
            mAdvice.setText("You need to meditate for 7 minutes");
        }
        else if (score >= 50 && score < 70){
            mFinalScore.setText("You are in " + score + "% Good Mood");
            mGrade.setText("Good Effort");
            mAdvice.setText("Go and Meditate for 5 minutes");
        }
        else if(score >= 70 && score <= 80){
            mFinalScore.setText("You are in " + score + "% Good Mood");
            mGrade.setText("Good Work");
            mAdvice.setText("Go and Meditate for 2 minute");
        }
        else{
            mFinalScore.setText("You are in " + score + "% Good Mood");
            mGrade.setText("OutStanding");
            mAdvice.setText("You can meditate to keep the stability constant");
        }

        mMeditate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MoodResult.this, MeditateNext.class));
                MoodResult.this.finish();
            }
        });
    }
}
