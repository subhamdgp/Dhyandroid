package com.humosys.dhyandroid;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class CalculationOutput extends AppCompatActivity {

    int points,wrong,correct;
    TextView last_right_text,last_wrong_text,last_points_text;


    public SharedPreferences prefs;
    public SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation_output);

         Intent intent = getIntent();
        points = intent.getExtras().getInt("Points");
        wrong = intent.getExtras().getInt("Wrong");
        correct = intent.getExtras().getInt("Correct");

        //previous data access
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int  last_right = prefs.getInt("CAL_Rights" ,0);
        int  last_wrong = prefs.getInt("CAL_Wrongs" ,0);
        int  last_point = prefs.getInt("CAL_Points" ,0);

        TextView tv1 = (TextView)findViewById(R.id.finalscorevalue);
        TextView tv2 = (TextView)findViewById(R.id.wrongvalue);
        TextView tv3 = (TextView)findViewById(R.id.correctvalue);

        last_right_text = (TextView)findViewById(R.id.last_right_answer);
        last_wrong_text = (TextView)findViewById(R.id.last_wrong_answer);
        last_points_text = (TextView)findViewById(R.id.last_points);

        tv1.setText(points + " pts");
        tv2.setText(wrong + "");
        tv3.setText(correct + "");

        last_right_text.setText("(Previous Right Answers = " + last_right + ")");
        last_wrong_text.setText("(Previous Wrong Answers = " + last_wrong + ")");
        last_points_text.setText("(Previous Point = " + last_point + ")");

        //saving current data
        editor = prefs.edit();
        editor.putInt("CAL_Rights", correct );
        editor.putInt("CAL_Wrongs", wrong);
        editor.putInt("CAL_Points",points );
        editor.commit();
    }

    /**
     * Created by Sarita Kumari on 6/20/2017.
     */

    static class Attention_Image_Adapter extends BaseAdapter {
        private Context context;
        private Integer image_id[][] = { {R.drawable.b_111, R.drawable.b_112,R.drawable.b_113, R.drawable.b_114,R.drawable.b_115,
                R.drawable.b_121,R.drawable.b_122,R.drawable.b_123},
                {R.drawable.b_211, R.drawable.b_212,R.drawable.b_213, R.drawable.b_214,R.drawable.b_215,
                        R.drawable.b_221,R.drawable.b_222,R.drawable.b_223},
                {R.drawable.b_311, R.drawable.b_312, R.drawable.b_313, R.drawable.b_314,R.drawable.b_315,
                        R.drawable.b_316,R.drawable.b_317, R.drawable.b_318}
        };


        Attention attention = new Attention();
        int width = attention.width;

        public static Random rnum = new Random();
        int rnum1 = rnum.nextInt(3);         //choosing random set
        int rnum2 =  rnum.nextInt(8);        //choosing random pic from set
        int rnum3 =  rnum.nextInt(8);        //choosing similar pic from the same set

        public static int num1;
        public Attention_Image_Adapter()  {
            num1 = rnum.nextInt(12);
        }
        public int getNum1(){
            return num1;
        }

        ////////
        public Attention_Image_Adapter(Context context) {
            this.context = context;
        }
        @Override
        public int getCount () {
            return 12;
        }
        @Override
        public Object getItem ( int position){
            return null;
        }
        @Override
        public long getItemId ( int position){
            return 0;
        }
        @Override
        public View getView (int position, View convertView, ViewGroup parent){
            ImageView img;
            if (convertView == null) {
                img = new ImageView(context);
                img.setLayoutParams(new GridView.LayoutParams(width/3, width/3));
                img.setScaleType(ImageView.ScaleType.CENTER_CROP);
                img.setPadding(5, 5, 5, 5);
            } else {
                img = (ImageView) convertView;
            }

            if (position == Attention_Image_Adapter.num1) {   //for setting the "different pic" at random place

                img.setImageResource(image_id[rnum1][rnum2]);
            }else {                                          //for setting the "same pic" at all the remaining places
                while(rnum3==rnum2){                        //while loop so that selected differ pic won't be same as other pics
                    rnum3= rnum.nextInt(8);
                }
                img.setImageResource(image_id[rnum1][rnum3]);
            }

            return img;
        }

    }
}
