package com.example.bookmanagement.Sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bookmanagement.model.category;

import java.util.ArrayList;
import java.util.List;

public class categoryDao {

    Sqlite sqlite;

    public categoryDao(Sqlite sqlite) {
        this.sqlite = sqlite;
    }

    public boolean adCategory(category category){
        SQLiteDatabase sqLiteDatabase = sqlite.getWritableDatabase();
        // ghép cặp giá trị vào tên cột 2
        ContentValues contentValues = new ContentValues();
        contentValues.put("idCategory",category.getIdCategory());
        contentValues.put("category",category.getCategory());

        // truy vấn 3
        long kq = sqLiteDatabase.insert("category", null, contentValues);

        if (kq > 0){
            return true;
        } else{
            return false;
        }
    }

    public boolean updatCategory(category category){
        SQLiteDatabase sqLiteDatabase = sqlite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idCategory",category.getIdCategory());
        contentValues.put("category",category.getCategory());
        // truy vấn 3
        long kq = sqLiteDatabase.update("category", contentValues, "idCategory ="+new int[]{category.getIdCategory()},null);

        if (kq > 0){
            return true;
        } else{
            return false;
        }
    }

    public boolean dCategory(int id) {
        SQLiteDatabase sqLiteDatabase = sqlite.getWritableDatabase();
        // truy vấn 3
        long kq = sqLiteDatabase.delete("category", "idCategory =" + id, null);

        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<category> getAllCategory() {
        List<category> list = new ArrayList<>();
        String sql = "SELECT * FROM category";
        Cursor cursor = sqlite.getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int ID = cursor.getInt(0);
                String categoryName = cursor.getString(1);

                category category = new category();
                category.setIdCategory(ID);
                category.setCategory(categoryName);

                list.add(category);
                cursor.moveToNext();
            }
        }
        return list;
    }
}
