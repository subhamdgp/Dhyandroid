package com.humosys.dhyandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Reflex extends AppCompatActivity {

    Animation myscaleanim;
    long starttime = 0, nowtime;
    int b, c, r2;    //int b&c are for 'if' loop in imgclick mathod,r2 is a random variable from 0 to 5 to choose random ball.
    int life = 1;
    int level,state2=0;
    int slowest = 0, fastest = 1000, counts = 0, currentScore = 0, lastScore = 0;

    public static int width, height;
    final Integer imageColor[] = {R.drawable.red, R.drawable.black,
            R.drawable.yellow, R.drawable.violet,
            R.drawable.pink, R.drawable.green, R.drawable.blue};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflex);

        Intent intent = getIntent();
        level = intent.getExtras().getInt("level");
        state2=intent.getExtras().getInt("state");

        TextView timetext = (TextView) findViewById(R.id.selective_time_ms);
        TextView hinttext = (TextView) findViewById(R.id.selective_hint_text);
        Button skipbtn = (Button) findViewById(R.id.skipbutton);
        skipbtn.setVisibility(View.GONE);
        if (level == 1) {
            hinttext.setText("Don't Click On The Red Ball\n\n\nTry To Be As Fast As You Can");
        } else if (level == 2) {
            hinttext.setText("Click On The Red Ball Only\n\n\nTry To Be As Fast As You Can");
        } else if (level == 3) {
            hinttext.setText("Don't Click On Red And Black Balls\n\n\nTry To Be As Fast As You Can");
        } else if (level == 4) {
            hinttext.setText("Click On Red And Black Balls Only\n\n\nTry To Be As Fast As You Can");
        }
        timetext.setText("");
    }

    public void instructionbtn(View view) {

        TextView hinttext = (TextView) findViewById(R.id.selective_hint_text);
        Button gotit_btn = (Button) findViewById(R.id.gotit);
        hinttext.setVisibility(View.GONE);
        gotit_btn.setVisibility(View.GONE);

        TextView timetext = (TextView) findViewById(R.id.selective_time_ms);
        TextView mSecs = (TextView) findViewById(R.id.mSecs);  // showing mSecs separately coz need to paras Int from timetext

        timetext.setText(0 + "");
        mSecs.setText(" mSecs");

        ImageView img = (ImageView) findViewById(R.id.ball);
        img.setImageResource(R.drawable.black);
        img.setEnabled(false);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        width = size.x - img.getHeight();
        height = size.y - img.getWidth();

        Handler handler1 = new Handler();
        for (int a = 0; a < 900; a++) {

            handler1.postDelayed(new Runnable() {
                @Override
                public void run() {

                    Random random = new Random();
                    r2 = random.nextInt(6);
                    starttime = System.currentTimeMillis();

                    ImageView img = (ImageView) findViewById(R.id.ball);
                    img.setX(random.nextInt(width));
                    img.setY(random.nextInt(height));
                    img.setImageResource(imageColor[r2]);
                    img.setEnabled(true);

                    if (r2 == 0) {       //r2 = 0 means red ball, r2=1 means black ball
                        b = 0;
                    } else {
                        b = 1;         //   b is 1 for set 2(set2=red ball click only) so it won't be equal to r2
                    }
                    if (r2 == 0 || r2 == 1) {
                        c = 0;
                    } else {
                        c = 1;
                    }
//                    myscaleanim = AnimationUtils.loadAnimation(Selective_Attention.this, R.anim.justfadein);
//                    img.startAnimation(myscaleanim);

                }
            }, 1000 * a);
        }

    }

    public void imgclick(View view) {

        TextView timetext = (TextView) findViewById(R.id.selective_time_ms);
        Button skipbtn = (Button) findViewById(R.id.skipbutton);
        if (level == 1 && b == 0 || level == 2 && b != 0 || level == 3 && c == 0 || level == 4 && c != 0) {
            life--;
        } else {
            lastScore = Integer.parseInt(timetext.getText().toString());

            nowtime = System.currentTimeMillis();
            int timediff = (int) (nowtime - starttime);
            timetext.setText(timediff + "");

            counts++;
            currentScore = timediff;
            if (currentScore >= slowest) {
                slowest = currentScore;
            }
            if (currentScore <= fastest) {
                fastest = currentScore;
            }

            if (counts >= 10) {
                skipbtn.setVisibility(View.VISIBLE);
            }
        }

        if (life == 0) {
            Intent intent = new Intent(getApplicationContext(), ReflexOutput.class);
            intent.putExtra("FastestTime", fastest);
            intent.putExtra("SlowestTime", slowest);
            intent.putExtra("Points", counts);
            intent.putExtra("level", level);
            intent.putExtra("state",state2);
            startActivity(intent);
            finish();
        }
    }

    public void skiptoresults(View view) {
        Intent intent = new Intent(getApplicationContext(), ReflexOutput.class);
        intent.putExtra("FastestTime", fastest);
        intent.putExtra("SlowestTime", slowest);
        intent.putExtra("Points", counts);
        intent.putExtra("level", level);
        intent.putExtra("state",state2);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Reflex.super.onBackPressed();
                        finish();
                    }
                }).create().show();
    }
}
