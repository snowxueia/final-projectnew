package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataBaseHelper extends SQLiteOpenHelper {

    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/com.example.finalproject/databases/";
    private static String DB_NAME = "gre.db";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

     /**     * Constructor     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.     * @param context     */
    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, 1);

        this.myContext = context;

        boolean dbexist = checkDataBase();
        if(dbexist) {
            openDataBase();
        } else {
            Log.e("Database", "Database doesn't exist");
            createDataBase();
            openDataBase();
        }

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //XUEXIA ADDS THE CODE HERE
        db.execSQL("Create table user(email text primary key, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Xuexia adds the code here
        db.execSQL("drop table if exists user");
    }

    public boolean insert(String email, String password){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        long ins = db.insert("user",null,contentValues);

        if(ins == -1)
            return false;
        else
            return true;

    }

    public  Boolean chkemail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?",new String[]{email});
        if(cursor.getCount()>0)
            return false;
        else
            return true;




    }
    public Boolean emailpassword(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=? and password=?",new String[]{email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    // Add your public helper methods to access and get content from the database.
    // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
    // to you to create adapters for your views.


    /**     * Creates a empty database on the system and rewrites it with your own database.     * */
    public void createDataBase() {
        boolean dbExist = checkDataBase();
        if(!dbExist){
            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();
            try
            {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }
    /**     * Check if the database already exist to avoid re-copying the file each time you open the application.     * @return true if it exists, false if it doesn't     */
    private boolean checkDataBase() {
//        SQLiteDatabase checkDB = null;
        boolean checkDB = false;
        try
        {
            String myPath = DB_PATH + DB_NAME;
            File dbFile = new File(myPath);
//            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
            checkDB = dbFile.exists();
        }catch (SQLiteException e)
        {
            //database does't exist yet.
            Log.e("Database", "Database doesn't exist");
        }
//        if(checkDB != null)
//        {
//            checkDB.close();
//        }
//        return checkDB != null ? true : false;
        return checkDB;
    }
    /**     * Copies your database from your local assets-folder to the just created empty database in the     * system folder, from where it can be accessed and handled.     * This is done by transfering bytestream.     * */
    private void copyDataBase() throws IOException
    {
        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;
        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);
        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0) {
            myOutput.write(buffer, 0, length);
        }
        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }
    public void openDataBase() throws SQLException {
        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close()
    {
        if(myDataBase != null)
            myDataBase.close();
        SQLiteDatabase.releaseMemory();
        super.close();
    }

    public String[][] getAppCategoryDetail() {

        final String TABLE_NAME = "vocab";

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery, null);
        String[][] data      = new String[4][52];
        cursor.moveToFirst ();
        for (int i = 0; i < 52; i++) {
            for(int j = 0; j < 4; j++) {
                data[j][i] = cursor.getString(j);
            }
            cursor.moveToNext();
        }


        /*
        if (cursor.moveToFirst()) {
            do {
                // get the data into array, or class variable
            } while (cursor.moveToNext());
        }
         */

        cursor.close();
        return data;
    }







}