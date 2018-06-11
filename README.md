# Android-app-using-SQLite
Android App which uses SQLite Database to store and retrieve data.

<h2>Screenshots</h2>

<img src = "https://github.com/lakshay1296/Android-app-using-SQLite/blob/master/Screenshot_1528349407.png" height = "520" width = "300" />  <img src = "https://github.com/lakshay1296/Android-app-using-SQLite/blob/master/Screenshot_1528349424.png" height = "520" width = "300" />

<h2>Wearable Screenshots</h2>

<img src = "https://github.com/lakshay1296/Android-app-using-SQLite/blob/master/WearSS.png" height = "240" width = "240" /> <img src = "https://github.com/lakshay1296/Android-app-using-SQLite/blob/master/Noti.gif" height = "240" width = "240" />


<h2><b>Features</b></h2>

1. Add new data

2. Update existing data

3. Update single, multiple or all the fields present in the database

4. Delete data

5. View data

6. Automatic Date and Time insertion

7. Easy to implement and make changes

8. Android OS and Wear OS Notifications with vibration and sound.

<h2><b>How to use</b></h2>

1. Just copy the code and classes to your project <b>or</b>

2. Import project to android studio

<h2><b>How to make changes in table</b></h2>

If you want to add or remove columns from table:

1. You need to add or remove column name from the "create table" query.

2. Update the version of the database which can be found in <b>DatabaseHelper.java</b> 

```
public DatabaseHelper(Context context) {
        super( context, DATABASE_NAME, null, 2 );

    }
```
Here, integer 2 mentions the version.

3. After adding columns update AddData() or updateData() methods so that they can accept values for the extra columns.

```
 public boolean instertData(String name, String surname, String marks, String date){
        // Rest of the code
        }
```

4. Add new column in <b>ContentValues</b> for inserting or updating the values in database.
```
contentValues.put( "Column name which is defined in database", "value of the method which you need to insert in the column of DB, For eg. name" );
```
That's it! It's a very simple app which helps you in understanding the concept of implementing SQLite Database in an Android application.

<h2>License</h2>

You are free to use this app for your project.
