package com.example.applicationouahline;

import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

public class UnService extends Service {
    private static  final String TAG = "MonService";
    private MediaPlayer lecteur;

    public class LocalBinder extends Binder{
        UnService getService(){
            return UnService.this;
        }
    }
    private final IBinder iBinder = new LocalBinder();

    public UnService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        lecteur = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        lecteur.setLooping(true);
        lecteur.start();

        Log.i(TAG, "le service a été démarré");
        Toast.makeText(this, "Service démarré", Toast.LENGTH_LONG).show();
        return iBinder;
        //throw new UnsupportedOperationException("Not yet implemented");

    }

    @Override
    public void onCreate(){
        Log.i(TAG, "le service a été créé");
        Toast.makeText(this, "Service créé", Toast.LENGTH_LONG).show();
    }
    /*
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        lecteur = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        lecteur.setLooping(true);
        lecteur.start();

        Log.i(TAG, "le service a été démarré");
        Toast.makeText(this, "Service démarré", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

     */


    @Override
    public void onDestroy(){
        super.onDestroy();

        Log.i(TAG, "Le service a été détruit");
        Toast.makeText(this, "Service Détruit", Toast.LENGTH_LONG).show();
       // lecteur.stop();
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getApplicationContext());
        builder1.setTitle("Arrêter le service");
        builder1.setMessage("Voulez-vous arrêter le service");
        builder1.setCancelable(true);
        builder1.setPositiveButton("oui", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                lecteur.stop();
            }} );
        builder1.setNegativeButton("non",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        } );
        AlertDialog alert1 = builder1.create();
        alert1.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alert1.show();
    }
}