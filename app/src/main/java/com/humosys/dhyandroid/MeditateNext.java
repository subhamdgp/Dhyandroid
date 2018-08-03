package com.humosys.dhyandroid;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.IOException;


public class MeditateNext extends AppCompatActivity {
    MediaPlayer Song2,Song5,Song7;
    int co=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditate_next);

        final Button meditate2,meditate5,meditate7, btnBack,btnStop;
        meditate2 = (Button)findViewById(R.id.meditate2);
        meditate5 = (Button)findViewById(R.id.meditate5);
        meditate7 = (Button)findViewById(R.id.meditate7);
        btnBack = (Button)findViewById(R.id.meditateBack);
        btnStop = (Button)findViewById(R.id.meditatePause);

        Song7 = MediaPlayer.create(MeditateNext.this,R.raw.min7new);
        Song2 = MediaPlayer.create(MeditateNext.this,R.raw.min2new);
        Song5 = MediaPlayer.create(MeditateNext.this,R.raw.min5new);



        meditate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(Song2.isPlaying()==true){
                        Song2.stop();
                        Song2.prepare();
                    }
                    if(Song5.isPlaying()==true) {
                        Song5.stop();
                        Song5.prepare();
                    }
                    if(Song7.isPlaying()==true) {
                        Song7.stop();
                        Song7.prepare();
                    }
                    Song2.start();

                }
                catch (Exception e){
                    }
                Song2.start();
                }
        });
        meditate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(Song5.isPlaying()==true){
                        Song5.stop();
                        Song5.prepare();
                    }
                    if(Song2.isPlaying()==true) {
                        Song2.stop();
                        Song2.prepare();
                    }
                    if(Song7.isPlaying()==true) {
                        Song7.stop();
                        Song7.prepare();
                    }
                    Song5.start();

                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                Song5.start();

            }
            });
        meditate7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(Song5.isPlaying()==true){
                        Song5.stop();
                        Song5.prepare();}
                    if(Song2.isPlaying()==true){
                        Song2.stop();
                        Song2.prepare();}
                    if(Song7.isPlaying()==true){
                        Song7.stop();
                        Song7.prepare();}
                    Song7.start();
                }
                catch (Exception e) {
                }
                Song7.start();
                }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Song5.isPlaying()==true){
                    Song5.stop();
                    Song5.release();}
                if(Song2.isPlaying()==true){
                    Song2.stop();
                    Song2.release();}
                if(Song7.isPlaying()==true){
                    Song7.stop();
                    Song7.release();}
                onBackPressed();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Song5.isPlaying()==true){
                    Song5.stop();
                    try {
                        Song5.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(Song2.isPlaying()==true){
                    Song2.stop();
                    try {
                        Song2.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(Song7.isPlaying()==true){
                    Song7.stop();
                    try {
                        Song7.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
    @Override
    public void onBackPressed()
    {

        finish();
    }

}
