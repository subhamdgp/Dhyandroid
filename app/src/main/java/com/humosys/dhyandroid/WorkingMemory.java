package com.humosys.dhyandroid;


import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class WorkingMemory extends AppCompatActivity {

    public  int num1,num2,num3,num4;
    //    long time=0;
    public int width;
    int group;
    MemoryGrid mg = new MemoryGrid();
    int groupSet= mg.getGroupSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_memory);


        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x - 90;


        final Integer image_pos[] = {R.drawable.a_1,R.drawable.a_2, //fruits
                R.drawable.a_3,R.drawable.a_4,
                R.drawable.a_5,R.drawable.a_6,
                R.drawable.a_7,R.drawable.a_8,
                R.drawable.a_9,R.drawable.a_10,
                R.drawable.a_11,R.drawable.a_12,
                R.drawable.a_13,R.drawable.a_14,
                R.drawable.a_15};

        final Integer image1_pos[] = {R.drawable.a1_1,R.drawable.a1_2,  //animals
                R.drawable.a1_3,R.drawable.a1_4,
                R.drawable.a1_5,R.drawable.a1_6,
                R.drawable.a1_7,R.drawable.a1_8,
                R.drawable.a1_9,R.drawable.a1_10,
                R.drawable.a1_11,R.drawable.a1_12,
                R.drawable.a1_13,R.drawable.a1_14,
                R.drawable.a1_15};

        final Integer image2_pos[] = {R.drawable.a2_1,R.drawable.a2_2, //tech products
                R.drawable.a2_3,R.drawable.a2_4,
                R.drawable.a2_5,R.drawable.a2_6,
                R.drawable.a2_7,R.drawable.a2_8,
                R.drawable.a2_9,R.drawable.a2_10,
                R.drawable.a2_11,R.drawable.a2_12,
                R.drawable.a2_13,R.drawable.a2_14,
                R.drawable.a2_15};

        final Integer image3_pos[] = {R.drawable.a3_1,R.drawable.a3_2,  //for group of vehicles
                R.drawable.a3_3,R.drawable.a3_4,
                R.drawable.a3_5,R.drawable.a3_6,
                R.drawable.a3_7,R.drawable.a3_8,
                R.drawable.a3_9,R.drawable.a3_10,
                R.drawable.a3_11,R.drawable.a3_12,
                R.drawable.a3_13,R.drawable.a3_14,
                R.drawable.a3_15};

        final Integer image4_pos[] = {R.drawable.a4_1,R.drawable.a4_2,  //for group of weapons
                R.drawable.a4_3,R.drawable.a4_4,
                R.drawable.a4_5,R.drawable.a4_6,
                R.drawable.a4_7,R.drawable.a4_8,
                R.drawable.a4_9,R.drawable.a4_10,
                R.drawable.a4_11,R.drawable.a4_12,
                R.drawable.a4_13,R.drawable.a4_14,
                R.drawable.a4_15};

        //choosing 4 different random numbers
        Random rnum = new Random();
        num1 =  rnum.nextInt(15);
        num2 =  rnum.nextInt(15);
        while(num2 ==num1) {
            num2 =  rnum.nextInt(15);
        }
        num3 =  rnum.nextInt(15);
        while(num3==num2||num3==num1){
            num3 = rnum.nextInt(15);
        }
        num4 = rnum.nextInt(15);
        while(num4==num3||num4==num2||num4==num1){
            num4 = rnum.nextInt(15);
        }


        final int a  = rnum.nextInt(11);
        final int b  = rnum.nextInt(11);
        final int c  = rnum.nextInt(11);
        final int d  = rnum.nextInt(11);
        final int e  = rnum.nextInt(11);

        final Integer[] image_distract,image1_distract,image2_distract,image3_distract,image4_distract;
        List<Integer> list = new ArrayList<Integer>();
        List<Integer> list1 = new ArrayList<Integer>();
        List<Integer> list2 = new ArrayList<Integer>();
        List<Integer> list3 = new ArrayList<Integer>();
        List<Integer> list4 = new ArrayList<Integer>();

        Collections.addAll(list, image_pos);
        Collections.addAll(list1, image1_pos);
        Collections.addAll(list2, image2_pos);
        Collections.addAll(list3, image3_pos);
        Collections.addAll(list4, image4_pos);

        list.removeAll(Arrays.asList(image_pos[num1],image_pos[num2],image_pos[num3],image_pos[num4]));
        list1.removeAll(Arrays.asList(image1_pos[num1],image1_pos[num2],image1_pos[num3],image1_pos[num4]));
        list2.removeAll(Arrays.asList(image2_pos[num1],image2_pos[num2],image2_pos[num3],image2_pos[num4]));
        list3.removeAll(Arrays.asList(image3_pos[num1],image3_pos[num2],image3_pos[num3],image3_pos[num4]));
        list4.removeAll(Arrays.asList(image4_pos[num1],image4_pos[num2],image4_pos[num3],image4_pos[num4]));

        image_distract = list.toArray(new Integer[list.size()]);
        image1_distract = list1.toArray(new Integer[list1.size()]);
        image2_distract = list2.toArray(new Integer[list2.size()]);
        image3_distract = list3.toArray(new Integer[list3.size()]);
        image4_distract = list4.toArray(new Integer[list4.size()]);

        Handler handler1 = new Handler();     //setting distraction
        for (int i = 0; i<80 ;i++) {

            handler1.postDelayed(new Runnable() {
                @Override
                public void run() {

                    ImageView img1 = (ImageView) findViewById(R.id.distraction1);
                    ImageView img2 = (ImageView) findViewById(R.id.distraction2);
                    ImageView img3 = (ImageView) findViewById(R.id.distraction3);
                    ImageView img4 = (ImageView) findViewById(R.id.distraction4);
                    ImageView img5 = (ImageView) findViewById(R.id.distraction5);

                    //showing 4 random pics
                    ImageView img01 = (ImageView) findViewById(R.id.imageView1);
                    ImageView img02 = (ImageView) findViewById(R.id.imageView2);
                    ImageView img03 = (ImageView) findViewById(R.id.imageView3);
                    ImageView img04 = (ImageView) findViewById(R.id.imageView4);

                    float x1 = img1.getX();
                    float y1 = img1.getY();
                    float x2 = img2.getX();
                    float y2 = img2.getY();
                    float x3 = img3.getX();
                    float y3 = img3.getY();
                    float x4 = img4.getX();
                    float y4 = img4.getY();
                    float x5 = img5.getX();
                    float y5 = img5.getY();

                    y1 = y1-7;
                    y2 = y2-10;
                    y3 = y3-15;
                    y4 = y4-10;
                    y5 = y5-7;

                    if (groupSet % 15 ==1) {                 //%15 coz groupSet is static int created in MemoryGrid class, keep increasing
                        img1.setImageResource(image_distract[a]);  // like 1,4,7,10... until we don't close the app
                        img2.setImageResource(image_distract[b]);
                        img3.setImageResource(image_distract[c]);
                        img4.setImageResource(image_distract[d]);
                        img5.setImageResource(image_distract[e]);

                        img01.setImageResource(image_pos[num1]);
                        img02.setImageResource(image_pos[num2]);
                        img03.setImageResource(image_pos[num3]);
                        img04.setImageResource(image_pos[num4]);

                        group = 1;
                    }
                    else if (groupSet % 15 == 4) {
                        img1.setImageResource(image1_distract[a]);
                        img2.setImageResource(image1_distract[b]);
                        img3.setImageResource(image1_distract[c]);
                        img4.setImageResource(image1_distract[d]);
                        img5.setImageResource(image1_distract[e]);

                        img01.setImageResource(image1_pos[num1]);
                        img02.setImageResource(image1_pos[num2]);
                        img03.setImageResource(image1_pos[num3]);
                        img04.setImageResource(image1_pos[num4]);

                        group = 2;
                    }
                    else if (groupSet % 15 == 7) {
                        img1.setImageResource(image2_distract[a]);
                        img2.setImageResource(image2_distract[b]);
                        img3.setImageResource(image2_distract[c]);
                        img4.setImageResource(image2_distract[d]);
                        img5.setImageResource(image2_distract[e]);

                        img01.setImageResource(image2_pos[num1]);
                        img02.setImageResource(image2_pos[num2]);
                        img03.setImageResource(image2_pos[num3]);
                        img04.setImageResource(image2_pos[num4]);

                        group = 3;
                    }
                    else if (groupSet % 15 == 10) {
                        img1.setImageResource(image3_distract[a]);
                        img2.setImageResource(image3_distract[b]);
                        img3.setImageResource(image3_distract[c]);
                        img4.setImageResource(image3_distract[d]);
                        img5.setImageResource(image3_distract[e]);

                        img01.setImageResource(image3_pos[num1]);
                        img02.setImageResource(image3_pos[num2]);
                        img03.setImageResource(image3_pos[num3]);
                        img04.setImageResource(image3_pos[num4]);

                        group = 4;
                    }
                    else if (groupSet % 15 == 13) {
                        img1.setImageResource(image4_distract[a]);
                        img2.setImageResource(image4_distract[b]);
                        img3.setImageResource(image4_distract[c]);
                        img4.setImageResource(image4_distract[d]);
                        img5.setImageResource(image4_distract[e]);

                        img01.setImageResource(image4_pos[num1]);
                        img02.setImageResource(image4_pos[num2]);
                        img03.setImageResource(image4_pos[num3]);
                        img04.setImageResource(image4_pos[num4]);

                        group = 5;
                    }

                    img1.setX(x1-6);
                    img1.setY(y1);
                    img2.setX(x2-3);
                    img2.setY(y2);
                    img3.setX(x3);
                    img3.setY(y3);
                    img4.setX(x4+3);
                    img4.setY(y4);
                    img5.setX(x5+6);
                    img5.setY(y5);


                }
            }, 50*i);
        }


//        final Chronometer chronometer = (Chronometer)findViewById(R.id.chronometer);
//        chronometer.setBase(SystemClock.elapsedRealtime()+time);
//        chronometer.start();

        new Timer().schedule(new TimerTask(){
            public void run() {
//                chronometer.stop();
//                time = SystemClock.elapsedRealtime() - chronometer.getBase();
                Intent intent = new Intent(getApplicationContext(), MemoryGrid.class);

                intent.putExtra("num1", num1);
                intent.putExtra("num2", num2);
                intent.putExtra("num3", num3);
                intent.putExtra("num4", num4);
                intent.putExtra("group", group);
                startActivity(intent);
                finish();
            }
        }, 4100);
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(getBaseContext(),"can't go back",Toast.LENGTH_SHORT).show();
        // do nothing.
    }
}

