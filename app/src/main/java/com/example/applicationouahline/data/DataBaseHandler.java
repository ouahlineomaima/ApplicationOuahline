package com.example.applicationouahline.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.applicationouahline.business.Item;
import com.example.applicationouahline.util.Constants;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {
    private final Context context;

    public DataBaseHandler(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NEED_TABLE = "CREATE TABLE "+Constants.TABLE_NAME+"("
                +Constants.KEY_ID+" INTEGER PRIMARY KEY,"
                +Constants.DB_NAME+" TEXT,"
                +Constants.KEY_COLOR + " TEXT,"
                +Constants.KEY_DATE_NAME+ " LONG,"
                +Constants.KEY_GROCERY_ITEM+ " INTEGER,"
                +Constants.KEY_QTY_ITEM +" TEXT,"
                +Constants.KEY_SIZE+ " INTEGER)";

        db.execSQL(CREATE_NEED_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Constants.TABLE_NAME);

        onCreate(db);
    }

    //CRUD
    public void AddItem(Item item){
        SQLiteDatabase db  = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.KEY_GROCERY_ITEM,item.getItemName());
        values.put(Constants.KEY_COLOR,item.getItemColor());
        values.put(Constants.KEY_QTY_ITEM,item.getItemQuantity());
        values.put(Constants.KEY_DATE_NAME,java.lang.System.currentTimeMillis());
        values.put(Constants.KEY_SIZE,item.getItemSize());
        //insert to row
        db.insert(Constants.TABLE_NAME,null,values);
        Log.d("DBHandler", "AddItem: ");

        db.close();
    }
    public Item getItem(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Constants.TABLE_NAME,new String[]{
                        Constants.KEY_ID,
                        Constants.KEY_GROCERY_ITEM,
                        Constants.KEY_QTY_ITEM,
                        Constants.KEY_SIZE,
                        Constants.KEY_COLOR,
                        Constants.KEY_DATE_NAME },
                Constants.KEY_ID+"=?",
                new String[]{String.valueOf(id)},
                null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();
        Item item = new Item();
        if(cursor!=null) {
            item.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
            item.setItemColor(cursor.getString(cursor.getColumnIndex(Constants.KEY_COLOR)));
            item.setItemQuantity(cursor.getInt(cursor.getColumnIndex(Constants.KEY_QTY_ITEM)));
            item.setItemSize(cursor.getInt(cursor.getColumnIndex(Constants.KEY_SIZE)));
            item.setItemName(cursor.getString(cursor.getColumnIndex(Constants.KEY_GROCERY_ITEM)));

            //Convert Time Stamp;
            DateFormat dateFormat = DateFormat.getInstance();
            String formattedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_NAME))).getTime());
            item.setDateItemAdded(formattedDate);
        }
        return item;
    }
    public List<Item> getAllItems(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Item>itemList = new ArrayList<>();
        String SelectAll = "SELECT * FROM "+Constants.TABLE_NAME;
        Cursor cursor = db.query(Constants.TABLE_NAME,new String[]{
                        Constants.KEY_ID,
                        Constants.KEY_GROCERY_ITEM,
                        Constants.KEY_QTY_ITEM,
                        Constants.KEY_SIZE,
                        Constants.KEY_COLOR,
                        Constants.KEY_DATE_NAME },null,null, null,null,
                Constants.KEY_DATE_NAME+" DESC");

        //Loop Through Data
        if(cursor.moveToFirst()){
            do{
                Item item = new Item();
                item.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
                item.setItemColor(cursor.getString(cursor.getColumnIndex(Constants.KEY_COLOR)));
                item.setItemQuantity(cursor.getInt(cursor.getColumnIndex(Constants.KEY_QTY_ITEM)));
                item.setItemSize(cursor.getInt(cursor.getColumnIndex(Constants.KEY_SIZE)));
                item.setItemName(cursor.getString(cursor.getColumnIndex(Constants.KEY_GROCERY_ITEM)));

                //Convert Time Stamp;
                DateFormat dateFormat = DateFormat.getInstance();
                String formattedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_NAME))).getTime());
                item.setDateItemAdded(formattedDate);
                itemList.add(item);
            }while (cursor.moveToNext());
        }
        return itemList;
    }

    public int updateItem(Item item){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_GROCERY_ITEM,item.getItemName());
        values.put(Constants.KEY_COLOR,item.getItemColor());
        values.put(Constants.KEY_QTY_ITEM,item.getItemQuantity());
        values.put(Constants.KEY_DATE_NAME,java.lang.System.currentTimeMillis());
        values.put(Constants.KEY_SIZE,item.getItemSize());


        return db.update(Constants.TABLE_NAME,values, Constants.KEY_ID+"=?",
                new String[]{String.valueOf(item.getId())});
    }
    public void deleteItem(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_NAME,Constants.KEY_ID+"=?",
                new String[]{String.valueOf(id)});
        db.close();
    }
    public int getItemCount(){
        String countQuery= "SELECT * FROM "+Constants.TABLE_NAME;
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);

        return cursor.getCount();
    }
}