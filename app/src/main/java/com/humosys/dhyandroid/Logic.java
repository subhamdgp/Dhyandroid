package com.humosys.dhyandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Logic extends AppCompatActivity {
    Button startLogicGame;
    int state1=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logic);

        Intent intent = getIntent();
        state1=intent.getExtras().getInt("state");

        startLogicGame =(Button)findViewById(R.id.startLogicGame);

        startLogicGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startLGame = new Intent(Logic.this,LogicGame.class);
                startLGame.putExtra("state",state1);
                Logic.this.finish();
                startActivity(startLGame);
            }
        });
    }

}
