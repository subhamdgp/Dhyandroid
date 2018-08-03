package com.humosys.dhyandroid;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Sarita Kumari on 6/20/2017.
 */

class MemoryGrid_Image_Adapter extends BaseAdapter {
    private Context context;

    MemoryGrid mg = new MemoryGrid();
    int groupSet = mg.getGroupSet()-3;
    int width = mg.width;

    public Integer image_id[] = {R.drawable.a_1,R.drawable.a_2,  //for group of fruits
            R.drawable.a_3,R.drawable.a_4,
            R.drawable.a_5,R.drawable.a_6,
            R.drawable.a_7,R.drawable.a_8,
            R.drawable.a_9,R.drawable.a_10,
            R.drawable.a_11,R.drawable.a_12,
            R.drawable.a_13,R.drawable.a_14,
            R.drawable.a_15};

    private Integer image1_id[] = {R.drawable.a1_1,R.drawable.a1_2,  // for group of animals
            R.drawable.a1_3,R.drawable.a1_4,
            R.drawable.a1_5,R.drawable.a1_6,
            R.drawable.a1_7,R.drawable.a1_8,
            R.drawable.a1_9,R.drawable.a1_10,
            R.drawable.a1_11,R.drawable.a1_12,
            R.drawable.a1_13,R.drawable.a1_14,
            R.drawable.a1_15};

    private Integer image2_id[] = {R.drawable.a2_1,R.drawable.a2_2,  //for group of tech products
            R.drawable.a2_3,R.drawable.a2_4,
            R.drawable.a2_5,R.drawable.a2_6,
            R.drawable.a2_7,R.drawable.a2_8,
            R.drawable.a2_9,R.drawable.a2_10,
            R.drawable.a2_11,R.drawable.a2_12,
            R.drawable.a2_13,R.drawable.a2_14,
            R.drawable.a2_15};

    private Integer image3_id[] = {R.drawable.a3_1,R.drawable.a3_2,  //for group of tech products
            R.drawable.a3_3,R.drawable.a3_4,
            R.drawable.a3_5,R.drawable.a3_6,
            R.drawable.a3_7,R.drawable.a3_8,
            R.drawable.a3_9,R.drawable.a3_10,
            R.drawable.a3_11,R.drawable.a3_12,
            R.drawable.a3_13,R.drawable.a3_14,
            R.drawable.a3_15};

    private Integer image4_id[] = {R.drawable.a4_1,R.drawable.a4_2,  //for group of tech products
            R.drawable.a4_3,R.drawable.a4_4,
            R.drawable.a4_5,R.drawable.a4_6,
            R.drawable.a4_7,R.drawable.a4_8,
            R.drawable.a4_9,R.drawable.a4_10,
            R.drawable.a4_11,R.drawable.a4_12,
            R.drawable.a4_13,R.drawable.a4_14,
            R.drawable.a4_15};

    public MemoryGrid_Image_Adapter(Context context){
        this.context = context;

    }

    @Override
    public int getCount(){
        return image_id.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView img;
        if (convertView == null) {
            img = new ImageView(context);
            img.setLayoutParams(new GridView.LayoutParams(width/3,width/3-10 ));  // width , height
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img.setPadding(5, 5, 5, 5);
        } else {
            img = (ImageView) convertView;
        }


        if (groupSet%15 == 0) {
            img.setImageResource(image_id[position]);
        }
        else if(groupSet%15==3){
            img.setImageResource(image1_id[position]);
        }
        else if(groupSet%15==6){
            img.setImageResource(image2_id[position]);
        }
        else if(groupSet%15==9){
            img.setImageResource(image3_id[position]);
        }
        else if(groupSet%15==12){
            img.setImageResource(image4_id[position]);
        }

        return img;

    }
}
