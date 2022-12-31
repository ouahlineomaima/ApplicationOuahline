package com.example.applicationouahline;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText nameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameField = findViewById(R.id.nom);

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

}