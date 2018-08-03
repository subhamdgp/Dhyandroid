package com.humosys.dhyandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView mGrade, mFinalScore, mAdvice;
    Button mMeditate;
    int state2=0;

    public SharedPreferences prefs,prefs1;
    public SharedPreferences.Editor editor,editor1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mGrade = (TextView)findViewById(R.id.grade);
        mFinalScore = (TextView)findViewById(R.id.outOf);
        mAdvice = (TextView)findViewById(R.id.advice);
        mMeditate = (Button)findViewById(R.id.button10);


        Bundle bundle = getIntent().getExtras();
        int score = bundle.getInt("finalScore");
        state2 =bundle.getInt("state");

        if(state2==1)
        {
            prefs=getSharedPreferences("beforeMeditation", Context.MODE_PRIVATE);
            editor= prefs.edit();
            editor.putInt("emotionScore",score);
            editor.commit();
        }
        else if(state2==2)
        {
            prefs1=getSharedPreferences("afterMeditation",Context.MODE_PRIVATE);
            editor1=prefs1.edit();
            editor1.putInt("emotionScore1",score);
            editor1.commit();
        }

        if(score < 50) {
            mGrade.setText("Try Hard");
            mFinalScore.setText("You are Emotionally Unstable");
            mAdvice.setText("You need to meditate for 5 minutes");
        }
        else if (score >= 50 && score < 70){
            mFinalScore.setText("You are " + score + "% emotionally stable");
            mGrade.setText("Good Effort");
            mAdvice.setText("Go and Meditate for 2 minutes");
        }
        else if(score >= 70 && score <= 80){
            mFinalScore.setText("You are " + score + "% emotionally stable");
            mGrade.setText("Good Work");
            mAdvice.setText("Go and Meditate for 1 minute");
        }
        else{
            mFinalScore.setText("You are " + score + "% emotionally stable");
            mGrade.setText("OutStanding");
            mAdvice.setText("You can meditate to keep the stability constant");
        }

        mMeditate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this, MeditateNext.class));
                ResultActivity.this.finish();
            }
        });
    }
}
