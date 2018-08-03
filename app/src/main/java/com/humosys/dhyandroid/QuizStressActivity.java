package com.humosys.dhyandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuizStressActivity extends AppCompatActivity {

    private TextView mQuest;
    private int mScore = 0;
    int scoreStress = 0,state1=0;
    RadioGroup rg1,rg2;
   // private RadioButton sr1,sr2,sr3,sr4,sr5,sr6,sr7,sr8,sr9,sr10;
    Button next;
    Context c = this;

    private int mQuestionsNumber = 0;
    public SharedPreferences prefs,prefs1;
    public SharedPreferences.Editor editor,editor1;

    void store()
    {
        if(state1==1)
        {
            prefs=getSharedPreferences("beforeMeditation",Context.MODE_PRIVATE);
            editor=prefs.edit();
            editor.putInt("stressScore",mScore);
            editor.commit();
        }
        else if(state1==2)
        {
            prefs1=getSharedPreferences("afterMeditation",Context.MODE_PRIVATE);
            editor1=prefs1.edit();
            editor1.putInt("stressScore",mScore);
            editor1.commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_stress);


        rg1 = (RadioGroup) findViewById(R.id.stressRadioGroup1);
        rg2 = (RadioGroup)findViewById(R.id.stressRadioGroup2);

        Intent getStateStress = getIntent();
        state1= getStateStress.getExtras().getInt("state");

        mQuest = (TextView) findViewById(R.id.quest);
        next = (Button) findViewById(R.id.button2);
        next.setEnabled(false);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuestionsNumber == QuestionStress.questions.length) {
                    Intent i = new Intent(QuizStressActivity.this, ResultStressActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("finalScore", mScore);
                    i.putExtras(bundle);
                    store();
                    QuizStressActivity.this.finish();
                    startActivity(i);
                }
                else {
                    next.setEnabled(false);
                    mQuest.setText(QuestionStress.questions[mQuestionsNumber]);
                    mQuestionsNumber++;
                    mScore=mScore+scoreStress;
                    Log.i("Score", "onClick: Score "+mScore);
                    rg1.clearCheck();
                    rg2.clearCheck();
                        Log.i("Score", "onClick: " + mScore);
                    Log.i("ok", "onClick: cleared");

                }
            }
        });
    }

    public void onRadioButtonStress(View view) {
        // Is the button now checked?
        next.setEnabled(true);
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radioButtonStress1:
                if (checked) {
                    scoreStress=1;
                    rg2.clearCheck();
                    break;
                }
            case R.id.radioButtonStress2:
                if (checked) {
                    scoreStress=2;
                    rg2.clearCheck();
                    break;
                }
            case R.id.radioButtonStress3:
                if (checked) {
                    scoreStress= 3;
                    rg2.clearCheck();
                    break;
                }
            case R.id.radioButtonStress4:
                if (checked) {
                    scoreStress= 4;
                    rg2.clearCheck();
                    break;
                }
            case R.id.radioButtonStress5:
                if (checked) {
                    scoreStress= 5;
                    rg2.clearCheck();
                    break;
                }
             case R.id.radioButtonStress6:
                if (checked) {
                    scoreStress= 6;
                    rg1.clearCheck();
                    break;
                }
            case R.id.radioButtonStress7:
                if (checked) {
                    scoreStress= 7;
                    rg1.clearCheck();
                    break;
                }
            case R.id.radioButtonStress8:
                if (checked) {
                    scoreStress= 8;
                    rg1.clearCheck();
                    break;
                }
            case R.id.radioButtonStress9:
                if (checked) {
                    scoreStress= 9;
                    rg1.clearCheck();
                    break;
                }
            case R.id.radioButtonStress10:
                if (checked) {
                    scoreStress= 10;
                    rg1.clearCheck();
                    break;
                }
        }
    }
}