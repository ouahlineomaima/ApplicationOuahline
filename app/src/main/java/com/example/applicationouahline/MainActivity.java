package com.example.applicationouahline;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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


    UnService myservice;
    boolean isBound = false;
    EditText nameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameField = findViewById(R.id.nom);
        /*
        // creation d'une autre activité
        Toast.makeText(this,"Activité est créée", Toast.LENGTH_SHORT).show();
        Log.i("onCreate():", "Activité est créée");

        createbtn = findViewById(R.id.createbtn);
        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                //startActivity(intent);
                startActivityIfNeeded(intent, 2);
            }
        });

        destroybtn = findViewById(R.id.destroybtn);
        destroybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                startActivity(intent);
            }
        });

        setbtn = findViewById(R.id.setbtn);
        setbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                        .putExtra(AlarmClock.EXTRA_MESSAGE, "alarme tp")
                        .putExtra(AlarmClock.EXTRA_HOUR, 10)
                        .putExtra(AlarmClock.EXTRA_MINUTES, 25);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

         */

    }
    public void onClickAjouterNoteInptiste(View view){
        ContentValues values = new ContentValues();
        values.put(InptisteProvider.NOM, ((EditText)findViewById(R.id.nom)).getText().toString());
        values.put(InptisteProvider.NOTE, ((EditText)findViewById(R.id.note)).getText().toString());

        Uri uri = getContentResolver().insert(InptisteProvider.CONTENT_URI, values);

        assert uri != null;
        Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void onClickRetrouverNotesInptistes(View view){
        String URL = "content://com.example.applicationouahline.InptisteProvider";

        Uri inptistes = Uri.parse(URL);
        Cursor c;
        c = getContentResolver().query(inptistes, null, null, null, "nom");

        assert c != null;
        if(c.moveToFirst()){
            do Toast.makeText(this,
                    c.getString(c.getColumnIndex(InptisteProvider._ID)) +
                    ";" + c.getString(c.getColumnIndex(InptisteProvider.NOM)) +
                            ";" + c.getString(c.getColumnIndex(InptisteProvider.NOTE)),
                    Toast.LENGTH_SHORT).show();while (c.moveToNext());
        }
    }
    public void onClickRetrouverNotesInptiste(View view){
        String name = nameField.getText().toString();
        String URL = "content://com.example.applicationouahline.InptisteProvider";

        Uri inptistes = Uri.parse(URL);
        Cursor c;
        String [] l = {name};
        c = getContentResolver().query(inptistes, null, "nom= ?", l, "nom");

        assert c != null;
        if(c.moveToFirst()){
            do Toast.makeText(this,
                    c.getString(c.getColumnIndex(InptisteProvider._ID)) +
                            ";" + c.getString(c.getColumnIndex(InptisteProvider.NOM)) +
                            ";" + c.getString(c.getColumnIndex(InptisteProvider.NOTE)),
                    Toast.LENGTH_SHORT).show();while (c.moveToNext());
        }
        else {
            Toast.makeText(this, "Etudiant non trouvé", Toast.LENGTH_LONG).show();
        }
    }
    /*
    //start the service
    protected ServiceConnection mServerConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            UnService.LocalBinder binder = (UnService.LocalBinder) service;
            myservice = binder.getService();
            isBound = true;
            Log.i("", "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
            Log.i("", "onServiceDisconnected");
        }
    };
    public void demmarrerService(View view){
        //startService(new Intent(this, UnService.class));
        bindService(new Intent(this, UnService.class), mServerConn, Context.BIND_AUTO_CREATE);
    }

    //stop the service
    public void arreterService(View view){
        unbindService(mServerConn);
        //stopService(new Intent(this, UnService.class));
    }*/

    @Override
    protected void onStart(){
        super.onStart();
        /*
        Toast.makeText(this, "Activité est démarrée", Toast.LENGTH_SHORT).show();
        Log.i("onStart():", "Activité est démarrée");
         */
    }


    @Override
    protected void onRestart(){
        super.onRestart();
        /*
        Toast.makeText(this, "Activité est redémarrée", Toast.LENGTH_SHORT).show();
        Log.i("onRestart():", "Activité est redémarrée");

         */
    }

    @Override
    protected void onResume(){
        super.onResume();
        /*
        Toast.makeText(this,"Activité est reprise", Toast.LENGTH_SHORT).show();
        Log.i("onResume():", "Activité est démarrée");
         */
    }

    @Override
    protected void onPause(){
        super.onPause();
        /*
        Toast.makeText(this, "Activité est en pause", Toast.LENGTH_SHORT).show();
        Log.i("onPause():", "Activité est en pause");

         */
    }

    @Override
    protected void onStop(){
        super.onStop();
        /*
        Toast.makeText(this, "Activité est stoppée", Toast.LENGTH_SHORT).show();
        Log.i("onStop():","Activité est stoppée");

         */
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        /*
        Toast.makeText(this, "Activité est détruite", Toast.LENGTH_SHORT).show();
        Log.i("onDestroy():", "Activity destroyed");
         */
    }
}