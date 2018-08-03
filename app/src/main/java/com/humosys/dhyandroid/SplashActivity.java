package com.humosys.dhyandroid;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


            if (ContextCompat.checkSelfPermission(SplashActivity.this,
                    Manifest.permission.RECORD_AUDIO)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                // Should we show an explanation?
                Log.d("Permission: ", "Not Granted");
                // No explanation needed; request the permission
                Log.d("Permission: ", "Requesting Permission");
                ActivityCompat.requestPermissions(SplashActivity.this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.

            } else {
                // Permission has already been granted
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(i);
                        finish();
                    }
                }, 1500);
            }
        }

    @Override
    protected void onStart() {
        super.onStart();
    }


    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission has already been granted
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(SplashActivity.this,LoginActivity.class);
                            startActivity(i);
                            finish();
                        }
                    },1500);

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)){
                        Toast.makeText(this, "Please allow to start the app..", Toast.LENGTH_LONG).show();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(SplashActivity.this, SplashActivity.class);
                                startActivity(intent);
                                finish();                        }
                        },1500);

                    }
                    else{
                        Toast.makeText(this, "Permission is required", Toast.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();                        }
                        },1000);
                    }
                }
                return;
            }
        }
    }

}
