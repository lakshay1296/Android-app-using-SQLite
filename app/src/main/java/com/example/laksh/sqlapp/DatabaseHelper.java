package com.example.laksh.sqlapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by laksh on 6/5/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //Initialize all the fields needed for database
    public static final String DATABASE_NAME = "Students.db";
    public static final String TABLE_NAME = "student_data";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "Surname";
    public static final String COL_4 = "Marks";
    public static final String COL_5 = "Date";
    public static final String LBR = "(";
    public static final String RBR = ")";
    public static final String COM = ",";

    //Just pass context of the app to make it simpler
    public DatabaseHelper(Context context) {
        super( context, DATABASE_NAME, null, 2 );

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Creating table

        db.execSQL( "create table " + TABLE_NAME + LBR + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT" + COM +
                COL_2 + " TEXT" + COM + COL_3 + " TEXT" + COM + COL_4 + " INTEGER" + COM + COL_5 + " INTEGER" +RBR );

        // Another way of writing the CREATE TABLE query
       /* db.execSQL( "create table student_data (ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Surname TEXT," +
                "Marks INTEGER, Date TEXT)" );*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        //Dropping old table
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate( db );

    }

    //Insert data in database
    public boolean instertData(String name, String surname, String marks, String date){

        //Get the instance of SQL Database which we have created
        SQLiteDatabase db = getWritableDatabase();

        //To pass all the values in database
        ContentValues contentValues = new ContentValues();
        contentValues.put( COL_2, name );
        contentValues.put( COL_3, surname );
        contentValues.put( COL_4, marks );
        contentValues.put( COL_5, date);

        long result = db.insert( TABLE_NAME, null, contentValues );

        if(result == -1)
            return false;
        else
            return true;
    }

    //Cursor class is used to move around in the database
    public Cursor getData(){

        //Get the data from database
        SQLiteDatabase db = getWritableDatabase();
        Cursor res = db.rawQuery( "select * from " + TABLE_NAME, null );
        return res;
    }

    //Update fields of database using ID (Unique identifier)
    public boolean updateData(String id, String name, String surname, String marks, String date){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues(  );
        // When you want to update only name field
        if(surname.equals( "" ) && marks.equals( "" )){
            contentValues.put( COL_1, id );
            contentValues.put( COL_2, name );
            contentValues.put( COL_5, date);
        }
        // When you want to update only surname field
        if(name.equals( "" ) && marks.equals( "" )){
            contentValues.put( COL_1, id );
            contentValues.put( COL_3, surname );
            contentValues.put( COL_5, date);
        }
        // When you want to update only marks field
        if(name.equals( "" ) && surname.equals( "" )){
            contentValues.put( COL_1, id );
            contentValues.put( COL_4, marks );
            contentValues.put( COL_5, date);
        }
        // When you want to update name and surname field
        if(marks.equals( "" ) && !name.isEmpty() && !surname.isEmpty()){
            contentValues.put( COL_1, id );
            contentValues.put( COL_2, name );
            contentValues.put( COL_3, surname );
            contentValues.put( COL_5, date);
        }
        // When you want to update marks and surname field
        if(name.isEmpty() && !marks.isEmpty() && !surname.isEmpty()){
            contentValues.put( COL_1, id );
            contentValues.put( COL_3, surname );
            contentValues.put( COL_4, marks );
            contentValues.put( COL_5, date);
        }
        // When you want to update name and marks field
        if(surname.isEmpty() && !name.isEmpty() && !marks.isEmpty()){
            contentValues.put( COL_1, id );
            contentValues.put( COL_2, name );
            contentValues.put( COL_4, marks );
            contentValues.put( COL_5, date);
        }
        // When you want to update every data field
        if(!id.isEmpty() && !name.isEmpty() && !surname.isEmpty() && !marks.isEmpty()){
            contentValues.put( COL_1, id );
            contentValues.put( COL_2, name );
            contentValues.put( COL_3, surname );
            contentValues.put( COL_4, marks );
            contentValues.put( COL_5, date);
        }

        // UPDATE query
        db.update( TABLE_NAME, contentValues, "ID = ?", new String[]{id} );
        return true;
    }

    //Delete data from the databse using ID (Primary Key)
    public Integer deleteData(String id){

        SQLiteDatabase db = getWritableDatabase();
        return db.delete( TABLE_NAME, "ID = ?", new String [] {id} );
    }
}
