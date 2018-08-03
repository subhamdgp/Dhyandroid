package com.humosys.dhyandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    private TextView mQuestion;
    private RadioButton rb1, rb2, rb3, rb4, rb5;
    Button next;
    int state1;
    private int mScore = 0;
    private int mQuestionsNumber = 0;
    int eScore=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        Intent getState2 = getIntent();
        state1 = getState2.getExtras().getInt("state");
        mQuestion = (TextView) findViewById(R.id.question);

    rb1 = (RadioButton)findViewById(R.id.radioButton1);
    rb2 = (RadioButton) findViewById(R.id.radioButton2);
    rb3 = (RadioButton) findViewById(R.id.radioButton3);
    rb4 = (RadioButton) findViewById(R.id.radioButton4);
    rb5 = (RadioButton) findViewById(R.id.radioButton5);

    next = (Button)findViewById(R.id.button1);

        next.setEnabled(false);
        next.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mQuestionsNumber == Questions.questions.length){
                Intent i = new Intent(QuizActivity.this, ResultActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("finalScore",mScore);
                bundle.putInt("state",state1);
                i.putExtras(bundle);
                   /* Intent ch = new Intent(QuizActivity.this,ChartActivity.class);
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("finalScore",mScore);
                    ch.putExtras(bundle2); */
//                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                SharedPreferences.Editor editor = sharedPref.edit();
//                editor.putInt("score1", mScore);
//                editor.commit();
                QuizActivity.this.finish();
                startActivity(i);
            }
            else{
                mQuestion.setText(Questions.questions[mQuestionsNumber]);
                mQuestionsNumber++;
                mScore = mScore + eScore;
                next.setEnabled(false);
                RadioGroup rg = (RadioGroup) findViewById(R.id.radiogroup2);
                rg.clearCheck();

            }
        }
    });

}

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        next.setEnabled(true);
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radioButton1:
                if (checked) {
                    eScore= 2;
                    break;
                }
            case R.id.radioButton2:
                if (checked) {
                    eScore =  4;
                    break;
                }
            case R.id.radioButton3:
                if (checked) {
                    eScore= 6;
                    break;
                }
            case R.id.radioButton4:
                if (checked) {
                    eScore= 8;
                    break;
                }
            case R.id.radioButton5:
                if (checked) {
                    eScore=10;
                    break;
                }
        }
    }
}
