package com.example.applicationouahline;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;

public class InptisteProvider extends ContentProvider {
    public InptisteProvider() {
    }

    static  final String PROVIDER_NAME = "com.example.applicationouahline.InptisteProvider";
    static  final String URL = "content://" + PROVIDER_NAME + "/inptistes";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String _ID = "_id";
    static final String NOM = "nom";
    static final String NOTE = "note";

    private static HashMap<String, String> INPTISTES_PROJECTION_MAP;

    static final int INPTISTES = 1;
    static final int INPTISTE_ID = 2;

    static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "inptistes", INPTISTES);
        uriMatcher.addURI(PROVIDER_NAME, "inptistes/ #", INPTISTE_ID);
    }

    private SQLiteDatabase db;
    static final String DATABASE_NAME = "INPT";
    static final String INPTISTES_TABLE_NAME = "inptistes";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE =
            "CREATE TABLE " + INPTISTES_TABLE_NAME +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nom TEXT NOT NULL, " +
                    "note TEXT NOT NULL);";

    private class DatabaseHelper extends SQLiteOpenHelper{
        DatabaseHelper( Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            db.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+ INPTISTES_TABLE_NAME);
            onCreate(db);

        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)){
            case INPTISTES:
                count = db.delete(INPTISTES_TABLE_NAME, selection, selectionArgs);
                break;
            case INPTISTE_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete(INPTISTES_TABLE_NAME, _ID + " = " +id+
                        (!TextUtils.isEmpty(selection) ? "AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case INPTISTES:
                return "vnd.android.cursor.dir/vnd.example.inptistes";
            case INPTISTE_ID:
                return "vnd.android.cursor.item/vnd.example.inptistes";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowID = db.insert(INPTISTES_TABLE_NAME, "", values);
        if (rowID > 0){
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLException("Failed to add a record into "+uri);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        db = dbHelper.getWritableDatabase();
        return (db == null) ? false:true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(INPTISTES_TABLE_NAME);

        switch (uriMatcher.match(uri)){
            case INPTISTES:
                qb.setProjectionMap(INPTISTES_PROJECTION_MAP);
                break;
            case INPTISTE_ID:
                qb.appendWhere(_ID + "=" + uri.getPathSegments().get(1));
                break;
            default:
        }
        if (sortOrder == null || sortOrder == ""){
            sortOrder = NOM;
        }

        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)){
            case INPTISTES:
                count = db.update(INPTISTES_TABLE_NAME, values, selection, selectionArgs);
                break;
            case INPTISTE_ID:
                count = db.update(INPTISTES_TABLE_NAME, values,
                        _ID + " = " + uri.getPathSegments().get(1)
                        + (!TextUtils.isEmpty(selection) ? "AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;

    }
}