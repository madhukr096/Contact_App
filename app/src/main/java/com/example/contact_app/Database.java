package com.example.contact_app;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database  extends SQLiteOpenHelper {
    public Database(Context context) {
        super(context, "contact1.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase DB1) {
        DB1.execSQL("create Table contact(name TEXT  , phone TEXT primary key, email TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB1, int i, int i1) {
        DB1.execSQL("drop Table if exists contact");

    }
    public Boolean insertuserdata (String name,String phone , String email) {
        SQLiteDatabase DB1 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        long result = DB1.insert("contact", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public Cursor viewdata () {
        SQLiteDatabase DB1 = this.getWritableDatabase();
        Cursor cursor=DB1.rawQuery("select * from contact", null);
        return cursor;
    }
    public Integer deleteData(){
        SQLiteDatabase DB1 = this.getWritableDatabase();
        return DB1.delete("contact",null,null);
    }
}
