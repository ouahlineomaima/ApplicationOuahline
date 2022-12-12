package com.example.applicationouahline;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import 	android.provider.AlarmClock;

public class MainActivity3 extends AppCompatActivity {

    Button bouton_explicite, boutton_implicite, boutton_implicite2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        bouton_explicite = (Button) findViewById(R.id.button);
        boutton_implicite = (Button) findViewById(R.id.button2);
        boutton_implicite2 = (Button) findViewById(R.id.button2);

        bouton_explicite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity4.class);
                intent.putExtra("nom", "ouahline");
                intent.putExtra("prénom", "omaima");
                startActivity(intent);
            }
        });

        boutton_implicite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.inpt.ac.ma"));
                startActivity(intent);
            }
        });

        boutton_implicite2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                        .putExtra(AlarmClock.EXTRA_MESSAGE, "alarme tp")
                        .putExtra(AlarmClock.EXTRA_HOUR, 10)
                        .putExtra(AlarmClock.EXTRA_MINUTES, 13);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }
}