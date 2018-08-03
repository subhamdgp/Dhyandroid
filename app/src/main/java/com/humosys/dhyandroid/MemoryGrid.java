package com.humosys.dhyandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MemoryGrid extends AppCompatActivity {

    GridView gridview;

    int a = 0, b = 0;//a// is used for conditional statement and b for counting number of wrong answers in each groupSet
    int number1,number2,number3,number4;  // 4 different random numbers
    int group;          // groupset is recived from Working_memory class through intent
    int score=0;   // score of individual groupSet and is displayed on screen
    int finalscore,totalattempts;             //final score is the total of all 5 groupSets scores,attempts is total attmpts in all 5 sets
    static int attempts=20 ;          //total right and wrong answers
    static int maxscore = 20;   //total 20 right answers, so max is 20. maxscore is used to keep memory of score after every groupSet completed

    long totaltime = 0;         //sum of the Time-taken to answer all 5 groupsets
    long thistime=0;            //Time taken by individual groupSet
    static long time=0;         //static variable to keep count of previous and curret value of Time

    Chronometer chronometer;


    public static int width;

    public static int groupSet=0;
    public MemoryGrid()  {
        groupSet++;
    }
    public int getGroupSet(){
        return groupSet;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_grid);

        chronometer = (Chronometer)findViewById(R.id.chronometer2);
        chronometer.setBase(SystemClock.elapsedRealtime()+thistime);
        chronometer.start();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x - 90;

        Intent intent = getIntent();
        number1 = intent.getExtras().getInt("num1");
        number2 = intent.getExtras().getInt("num2");
        number3 = intent.getExtras().getInt("num3");
        number4 = intent.getExtras().getInt("num4");
        group = intent.getExtras().getInt("group");
//        music = MediaPlayer.create(this, R.raw.correct);
//        music1 = MediaPlayer.create(this, R.raw.wrong);

        gridview = (GridView) findViewById(R.id.grid_memory);
        gridview.setAdapter(new MemoryGrid_Image_Adapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ImageView imageView = (ImageView) view;
                int cntr = 0;
                if (position == number1 || position == number2 || position == number3 || position == number4) {
                    do {
                        imageView.setClickable(true);
                        cntr++;
                        a++;
                        imageView.setImageResource(R.drawable.correct);
                        score++;
//                        music.seekTo(700);
//                        music.start();
                    } while (cntr < 1);
                } else {
                    do {
                        imageView.setClickable(true);
                        cntr++;
                        b++;
                        imageView.setImageResource(R.drawable.cross);
                        score--;
//                        music1.seekTo(500);
//                        music1.start();
                    } while (cntr < 1);
                }

                TextView textView = (TextView) findViewById(R.id.scorevalue);
                textView.setText(score + "");

                if (a == 4 && group != 5) {
                    thistime = SystemClock.elapsedRealtime() - chronometer.getBase();
                    chronometer.stop();

                    maxscore = maxscore - b;          //maximum - negative points
                    attempts = attempts + b;  //minimum question that must be answred + wrong answers
                    time = time + thistime;
                    Intent intent1 = new Intent(getApplicationContext(), WorkingMemory.class);
                    startActivity(intent1);
                    finish();

                }

                if (a == 4 && group == 5) {
                    thistime = SystemClock.elapsedRealtime() - chronometer.getBase();
                    chronometer.stop();

                    finalscore = maxscore - b;
                    totalattempts = attempts + b;
                    totaltime = time + thistime;
                    Intent intent1 = new Intent(getApplicationContext(), MemoryOutput.class);
                    intent1.putExtra("finalscore", finalscore);
                    intent1.putExtra("totalattempts", totalattempts);
                    intent1.putExtra("totaltime", totaltime);

                    maxscore = 20;    // set it back to 20 as the game has finished for now
                    attempts = 20;
                    time = 0;
                    MemoryGrid.groupSet = 0;
                    startActivity(intent1);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
//        music.release();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        MemoryGrid.super.onBackPressed();
                        maxscore = 20;     //reseting static value of maxscore
                        attempts = 20; //reset static value of attempts
                        time = 0;          //reseting static value of time
                        MemoryGrid.groupSet = 0;
                        finish();
                    }
                }).create().show();
    }
}
