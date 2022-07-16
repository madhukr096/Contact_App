package com.example.contact_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "contact.db", null, 1);
    }
    
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table user(email TEXT primary key , password TEXT, secret TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists user");

    }
    public Boolean insertuserdata (String email,String password , String secret) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("secret", secret);
        long result = DB.insert("user", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
        public Boolean checkemail(String email)
        {
            SQLiteDatabase DB = this.getWritableDatabase();
            Cursor cursor =DB.rawQuery("select * from user where email=?",new String[]{email});
            if (cursor.getCount()>0)
                return  true;
            else
                return false;
        }
        public Boolean checkemailpassword(String email,String password){
            SQLiteDatabase DB =this.getWritableDatabase();
            Cursor cursor =DB.rawQuery("select * from user where email=? and password=?",new String[]{email,password});
            if (cursor.getCount()>0)
                return true;
            else
                return false;

        }
    public Boolean checkpasswordUpdate(String email,String password){
        SQLiteDatabase DB =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Password",password);
        long result=DB.update("user",contentValues,"email=?",new String[]{email});
        if (result==-1)
            return false;
        else
            return true;

    }

}

