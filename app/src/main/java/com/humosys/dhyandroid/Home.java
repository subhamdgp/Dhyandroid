package com.humosys.dhyandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {
    public Button meditate,evaluate,compare,exits,logout;
    SharedPreferences prefs,prefs1;
    SharedPreferences.Editor edit1,edit2;
    TextView textViewDislpayName,textViewCopyrights;
    public void init(){
        meditate = (Button) findViewById(R.id.button_meditate);
        evaluate = (Button) findViewById(R.id.button_evaluate);
        compare = (Button) findViewById(R.id.button_compare);
        exits = (Button) findViewById(R.id.button_exit);
        logout = (Button) findViewById(R.id.buttonLogout);
        textViewCopyrights = (TextView)findViewById(R.id.textViewCopyRights);

        meditate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent obj1=new Intent(Home.this,MeditateNext.class);
                startActivity(obj1);
            }
        });

        evaluate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent obj2=new Intent(Home.this,EvaluateNext.class);
                startActivity(obj2);
            }
        });

        //on clicking compare
        compare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj7 = new Intent(Home.this,Compare.class);
                startActivity(obj7);
            }
        });
        exits.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onDestroy();
                System.exit(0);
            }
        });

        //On clicking LogOut Button
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent1 = new Intent(Home.this,LoginActivity.class);
                startActivity(intent1);
                Home.this.finish();
            }
        });

        //On Clicking Copyrights
        textViewCopyrights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this,CreditsActivity.class);
                startActivity(i);
            }
        });
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null)
        {
            textViewDislpayName = (TextView)findViewById(R.id.textViewDisplayName);
            String name = user.getDisplayName();
            textViewDislpayName.setText("Hi, "+ name+ " Welcome!");
        }
        init();
    }
    @Override
    public void onDestroy() {

        prefs = getSharedPreferences("beforeMeditation", Context.MODE_PRIVATE);
        edit1 = prefs.edit();
        edit1.putInt("emotionScore", 0);
        edit1.putInt("memoryScore", 0);
        edit1.putInt("stressScore", 0);
        edit1.putInt("logicScore", 0);
        edit1.putInt("moodScore", 0);
        edit1.putFloat("scoreAttention", 0);
        edit1.putFloat("reflexTime", 0);
        edit1.putFloat("aggressionScore", 0);
        edit1.commit();

        prefs1 = getSharedPreferences("afterMeditation", Context.MODE_PRIVATE);
        edit2 = prefs1.edit();
        edit2.putInt("emotionScore1", 0);
        edit2.putInt("memoryScore", 0);
        edit2.putInt("stressScore", 0);
        edit2.putInt("logicScore", 0);
        edit2.putInt("moodScore", 0);
        edit2.putFloat("scoreAttention", 0);
        edit2.putFloat("reflexTime", 0);
        edit2.putFloat("aggressionScore", 0);
        edit2.commit();
        super.onDestroy();
    }


}
