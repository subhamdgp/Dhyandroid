package com.humosys.dhyandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class EvaluateNext extends AppCompatActivity {
    int state =0;
    public Button Before_meditation,After_meditation;

    public void init(){
        Before_meditation = (Button) findViewById(R.id.button_before_meditation);
        After_meditation = (Button) findViewById(R.id.button_after_meditation);

        Before_meditation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                state=1;
                Intent obj1=new Intent(EvaluateNext.this,EvaluateNextBeforeNext.class);
                obj1.putExtra("state",state);
                startActivity(obj1);
            }
        });

        After_meditation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                state=2;
                Intent obj2=new Intent(EvaluateNext.this,EvaluateNextBeforeNext.class);
                obj2.putExtra("state",state);
                startActivity(obj2);
            }
        });
    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate_next);
        init();
    }

}
