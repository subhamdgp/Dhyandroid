package com.humosys.dhyandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;


public class Aggression extends AppCompatActivity {

    private Button detectPitch,analyse;
    final ArrayList<Float> pitchList = new ArrayList<Float>();
    double perAgg=0.00;
    SharedPreferences prefs,prefs1;
    SharedPreferences.Editor Editor1,Editor2;

    Thread a;
    AudioProcessor p;
    AudioDispatcher dispatcher;
    int state=0;


    Handler update = new Handler(){
        @Override
        public  void handleMessage(Message msg){
            TextView text = (TextView)findViewById(R.id.peragg);
            text.setText("Percentage Aggression= "+ perAgg);
        }
    };

    //Function for calculating %Aggression
    void calculate(){
        int i,n;
        double sum1=0,mean,diff,sum2=0,sDeviation,aggression;
        n=pitchList.size();
        for(i=0;i<n;i++)
        {
            sum1 = sum1 + pitchList.get(i);
        }
        mean=sum1/n;
        for(i=0;i<n;i++)
        {
            diff=mean-pitchList.get(i);
            diff=diff*diff;
            sum2= sum2 +diff;
        }
        sum2=sum2/(n-1);
        sDeviation=Math.sqrt(sum2);
        aggression=(sDeviation/mean)*100;
        perAgg = Math.round(aggression * 100.0) / 100.0;
        storeAggressionResult();
    }

    public void storeAggressionResult()
    {
        if(state==1)
        {
            prefs=getSharedPreferences("beforeMeditation", Context.MODE_PRIVATE);
            Editor1 = prefs.edit();
            Editor1.putFloat("aggressionScore",(float)perAgg);
            Editor1.commit();
        }
        else if(state==2)
        {
            prefs1=getSharedPreferences("afterMeditation", Context.MODE_PRIVATE);
            Editor2 = prefs1.edit();
            Editor2.putFloat("aggressionScore",(float)perAgg);
            Editor2.commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggression);

        Intent getState1 =  getIntent();
        state = getState1.getExtras().getInt("state");

        detectPitch = (Button) findViewById(R.id.startButton);
        //analyse = (Button) findViewById(R.id.btnAnalyse);

        detectPitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detectPitch.setEnabled(false);
                dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(22050,1024,0);

                PitchDetectionHandler pdh = new PitchDetectionHandler() {
                    @Override
                    public void handlePitch(PitchDetectionResult result,AudioEvent e) {
                        final float pitchInHz = result.getPitch();
                        if(pitchInHz!=-1)
                            pitchList.add(pitchInHz);
                    }
                };
                p = new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.FFT_YIN, 22050, 1024, pdh);
                dispatcher.addAudioProcessor(p);
                a= new Thread(dispatcher,"Audio Dispatcher");
                a.start();

                Runnable r =new Runnable() {
                    @Override
                    public void run() {
                        long startTime = System.currentTimeMillis();
                        long endTime = startTime + 6000;
                        while (startTime < endTime) {
                            // Still within time theshold, wait a little longer
                            Log.i("Thread", "onClick: current time  "+System.currentTimeMillis());
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            startTime=System.currentTimeMillis();
                        }
                        Log.i("10secOver", "onClick: before stop");
                        dispatcher.stop();
                        p.processingFinished();
                        Log.i("StopOver", "onClick: after stop");
                        calculate();
                        update.sendEmptyMessage(0);
                    }
                };
                Thread delay = new Thread(r);
                delay.start();
            }
        });

    }
}
