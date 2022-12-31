package com.example.applicationouahline;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle extras = getIntent().getExtras();
        Toast.makeText(getApplicationContext(), "nom et prénom: "+ extras.get("nom")+" "+extras.get("prénom"), Toast.LENGTH_LONG).show();

    }
}