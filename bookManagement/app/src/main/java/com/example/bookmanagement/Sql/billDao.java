  package com.example.bookmanagement.Sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bookmanagement.model.bill;


import java.util.ArrayList;
import java.util.List;

public class billDao {

    private Sqlite sqlite;

    public billDao(Sqlite sqlite) {
        this.sqlite = sqlite;
    }

    public boolean addBill(bill bill){
        SQLiteDatabase sqLiteDatabase = sqlite.getWritableDatabase();
        // ghép cặp giá trị vào tên cột 2
        ContentValues contentValues = new ContentValues();
        contentValues.put("idBill",bill.getIdBill());
        contentValues.put("date",bill.getDate());
        contentValues.put("time",bill.getTime());
        // truy vấn 3
        long kq = sqLiteDatabase.insert("bill", null, contentValues);

        if (kq > 0){
            return true;
        } else{
            return false;
        }
    }

    public boolean updateBill(bill bill){
        SQLiteDatabase sqLiteDatabase = sqlite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idBill",bill.getIdBill());
        contentValues.put("date",bill.getDate());
        contentValues.put("time",bill.getTime());
        // truy vấn 3
        long kq = sqLiteDatabase.update("bill", contentValues, "idBill ="+new int[]{bill.getIdBill()},null);

        if (kq > 0){
            return true;
        } else{
            return false;
        }
    }

    public boolean deBill(int id) {
        SQLiteDatabase sqLiteDatabase = sqlite.getWritableDatabase();
        // truy vấn 3
        long kq = sqLiteDatabase.delete("bill", "idBill =" + new int[]{id}, null);

        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<bill> getAllUBill() {
        List<bill> list = new ArrayList<>();
        String sql = "SELECT * FROM bill";
        Cursor cursor = sqlite.getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int ID = cursor.getInt(0);
                String date = cursor.getString(1);
                String time = cursor.getString(2);

                bill bill= new bill();
                bill.setIdBill(ID);
                bill.setDate(date);
                bill.setTime(time);

                list.add(bill);
                cursor.moveToNext();
            }
        }
        return list;
    }

    public List<bill> timKiem(String datetk) {
        List<bill> list = new ArrayList<>();
        String sql = "SELECT * FROM bill WHERE date LIKE '%" + datetk + "%'";
        Cursor cursor = sqlite.getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int ID = cursor.getInt(0);
                String date = cursor.getString(1);
                String time = cursor.getString(2);

                bill bill= new bill();
                bill.setIdBill(ID);
                bill.setDate(date);
                bill.setTime(time);

                list.add(bill);
                cursor.moveToNext();
            }
        }

        return list;
    }
}
