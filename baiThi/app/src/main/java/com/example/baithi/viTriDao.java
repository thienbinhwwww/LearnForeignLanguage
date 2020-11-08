package com.example.baithi;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class viTriDao {
    Sqlite sqlite;

    public viTriDao(Sqlite sqlite) {
        this.sqlite = sqlite;
    }

    public boolean addVT(ViTri viTri){
        SQLiteDatabase sqLiteDatabase = sqlite.getWritableDatabase();
        // ghép cặp giá trị vào tên cột 2
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",viTri.getID());
        contentValues.put("ten",viTri.getTen());
        contentValues.put("kinhDo",viTri.getKinhDo());
        contentValues.put("viDo",viTri.getViDo());
        // truy vấn 3
        long kq = sqLiteDatabase.insert("khachHang", null, contentValues);

        if (kq > 0){
            return true;
        } else{
            return false;
        }
    }

    public List<ViTri> getVT() {
        List<ViTri> list = new ArrayList<>();
        String sql = "SELECT * FROM khachHang";
        Cursor cursor = sqlite.getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                String ID = cursor.getString(0);
                String ten = cursor.getString(1);
                Double kinhDo = cursor.getDouble(2);
                Double viDo = cursor.getDouble(3);

                ViTri viTri= new ViTri();
                viTri.setID(ID);
                viTri.setTen(ten);
                viTri.setKinhDo(kinhDo);
                viTri.setViDo(viDo);

                list.add(viTri);
                cursor.moveToNext();
            }
        }
        return list;
    }
    public boolean deBill(String  id) {
        SQLiteDatabase sqLiteDatabase = sqlite.getWritableDatabase();
        // truy vấn 3
        long kq = sqLiteDatabase.delete("khachHang", "ID =" + id, null);

        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }
}
