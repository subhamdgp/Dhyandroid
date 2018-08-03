package com.humosys.dhyandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MoodQuiz extends AppCompatActivity {

    private TextView mQuestion;
   // private RadioButton rb1, rb2, rb3, rb4, rb5;
    Button next;
    int state1,meditateScore;
    private int mScore = 0;
    private int mQuestionsNumber = 0;
    SharedPreferences prefs1,prefs2;
    SharedPreferences.Editor editor1,editor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_quiz);

        Intent getState = getIntent();
        state1=getState.getExtras().getInt("state");

        mQuestion = (TextView) findViewById(R.id.question);

//        rb1 = (RadioButton)findViewById(R.id.radioButton11);
//        rb2 = (RadioButton) findViewById(R.id.radioButton22);
//        rb3 = (RadioButton) findViewById(R.id.radioButton33);
//        rb4 = (RadioButton) findViewById(R.id.radioButton44);
//        rb5 = (RadioButton) findViewById(R.id.radioButton55);

        next = (Button)findViewById(R.id.button2);
        next.setEnabled(false);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mQuestionsNumber == QuestionsMood.questions.length){
                    Intent i = new Intent(MoodQuiz.this, MoodResult.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("finalScore",mScore);
                    i.putExtras(bundle);

                    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("score1", mScore);
                    editor.commit();
                    storeMoodResults(mScore);
                    MoodQuiz.this.finish();
                    startActivity(i);
                }
                else{
                    next.setEnabled(false);
                    mQuestion.setText(QuestionsMood.questions[mQuestionsNumber]);
                    mQuestionsNumber++;
                    mScore += meditateScore;
                    RadioGroup rg = (RadioGroup) findViewById(R.id.radiogroup3);
                    rg.clearCheck();

                }
            }
        });

    }
    //Storing results in the SharedPreferences to be used in the Compare activity
    public void storeMoodResults(int data){
        if(state1==1)
        {
            prefs1=getSharedPreferences("beforeMeditation", Context.MODE_PRIVATE);
            editor1=prefs1.edit();
            editor1.putInt("moodScore",data);
            editor1.commit();
        }
        else if(state1==2)
        {
            prefs2=getSharedPreferences("afterMeditation", Context.MODE_PRIVATE);
            editor2=prefs2.edit();
            editor2.putInt("moodScore",data);
            editor2.commit();
        }
    }

    //Function that checks for radio button clicks and calculating score
    public void onRadioButtonClicked1(View view) {
        //Next button will be enabled only if some radio button will be checked
        next.setEnabled(true);
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radioButton11:
                if (checked) {
                    meditateScore= 2;
                    break;
                }
            case R.id.radioButton22:
                if (checked) {
                    meditateScore= 4;
                    break;
                }
            case R.id.radioButton33:
                if (checked) {
                    meditateScore= 6;
                    break;
                }
            case R.id.radioButton44:
                if (checked) {
                    meditateScore= 8;
                    break;
                }
            case R.id.radioButton55:
                if (checked) {
                    meditateScore= 10;
                    break;
                }
        }
    }
}
