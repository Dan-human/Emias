package com.Dan_human.Emias;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {

    private static final String DBNAME = "Login.db";


    public DBHelper(@Nullable Context context)
    {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(id INTEGER primary key AUTOINCREMENT, username TEXT, password TEXT )" );
        MyDB.execSQL("create Table doctors(id INTEGER primary key AUTOINCREMENT,name_surname TEXT , type TEXT )" );
        MyDB.execSQL("create Table enroll(id INTEGER primary key AUTOINCREMENT, type TEXT, name_surname TEXT, date TEXT, user TEXT )" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertDataUser(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Boolean insertDataDoctor(String name_surname, String type){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name_surname", name_surname);
        contentValues.put("type", type);
        long result = MyDB.insert("doctors", null, contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Boolean insertDataEnroll(String name_surname, String type, String date, String user){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name_surname", name_surname);
        contentValues.put("type", type);
        contentValues.put("date", date);
        contentValues.put("user", user);

        long result = MyDB.insert("enroll", null, contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Boolean checkusername (String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor =  MyDB.rawQuery("Select * from users where username = ?", new String[] {username});
        if (cursor.getCount()>0)
            return true;
            else
                return false;
    }hiв

    public Boolean checkusernamepassword ( String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?",new String [] {username, password} );
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Cursor getUserEnrolls(String user, String parametr){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        return MyDB.query("enroll", new String[]{parametr},"user = ?", new String[]{user}, null, null, null );
    }

        public Cursor getDoctorType(String type){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        return MyDB.query("doctors", new String[]{"name_surname"},"type = ?", new String[]{type}, null, null, null );
    }

    public void deleteDoctor(String name_surname){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.delete("doctors", "name_surname = ?", new String[]{name_surname});
        MyDB.delete("enroll", "name_surname = ?", new String[]{name_surname});
    }
}
