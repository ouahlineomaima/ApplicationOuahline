package com.example.applicationouahline;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private BroadcastReceiver monReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.data.CUSTOM_INTENT");
        monReceiver = new BroadcastReceiver(){

            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "Intent détécté", Toast.LENGTH_LONG).show();
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
    protected void onDestroy(){
        super.onDestroy();
        if(monReceiver != null){
            unregisterReceiver(monReceiver);
            monReceiver = null;
        }
    }
}