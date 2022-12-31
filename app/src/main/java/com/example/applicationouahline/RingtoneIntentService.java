package com.example.applicationouahline;

import android.app.IntentService;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

public class RingtoneIntentService extends IntentService {

     static final String ACTION_START_RINGTONE = "com.example.action.START_RINGTONE";
     static final String ACTION_STOP_RINGTONE = "com.example.action.STOP_RINGTONE";

    private Ringtone ringtone;

    public RingtoneIntentService() {
        super("RingtoneIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_START_RINGTONE.equals(action)) {
                handleActionStartRingtone();
            } else if (ACTION_STOP_RINGTONE.equals(action)) {
                handleActionStopRingtone();
            }
        }
    }

    private void handleActionStartRingtone() {
        Uri ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        ringtone = RingtoneManager.getRingtone(getApplicationContext(), ringtoneUri);
        ringtone.play();
    }

    private void handleActionStopRingtone() {
        if (ringtone != null) {
            ringtone.stop();
        }
    }
}