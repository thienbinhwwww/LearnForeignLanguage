package com.example.baithi;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Sqlite extends SQLiteOpenHelper {
    public Sqlite(@Nullable Context context) {
        super(context, "datavt.db",null,1);
    }

    public void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String khachHang = "CREATE TABLE khachHang (" +
                "ID TEXT NOT NULL, " +
                "ten TEXT ," +
                "kinhDo BLOB," +
                "viDo BLOB," +
                "PRIMARY KEY(ID)) ";
        db.execSQL(khachHang);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
