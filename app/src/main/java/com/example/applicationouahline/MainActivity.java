package com.example.applicationouahline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void demarrerService(View view){
        Intent startIntent = new Intent(this, RingtoneIntentService.class);
        startIntent.setAction(RingtoneIntentService.ACTION_START_RINGTONE);
        startService(startIntent);
    }

    public void arreterService(View view){
        Intent stopIntent = new Intent(this, RingtoneIntentService.class);
        stopIntent.setAction(RingtoneIntentService.ACTION_STOP_RINGTONE);
        startService(stopIntent);
    }


}