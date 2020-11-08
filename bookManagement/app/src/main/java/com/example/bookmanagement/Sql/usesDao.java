package com.example.bookmanagement.Sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bookmanagement.model.uses;

import java.util.ArrayList;
import java.util.List;

public class usesDao {

    private Sqlite sqlite;

    public usesDao(Sqlite sqlite) {
        this.sqlite = sqlite;
    }



    public boolean addUser(uses uses){
        SQLiteDatabase sqLiteDatabase = sqlite.getWritableDatabase();
        // ghép cặp giá trị vào tên cột 2
        ContentValues contentValues = new ContentValues();
        contentValues.put("idUses",uses.getIdUses());
        contentValues.put("usesName",uses.getUsesName());
        contentValues.put("password",uses.getPassword());
        contentValues.put("email",uses.getGmail());

        // truy vấn 3
        long kq = sqLiteDatabase.insert("uses", null, contentValues);

        if (kq > 0){
            return true;
        } else{
            return false;
        }
    }

    public boolean updateUses(uses uses){
        SQLiteDatabase sqLiteDatabase = sqlite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idUses",uses.getIdUses());
        contentValues.put("usesName",uses.getUsesName());
        contentValues.put("password",uses.getPassword());
        contentValues.put("email",uses.getGmail());
        // truy vấn 3
        long kq = sqLiteDatabase.update("uses", contentValues, "idUses ="+uses.getIdUses(),null);

        if (kq > 0){
            return true;
        } else{
            return false;
        }
    }

    public boolean deUses(int id) {
        SQLiteDatabase sqLiteDatabase = sqlite.getWritableDatabase();
        // truy vấn 3
        long kq = sqLiteDatabase.delete("uses", "idUses =" + id, null);

        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<uses> getAllUsers() {
        List<uses> list = new ArrayList<>();
        String sql = "SELECT * FROM uses";
        Cursor cursor = sqlite.getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int ID = cursor.getInt(0);
                String Name = cursor.getString(1);
                String pass = cursor.getString(2);
                String email = cursor.getString(3);

                uses uses = new uses();
                uses.setIdUses(ID);
                uses.setUsesName(Name);
                uses.setPassword(pass);
                uses.setGmail(email);

                list.add(uses);
                cursor.moveToNext();
            }
        }
        return list;
    }

    public List<uses> timKiemUsername(String TimUsername) {
        List<uses> nguoiDungList = new ArrayList<>();
        String sql = "SELECT * FROM uses WHERE usesName LIKE '%" + TimUsername + "%'";
        Cursor cursor = sqlite.getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int ID = cursor.getInt(0);
                String Name = cursor.getString(1);
                String pass = cursor.getString(2);
                String email = cursor.getString(3);

                uses uses = new uses();
                uses.setIdUses(ID);
                uses.setUsesName(Name);
                uses.setPassword(pass);
                uses.setGmail(email);

                nguoiDungList.add(uses);
                cursor.moveToNext();
            }
        }

        return nguoiDungList;
    }
}
