package com.example.learnforeignlanguage.DAO;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.learnforeignlanguage.Database.Sqlite;
import com.example.learnforeignlanguage.mode.Custom;

public class CustomDao {
    private Sqlite sqlite;

    public CustomDao(Sqlite sqlite) {
        this.sqlite = sqlite;
    }

    public boolean addCustom(Custom custom){
        SQLiteDatabase sqLiteDatabase = sqlite.getWritableDatabase();
        // ghép cặp giá trị vào tên cột 2
        ContentValues contentValues = new ContentValues();


        long kq = sqLiteDatabase.insert("Custom", null, contentValues);

        if (kq > 0){
            return true;
        } else{
            return false;
        }
    }
}
