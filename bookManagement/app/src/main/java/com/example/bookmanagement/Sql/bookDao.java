package com.example.bookmanagement.Sql;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bookmanagement.model.book;

import java.util.ArrayList;
import java.util.List;

public class bookDao {
    Sqlite sqlite;

    public bookDao(Sqlite sqlite) {
        this.sqlite = sqlite;
    }

    public boolean addBook(book book){
        SQLiteDatabase sqLiteDatabase = sqlite.getWritableDatabase();
        // ghép cặp giá trị vào tên cột 2
        ContentValues contentValues = new ContentValues();
        contentValues.put("idBook",book.getIdBook());
        contentValues.put("bookName",book.getBookName());
        contentValues.put("idCategory",book.getIdCategory());
        contentValues.put("unitPrice",book.getUnitPrice());
        contentValues.put("amount",book.getAmount());

        // truy vấn 3
        long kq = sqLiteDatabase.insert("book", null, contentValues);

        if (kq > 0){
            return true;
        } else{
            return false;
        }
    }

    public boolean updateBook(book book){
        SQLiteDatabase sqLiteDatabase = sqlite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idBook",book.getIdBook());
        contentValues.put("bookName",book.getBookName());
        contentValues.put("idCategory",book.getIdCategory());
        contentValues.put("unitPrice",book.getUnitPrice());
        contentValues.put("amount",book.getAmount());
        // truy vấn 3
        long kq = sqLiteDatabase.update("book", contentValues, "idBook ="+book.getIdBook(),null);

        if (kq > 0){
            return true;
        } else{
            return false;
        }
    }

    public boolean deBook(int id) {
        SQLiteDatabase sqLiteDatabase = sqlite.getWritableDatabase();

        // truy vấn 3
        long kq = sqLiteDatabase.delete("book", "idBook ="+id,null);

        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<book> getAllBook() {
        List<book> list = new ArrayList<>();
        String sql = "SELECT * FROM book";
        Cursor cursor = sqlite.getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int ID = cursor.getInt(0);
                String Name = cursor.getString(1);
                int idCategory = cursor.getInt(2);
                Double unitPrice = cursor.getDouble(3);
                int amount = cursor.getInt(4);

                book book = new book();
                book.setIdBook(ID);
                book.setBookName(Name);
                book.setIdCategory(idCategory);
                book.setUnitPrice(unitPrice);
                book.setAmount(amount);

                list.add(book);
                cursor.moveToNext();
            }
        }

        return list;
    }

    public List<book> timKiemBookName(String bookName) {
        List<book> list = new ArrayList<>();
        String sql = "SELECT * FROM book WHERE bookName LIKE '%" + bookName + "%'";
        Cursor cursor = sqlite.getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int ID = cursor.getInt(0);
                String Name = cursor.getString(1);
                int idCategory = cursor.getInt(2);
                Double unitPrice = cursor.getDouble(3);
                int amount = cursor.getInt(4);

                book book = new book();
                book.setIdBook(ID);
                book.setBookName(Name);
                book.setIdCategory(idCategory);
                book.setUnitPrice(unitPrice);
                book.setAmount(amount);

                list.add(book);
                cursor.moveToNext();
            }
        }

        return list;
    }

    public int soLuong(int idBook){
        String sql = "SELECT * FROM  invoiceDetails WHERE invoiceDetails.idBook = "+idBook+";";
        Cursor cursor = sqlite.getReadableDatabase().rawQuery(sql,null);

        int tt=0;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                tt += cursor.getDouble(0);
                cursor.moveToNext();
            }
        }
        return tt;
    }
}
