package com.example.applicationouahline.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.applicationouahline.R;
import com.example.applicationouahline.data.DataBaseHandler;
import com.example.applicationouahline.business.Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private Button saveButton;
    private EditText babyItem;
    private EditText itemQuantity;
    private EditText itemColor;
    private EditText itemSize;
    private DataBaseHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DataBaseHandler(MainActivity.this);
        byPassActivity();
        List<Item>items = new ArrayList<>();
        items =  db.getAllItems();

        for (Item item : items){
            Log.d("TAG", "onCreate: "+item.getItemName()+" "+item.getItemColor()+" "+item.getItemSize()+" "+item.getDateItemAdded());
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatPopupDialog();
                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //   .setAction("Action", null).show();
            }
        });
    }

    private void byPassActivity() {
        if(db.getItemCount() > 0){
            startActivity(new Intent(MainActivity.this,ItemListActivity.class));
            finish();
        }
    }

    private void creatPopupDialog() {
        builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup,null);
        saveButton = view.findViewById(R.id.saveBotton);
        babyItem = view.findViewById(R.id.babyItem);
        itemQuantity = view.findViewById(R.id.itemQuantity);
        itemColor = view.findViewById(R.id.colorItem);
        itemSize = view.findViewById(R.id.sizeItem);
        builder.setView(view);
        dialog = builder.create();
        dialog.show();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("save", "onClick: ");
                if(!babyItem.getText().toString().isEmpty()
                        && !itemColor.getText().toString().isEmpty()
                        && !itemSize.getText().toString().isEmpty()
                        && !itemQuantity.getText().toString().isEmpty()){

                    saveItem(v);
                }else{
                    Snackbar.make(v,"Empty Fields not Allowed",Snackbar.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    private void saveItem(View view) {
        Item item = new Item();
        String newItem = babyItem.getText().toString().trim();
        String newColor = itemColor.getText().toString().trim();
        int newSize = Integer.parseInt(itemSize.getText().toString().trim());
        int newQuantity = Integer.parseInt(itemSize.getText().toString().trim());


        item.setItemName(newItem);
        item.setItemSize(newSize);
        item.setItemQuantity(newQuantity);
        item.setItemColor(newColor);


        db.AddItem(item);
        Snackbar.make(view,"Item Saved",Snackbar.LENGTH_SHORT)
                .show();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                startActivity(new Intent(MainActivity.this,ItemListActivity.class));
            }
        },1200);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}