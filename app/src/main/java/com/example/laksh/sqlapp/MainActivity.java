package com.example.laksh.sqlapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    //Initializing fields
    DatabaseHelper myDB;
    EditText edit_name, edit_surname, edit_marks, edit_id;
    Button addData, viewData, updateData, deleteData;
    String name, surname, marks, id;
    boolean isUpdated;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        //Initialize Database
        myDB = new DatabaseHelper( this );

        //Initialize EditText
        edit_name = findViewById( R.id.name );
        edit_surname = findViewById( R.id.surname );
        edit_marks = findViewById( R.id.marks );
        edit_id = findViewById( R.id.id );

        // TextView
        textView = findViewById( R.id.textView5 );
        textView.setText( "Important Notes:\n1. Both Date and Time will be stored automatically on the time of insertion.\n2. Existing Date and Time will be updated when you update your data." );

        //Initialize Button
        addData = findViewById( R.id.button );
        viewData = findViewById( R.id.button2 );
        updateData = findViewById( R.id.button3 );
        deleteData = findViewById( R.id.button4 );

        //Call Methods
        AddData();
        viewData();
        updateData();
        deleteData();
    }

    //Adding or inserting data to database
    public void AddData(){

        addData.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = edit_name.getText().toString();
                surname = edit_surname.getText().toString();
                marks = edit_marks.getText().toString();

                //Current Date and Time
                Date date1 = new Date();
                String date = DateFormat.getDateTimeInstance(). format(date1);

                boolean isInserted = myDB.instertData( name, surname, marks, date);

                if(isInserted == true){
                    Toast.makeText( MainActivity.this, "Data is inserted", Toast.LENGTH_SHORT ).show();
                }
                else
                    Toast.makeText( MainActivity.this, "Data is not inserted", Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    //For viewing data in database
    public void viewData(){

        viewData.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDB.getData();

                if (res.getCount() == 0){
                    showMessage("Error", "Data not found!");
                }

                else{
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()){
                        buffer.append( "ID: " + res.getString( 0 ) + "\n" );
                        buffer.append( "Name: " + res.getString( 1 ) + "\n" );
                        buffer.append( "Surname: " + res.getString( 2 ) + "\n" );
                        buffer.append( "Marks: " + res.getString( 3 ) + "\n" );
                        buffer.append( "Insertion/Updation Date:\n" + res.getString( 4 ) + "\n\n" );
                    }

                    showMessage( "Data", buffer.toString() );

                }
            }
        } );
    }

    //For updating existing data in database
    public void updateData(){

        updateData.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id = edit_id.getText().toString();
                name = edit_name.getText().toString();
                surname = edit_surname.getText().toString();
                marks = edit_marks.getText().toString();

                //Current Date and Time
                Date date1 = new Date();
                String date = DateFormat.getDateTimeInstance(). format(date1);

                boolean isUpdated = myDB.updateData( id, name, surname, marks, date );

                if (isUpdated == true){
                    showMessage( "Update", "Your data has been successfully updated!" );
                }
                else
                    showMessage( "Update failed", "Cannot Update your data :(" );
            }
        } );
    }

    //For deleting data in the database
    public void deleteData(){

        deleteData.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id = edit_id.getText().toString();

                Integer res = myDB.deleteData( id );
                if(res > 0){
                    Toast.makeText( getApplicationContext(), "Row effected", Toast.LENGTH_SHORT ).show();
                }
                else{
                    Toast.makeText( getApplicationContext(), "Row not effected", Toast.LENGTH_SHORT ).show();
                }

            }
        } );
    }

    //Method for creating AlertDialog box
    private void showMessage(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setCancelable( true );
        builder.setTitle( title );
        builder.setMessage( message );
        builder.show();
    }
}
