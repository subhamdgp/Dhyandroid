package com.humosys.dhyandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ReflexLevels extends AppCompatActivity {

    int n;
    int state1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflex_levels);
        Intent getStateR =getIntent();
        state1=getStateR.getExtras().getInt("state");
    }
    public void level1(View view) {
        Intent intent = new Intent(getApplicationContext(),Reflex.class);
        n=1;
        intent.putExtra("level", n);
        intent.putExtra("state",state1);
        startActivity(intent);

    }

    public void level2(View view) {
        Intent intent = new Intent(getApplicationContext(), Reflex.class);
        n=2;
        intent.putExtra("level", n);
        intent.putExtra("state",state1);
        startActivity(intent);

    }

    public void level3(View view) {
        Intent intent = new Intent(getApplicationContext(), Reflex.class);
        n=3;
        intent.putExtra("level", n);
        intent.putExtra("state",state1);
        startActivity(intent);

    }

    public void level4(View view) {
        Intent intent = new Intent(getApplicationContext(), Reflex.class);
        n=4;
        intent.putExtra("level", n);
        intent.putExtra("state",state1);
        startActivity(intent);

    }
}
