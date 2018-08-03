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


public class Attention extends AppCompatActivity {

    GridView gridview;
    Chronometer chronometer;
    static int attempts=0;  // 'attempts' is total number of attmpts to get right answer
    long time=0;
    static long timekeeper;
    public int b;
    int num1;
    int state1=0;

    public static int width;
    public static int a;  //to keep repeating game for a times,as this class recreated everytime, asses static num2 from adpter class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);
        chronometer = (Chronometer) findViewById(R.id.chronometer3);
        chronometer.setBase(SystemClock.elapsedRealtime() + time);
        chronometer.start();

        Intent getstate1 = getIntent();
        state1 =getstate1.getExtras().getInt("state");

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x - 90;

        CalculationOutput.Attention_Image_Adapter aia = new CalculationOutput.Attention_Image_Adapter();
        num1 = aia.getNum1();

        gridview = (GridView) findViewById(R.id.grid_attention);
        gridview.setAdapter(new CalculationOutput.Attention_Image_Adapter(this));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView imageView = (ImageView) view;
                int cntr = 0;
                b = a%5;   //for repeating 5 times for different sets
                if (num1 == position) {
                    do {
                        imageView.setClickable(true);
                        cntr++;
                        attempts++;
                        imageView.setImageResource(R.drawable.correct);

                        if (b < 4) {
                            time = SystemClock.elapsedRealtime() - chronometer.getBase();
                            chronometer.stop();
                            timekeeper = timekeeper+time;
                            a++;
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);}
                        if (b == 4) {
                            time = SystemClock.elapsedRealtime() - chronometer.getBase();
                            chronometer.stop();
                            timekeeper = timekeeper+time;
                            Intent intent = new Intent(getApplicationContext(), AttentionOutput.class);
                            intent.putExtra("time", timekeeper);
                            intent.putExtra("attempts", attempts);
                            intent.putExtra("state",state1);
                            a=0;
                            timekeeper=0;
                            attempts=00;
                            startActivity(intent);
                            finish();}
                    } while (cntr < 1);

                } else {
                    do {
                        imageView.setClickable(true);
                        cntr++;
                        attempts++;
                        imageView.setImageResource(R.drawable.cross);
                    } while (cntr < 1);
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        a=0;
                        timekeeper=0;
                        attempts=0;
                        Attention.super.onBackPressed();
                    }
                }).create().show();
    }
}
