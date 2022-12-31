package com.example.applicationouahline;

import android.content.Intent;
import android.net.Uri;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button bouton_implicite, bouton_explicite, setbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bouton_explicite = findViewById(R.id.button);
        bouton_implicite = findViewById(R.id.button2);
        setbtn = findViewById(R.id.setbtn);

        bouton_explicite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity2.class);
                intent.putExtra("nom", "ouahline");
                intent.putExtra("prénom", "omaima");
                startActivity(intent);
            }
        });

        bouton_implicite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.inpt.ac.ma"));
                startActivity(intent);
            }
        });

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
    }


}