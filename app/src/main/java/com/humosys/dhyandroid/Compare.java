package com.humosys.dhyandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;

import java.util.ArrayList;

public class Compare extends AppCompatActivity {

    int emotionScore1,emotionScore2,memoryScore1 ,memoryScore2 ,moodScore1,moodScore2,stressScore1,stressScore2,logicScore1,logicScore2;

    float attentionTime1,attentionTime2,reflexTime1,reflexTime2,aggScore1,aggScore2;
    public SharedPreferences prefs,prefs1;

    private BarChart chart;
    float barWidth;
    float barSpace;
    float groupSpace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        barWidth = 0.3f;
        barSpace = 0f;
        groupSpace = 0.4f;

        prefs = getSharedPreferences("beforeMeditation", Context.MODE_PRIVATE);
        emotionScore1 = prefs.getInt("emotionScore",0);
        memoryScore1 = prefs.getInt("memoryScore",0);
        stressScore1=prefs.getInt("stressScore",0);
        attentionTime1=prefs.getFloat("scoreAttention",0);
        reflexTime1=prefs.getFloat("reflexTime",0);
        logicScore1=prefs.getInt("logicScore",0);
        moodScore1=prefs.getInt("moodScore",0);
        aggScore1=prefs.getFloat("aggressionScore",0);

        prefs1= getSharedPreferences("afterMeditation",Context.MODE_PRIVATE);
        emotionScore2 = prefs1.getInt("emotionScore1",0);
        memoryScore2=prefs1.getInt("memoryScore",0);
        stressScore2=prefs1.getInt("stressScore",0);
        attentionTime2=prefs1.getFloat("scoreAttention",0);
        reflexTime2=prefs1.getFloat("reflexTime",0);
        logicScore2=prefs1.getInt("logicScore",0);
        moodScore2=prefs1.getInt("moodScore",0);
        aggScore2=prefs1.getFloat("aggressionScore",0);

        float e1=0,e2=0,mem1=0,mem2=0,s1=0,s2=0,att1=0,att2=0,r1=0,r2=0,l1=0,l2=0,mo1=0,mo2=0,agg1=0,agg2=0;

        e1=emotionScore1/10;
        e2=emotionScore2/10;
        mem1=memoryScore1/2;
        mem2=memoryScore2/2;
        s1=stressScore1/10;
        s2=stressScore2/10;
        att1=attentionTime1/10;
        att2=attentionTime2/10;
        r1=reflexTime1/100;
        r2=reflexTime2/100;
        l1=logicScore1/10;
        l2=logicScore2/10;
        mo1=moodScore1/10;
        mo2=moodScore2/10;
        agg1=aggScore1/10;
        agg2=aggScore2/10;

        chart = (BarChart)findViewById(R.id.barChart);
        chart.setDescription(null);
        chart.setPinchZoom(false);
        chart.setScaleEnabled(false);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);


        int groupCount = 8;

        ArrayList xVals = new ArrayList();

        xVals.add("Emotion");
        xVals.add("Memory");
        xVals.add("Stress");
        xVals.add("Attention");
        xVals.add("Reflex");
        xVals.add("Logic");
        xVals.add("Mood");
        xVals.add("Aggression");

        ArrayList yVals1 = new ArrayList();
        ArrayList yVals2 = new ArrayList();

        yVals1.add(new BarEntry(1, (float) e1));
        yVals2.add(new BarEntry(1, (float) e2));
        yVals1.add(new BarEntry(2, (float) mem1));
        yVals2.add(new BarEntry(2, (float) mem2));
        yVals1.add(new BarEntry(3, (float) s1));
        yVals2.add(new BarEntry(3, (float) s2));
        yVals1.add(new BarEntry(4, (float) att1));
        yVals2.add(new BarEntry(4, (float) att2));
        yVals1.add(new BarEntry(5, (float) r1));
        yVals2.add(new BarEntry(5, (float) r2));
        yVals1.add(new BarEntry(6, (float) l1));
        yVals2.add(new BarEntry(6, (float) l2));
        yVals1.add(new BarEntry(7, (float) mo1));
        yVals2.add(new BarEntry(7, (float) mo2));
        yVals1.add(new BarEntry(8, (float) agg1));
        yVals2.add(new BarEntry(8, (float) agg2));

        BarDataSet set1, set2;
        set1 = new BarDataSet(yVals1, "Before Meditation");
        set1.setColor(Color.RED);
        set2 = new BarDataSet(yVals2, "After Meditation");
        set2.setColor(Color.BLUE);
        BarData data = new BarData(set1, set2);
        data.setValueFormatter(new LargeValueFormatter());
        chart.setData(data);
        chart.getBarData().setBarWidth(barWidth);
        chart.getXAxis().setAxisMinimum(0);
        chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        chart.groupBars(0, groupSpace, barSpace);
        chart.getData().setHighlightEnabled(false);
        chart.invalidate();

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(true);
        l.setYOffset(20f);
        l.setXOffset(0f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);

        //X-axis
        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMaximum(8);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xVals));
//Y-axis
        chart.getAxisRight().setEnabled(false);
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(true);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f);



        Log.i("before", "onCreate: "+emotionScore1);
        Log.i("before", "onCreate: "+memoryScore1);
        Log.i("before", "onCreate: "+stressScore1);
        Log.i("before", "onCreate: "+attentionTime1);
        Log.i("before", "onCreate: "+reflexTime1);
        Log.i("before", "onCreate: "+logicScore1);
        Log.i("before", "onCreate: "+moodScore1);
        Log.i("before", "onCreate: "+aggScore1);


        Log.i("after", "onCreate: "+emotionScore2);
        Log.i("after", "onCreate: "+memoryScore2);
        Log.i("after", "onCreate: "+stressScore2);
        Log.i("after", "onCreate: "+attentionTime2);
        Log.i("after", "onCreate: "+reflexTime2);
        Log.i("after", "onCreate: "+logicScore2);
        Log.i("after", "onCreate: "+moodScore2);
        Log.i("after", "onCreate: "+aggScore2);
    }

}
