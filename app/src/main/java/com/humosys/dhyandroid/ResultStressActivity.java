package com.humosys.dhyandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultStressActivity extends AppCompatActivity {

    TextView mGrade, mFinalScore, mAdvice;
    Button mMeditate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_stress);

        mGrade = (TextView)findViewById(R.id.grade);
        mFinalScore = (TextView)findViewById(R.id.outOf);
        mAdvice = (TextView)findViewById(R.id.advice);
        mMeditate = (Button)findViewById(R.id.button10);

        Bundle bundle = getIntent().getExtras();
        int score = bundle.getInt("finalScore");
        int abc = (100 - score);

        if(score > 80) {
            mGrade.setText("Try Hard");
            mFinalScore.setText("Stress is at it's HIGHEST");
            mAdvice.setText("It's not the load that breaks you down, It's the way you carry it. You need to meditate for 5 minutes");
        }
        else if (score >= 50 && score <= 80){
            mFinalScore.setText("You are " + abc + "% stress free");
            mGrade.setText("Good Effort and Stress Less");
            mAdvice.setText("Never Stress over what you can't Control. Go and Meditate for 2 minutes");
        }
        else if(score >= 25 && score <= 50){
            mFinalScore.setText("You are " + abc + "% stress free");
            mGrade.setText("Good Going");
            mAdvice.setText("Don't Stress, Do your very Best, Appreciate each Step, Forget the Rest. Go and Meditate for 1 minute");
        }
        else{
            mFinalScore.setText("You are in Stress Free Zone");
            mGrade.setText("Congratulations");
            mAdvice.setText("Stress doesn't really go with your Outfit. You can meditate to keep the stability constant");
        }


        mMeditate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultStressActivity.this, MeditateNext.class));
                ResultStressActivity.this.finish();
            }
        });
    }
}
