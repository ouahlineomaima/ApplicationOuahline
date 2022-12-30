package com.example.applicationouahline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Activité est crée", Toast.LENGTH_SHORT).show();
        Log.i("onCreate()", "Activité est crée");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Toast.makeText(this, "Activité est démarrée", Toast.LENGTH_SHORT).show();
        Log.i("onStart()", "Activité est démarrée");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Toast.makeText(this, "Activité est redémarrée", Toast.LENGTH_SHORT).show();
        Log.i("onRestart()", "Activité est redémarrée");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Toast.makeText(this, "Activité est reprise", Toast.LENGTH_SHORT).show();
        Log.i("onResume()", "Activité est démarrée");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Toast.makeText(this, "Activité est en pause", Toast.LENGTH_SHORT).show();
        Log.i("onPause()", "Activité est en pause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Toast.makeText(this, "Activité est stoppée", Toast.LENGTH_SHORT).show();
        Log.i("onStop()", "Activité est stoppée");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Toast.makeText(this, "Activité est détruite", Toast.LENGTH_SHORT).show();
        Log.i("onDestroy()", "Activity desttroyed");
    }
}