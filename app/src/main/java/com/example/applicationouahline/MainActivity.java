package com.example.applicationouahline;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BroadcastReceiver monReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.data.CUSTOM_INTENT");
        monReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "Intent détecté", Toast.LENGTH_LONG).show();
            }
        };
        registerReceiver(monReceiver, filter);
    }
    public void broadcastIntent(View view){
        Intent intent = new Intent();
        intent.setAction("com.data.CUSTOM_INTENT");
        sendBroadcast(intent);
    }

    @Override
    protected void onStart(){
        super.onStart();
    }


    @Override
    protected void onRestart(){
        super.onRestart();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(monReceiver != null){
            unregisterReceiver(monReceiver);
            monReceiver = null;
        }
    }
}