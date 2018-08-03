package com.humosys.dhyandroid;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class EvaluateNextBeforeNext extends AppCompatActivity {
    int state1=0;
    SharedPreferences state;
    SharedPreferences.Editor editState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate_next_before_next);
        final Button memory,emotion,stress,attention,reflex,logic,mood,aggression;
        memory = (Button) findViewById(R.id.button_memory);
        emotion = (Button)findViewById(R.id.button_emotion);
        stress = (Button)findViewById(R.id.button_stress);
        attention = (Button)findViewById(R.id.button_attention);
        reflex = (Button)findViewById(R.id.button_reflex);
        logic = (Button)findViewById(R.id.button_logic);
        mood = (Button)findViewById(R.id.button_mood);
        aggression = (Button)findViewById(R.id.button_aggression);

        Intent stateCondition =getIntent();
        state1= stateCondition.getExtras().getInt("state");
        Log.i("State", "onCreate: State"+state1);




        //On clicking Memory Button
        memory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state =getSharedPreferences("State", Context.MODE_PRIVATE);
                editState=state.edit();
                editState.putInt("state",state1);
                editState.commit();
                Intent openMemory = new Intent(EvaluateNextBeforeNext.this, WorkingMemory.class);
                startActivity(openMemory);

            }
        });

        //On clicking Emotion Button
        emotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openEmotion = new Intent(EvaluateNextBeforeNext.this,QuizActivity.class);
                openEmotion.putExtra("state",state1);
                startActivity(openEmotion);
            }
        });

        //On clicking Stress Button
        stress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openStress = new Intent (EvaluateNextBeforeNext.this,QuizStressActivity.class);
                openStress.putExtra("state",state1);
                startActivity(openStress);
            }
        });

        //On clicking Attention Button
        attention.setOnClickListener(new View.OnClickListener() {
             @Override
            public void onClick(View v) {
                Intent openAttention = new Intent(EvaluateNextBeforeNext.this,Attention.class);
                openAttention.putExtra("state",state1);
                startActivity(openAttention);
            }
        });

        //On clicking Reflex Button
        reflex.setOnClickListener(new View.OnClickListener() {
             @Override
            public void onClick(View v) {
                Intent openReflex = new Intent(EvaluateNextBeforeNext.this,ReflexLevels.class);
                openReflex.putExtra("state",state1);
                startActivity(openReflex);
            }

        });

        //On clicking Logic Button
        logic.setOnClickListener(new View.OnClickListener() {
             @Override
            public void onClick(View v) {
                Intent openLogic = new Intent(EvaluateNextBeforeNext.this,Logic.class);
                openLogic.putExtra("state",state1);
                startActivity(openLogic);
            }
        });

        //On clicking Mood Button
        mood.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent openMood = new Intent(EvaluateNextBeforeNext.this,MoodQuiz.class);
                openMood.putExtra("state",state1);
                startActivity(openMood);
            }
        });

        //On Clicking Aggression Button
        aggression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openAggression = new Intent(EvaluateNextBeforeNext.this,Aggression.class);
                openAggression.putExtra("state",state1);
                startActivity(openAggression);
            }
        });

    }
}