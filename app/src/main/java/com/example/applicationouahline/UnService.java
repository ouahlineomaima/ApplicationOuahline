package com.example.applicationouahline;

import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

public class UnService extends Service {
    private static final String TAG = "Mon service";

    private MediaPlayer lecteur;

    public UnService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");

    }

    @Override
    public void onCreate(){
        Log.i(TAG, "le service a été créé");
        Toast.makeText(this, "service créé", Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        lecteur = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        lecteur.setLooping(true);
        lecteur.start();

        Log.i(TAG, "Le service a été démarré");
        Toast.makeText(this, "Service démarré", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "Le service a été détruit");
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setMessage("Voulez-vous arrêter le lecteur ?");
        builder.setTitle("Alert !");
        builder.setCancelable(false);
        builder.setPositiveButton("Oui", (DialogInterface.OnClickListener) (dialog, which) -> {
            Toast.makeText(this, "Service détruit", Toast.LENGTH_LONG).show();
            lecteur.stop();
        });
        builder.setNegativeButton("Non", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
            Toast.makeText(this, "Service non détruit", Toast.LENGTH_LONG).show();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}