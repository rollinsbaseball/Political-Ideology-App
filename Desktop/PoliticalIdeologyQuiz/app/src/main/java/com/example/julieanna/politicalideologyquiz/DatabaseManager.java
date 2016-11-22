package com.example.julieanna.politicalideologyquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by julieanna on 11/15/16.
 */

public class DatabaseManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "QuizHistory";
    public static final int DATABASE_VERSION = 1;
    public static String HISTORY_TABLE = "tblHistory";
    public static final String ID = "id";
    public static final String DATE = "date";
    public static final String IMAGE = "userimage";
    public static final String NAME = "name";
    public static final String RESULT = "result";
    public static final String TWOPARTY = "twoparty";
    private Context appContext;

    public DatabaseManager( Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        appContext = context;
    }

    public void onCreate( SQLiteDatabase db) {
        // create table statement
        Log.i( "Quiz", "onCreate called");
        String sqlCreate = "create table" + HISTORY_TABLE + " ( "
                + ID + " integer primary key autoincrement, "
                + DATE + " text, "
                + IMAGE + " blob, "
                + NAME + " text, "
                + RESULT + " text, "
                + TWOPARTY + " text "
                + ")";
        try {
            db.execSQL(sqlCreate);
        }
        catch ( SQLException se ) {
            Toast.makeText(appContext, se.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public long insert(String date, String image, String name, String result, String twoparty) {
        long newId = -1;
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(DATE, date);
            values.put(IMAGE, image);
            values.put(NAME, name);
            values.put(RESULT, result);
            values.put(TWOPARTY, twoparty);

            newId = db.insert( HISTORY_TABLE, null, values );
            db.close();
        }
        catch (SQLException se) {
            Toast.makeText( appContext, se.getMessage(), Toast.LENGTH_LONG).show();
        }
        return newId;
    }

    public ArrayList<String> selectAll() {

        ArrayList<String> historyList = new ArrayList<String>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            String query = "Select * from " + HISTORY_TABLE;
            Cursor cursor = db.rawQuery(query, null);

            cursor.moveToFirst();
            while( !cursor.isAfterLast()) {
                String oneRecord = "";

                for (int i=0; i<cursor.getColumnCount(); i++) {
                    oneRecord += cursor.getString(i) + " ";
                }
                historyList.add(oneRecord);
                cursor.moveToNext();
            }
        }
        catch (SQLException se) {
            Toast.makeText(appContext, se.getMessage(), Toast.LENGTH_LONG).show();
        }
        return historyList;
    }
    public ArrayList<String> selectByColumn( String columnName, String columnValue ) {

        ArrayList<String> historyList = new ArrayList<String>( );
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query( HISTORY_TABLE, null, columnName + "=?",
                    new String[] {columnValue}, null, null, columnName );

            cursor.moveToFirst();
            while ( !cursor.isAfterLast()) {
                String oneRecord = "";

                for ( int i = 1; i < cursor.getColumnCount(); i++) {
                    oneRecord += cursor.getString(i) + " ";
                }
                historyList.add( oneRecord);
                cursor.moveToNext();
            }
        }
        catch ( SQLException se ) {
            Toast.makeText( appContext, se.getMessage( ), Toast.LENGTH_LONG).show();
        }

        return historyList;
    }

    public ArrayAdapter<String> fillAutoCompleteTextFields(Context context, String column) {
        ArrayAdapter<String> adapter = null;

        try {
            SQLiteDatabase db = this.getReadableDatabase();

            // select distinct values in column
            Cursor cursor = db.query(true, HISTORY_TABLE, new String[]{column},
                    null, null, null, null, column, null);

            int numberOfRecords = cursor.getCount();

            if (numberOfRecords > 0) {
                cursor.moveToFirst();

                String[] autoTextOptions = new String[numberOfRecords];
                for (int i = 0; i < numberOfRecords; i++) {
                    autoTextOptions[i] = cursor.getString(cursor.getColumnIndex(column));
                    cursor.moveToNext();
                }

                adapter = new ArrayAdapter<String>(context,
                        android.R.layout.simple_dropdown_item_1line,
                        autoTextOptions);
                db.close();
            }
        }

        catch ( SQLException se ) {
            Toast.makeText( appContext, se.getMessage( ), Toast.LENGTH_LONG).show();
        }
        return adapter;


    }

    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText( appContext, "onUpgrade called", Toast.LENGTH_LONG).show( );
    }

}


