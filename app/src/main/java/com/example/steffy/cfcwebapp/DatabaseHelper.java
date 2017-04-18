package com.example.steffy.cfcwebapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="CFC.db";
    public static final String TABLE_NAME="Details";
    public static final String col_1="NAME";
    public static final String col_2="EMAIL";
    public static final String col_3="PHONENO";
    public static final String col_4="MSG";

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table"+TABLE_NAME+"(NAME TEXT,EMAIL TEXT,PHONENO INTEGER,MSG TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertdata(String name,String mail,String cellno,String msg) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int phno=Integer.parseInt(cellno);
        contentValues.put(col_1, name);
        contentValues.put(col_2, mail);
        contentValues.put(col_3, phno);
        contentValues.put(col_4, msg);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){

        SQLiteDatabase db =this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;

    }
}
