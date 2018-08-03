package com.humosys.dhyandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class LogicGame extends AppCompatActivity {
    ArrayList<String> eq = new ArrayList<String>(Arrays.asList("30 + 45 = 75","61 + 24 = 85","20 + 18 = 38","75 + 8 = 83","32 + 28 = 60","70 - 32 = 38","36 - 12 = 24","77 - 33 = 44","88 -10 = 78","60 - 9 =51","7 * 13 = 91","13 * 13 = 169","14 * 11 = 154","5 * 25 = 125","17 * 3 = 51","35 / 5 = 7","105 / 5 = 21","132 / 6 = 22","506 / 23 = 22","45 / 9 = 5","37 + 13 = 40","25 + 35 = 70","53 + 12 = 66","75 + 18 = 94","38 + 26 = 74","70 - 35 = 25","33 - 10 = 13","77 - 33 = 55","88 - 20 = 78","60 - 19 = 51","13 * 8 = 102","14 * 8 = 102","15 * 5 = 125","25 * 9 =220","17 * 6 = 112","135 / 5 = 29","49 / 7 = 11","120 / 6 = 12","506 / 22 = 22","105 / 7 = 21"));
    Random rand;
    int randomIndex;
    int Lcount=0,score = 0,Rcount=0,Wcount=0;
    Button yes,no;
    TextView equation;
    int state2=0;
    static long starttime;
    long time=0;
    SharedPreferences prefs,prefs1;
    SharedPreferences.Editor editor,editor1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logic_game);

        Intent i =getIntent();
        state2=i.getExtras().getInt("state");
        equation = (TextView)findViewById(R.id.equationLogic);
        yes =(Button)findViewById(R.id.yesButton);
        no = (Button)findViewById(R.id.noButton);
        rand = new Random();
        randomIndex = rand.nextInt(eq.size());
        String randomElement = eq.get(randomIndex);
        Lcount =1;
        equation.setText(Lcount+ ". Is "+ randomElement +" ?");
        starttime=System.currentTimeMillis();
        final Intent result = new Intent(LogicGame.this,ResultLogic.class);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(randomIndex<20) {
                    score = score + 10;
                    Rcount++;
                }
                else {
                    score = score - 5;
                    Wcount++;
                }
                Lcount++;
                if(Lcount>10)
                {
                    time=System.currentTimeMillis()-starttime;
                    result.putExtra("Score",score);
                    result.putExtra("time",time);
                    result.putExtra("Right",Rcount);
                    result.putExtra("Wrong",Wcount);
                    storeResults(score);
                    LogicGame.this.finish();
                    startActivity(result);
                    }
                Random rand1= new Random();
                randomIndex =rand1.nextInt(eq.size());
                String randomElement = eq.get(randomIndex);
                equation.setText(Lcount+ ". Is "+ randomElement +" ?");

            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(randomIndex<20) {
                    score = score - 5;
                    Wcount++;
                }
                else {
                    score = score + 10;
                    Rcount++;
                }
                Lcount++;
                if(Lcount>10)
                {
                    time=System.currentTimeMillis()-starttime;
                    result.putExtra("Score",score);
                    result.putExtra("time",time);
                    result.putExtra("Right",Rcount);
                    result.putExtra("Wrong",Wcount);
                    storeResults(score);
                    LogicGame.this.finish();
                    startActivity(result);
                    }
                Random rand1= new Random();
                randomIndex =rand1.nextInt(eq.size());
                String randomElement = eq.get(randomIndex);
                equation.setText(Lcount+ ". Is "+ randomElement +" ?");
            }
        });

    }
    void storeResults(int score)
    {
        if(state2==1)
        {
            prefs=getSharedPreferences("beforeMeditation", Context.MODE_PRIVATE);
            editor=prefs.edit();
            editor.putInt("logicScore",score);
            editor.commit();
        }
        else if(state2==2)
        {
            prefs1=getSharedPreferences("afterMeditation", Context.MODE_PRIVATE);
            editor1=prefs1.edit();
            editor1.putInt("logicScore",score);
            editor1.commit();
        }
    }
}
