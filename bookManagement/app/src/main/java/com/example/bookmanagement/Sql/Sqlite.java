package com.example.bookmanagement.Sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.bookmanagement.model.uses;


public class Sqlite extends SQLiteOpenHelper {
    public Sqlite(@Nullable Context context) {
        super(context, "data.db",null,1);
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


//        Tạo bảng sách
        String book = "CREATE TABLE book (" +
                "idBook INTEGER NOT NULL, " +
                "bookName TEXT," +
                "idCategory INTEGER," +
                "unitPrice BLOB," +
                "amount INTEGER," +
                "FOREIGN KEY(idCategory) REFERENCES category(idCategory)," +
                "PRIMARY KEY(idBook)) ";
        db.execSQL(book);

//        Tạo bảng thể loại
        String category = "CREATE TABLE category(" +
                "idCategory INTEGER NOT NULL," +
                "category TEXT," +
                "PRIMARY KEY(idCategory))";
        db.execSQL(category);

//        Tạo bảng hóa đơn
        String bill = "CREATE TABLE bill(" +
                "idBill INTEGER NOT NULL," +
                "date TEXT," +
                "PRIMARY KEY(idBill))";
        db.execSQL(bill);

//        Tạo bảng hóa đơn chi tiết
        String invoiceDetails = "CREATE TABLE invoiceDetails(" +
                "idInvoiceDetails INTEGER NOT NULL," +
                "idBook INTEGER," +
                "idBill INTEGER," +
                "amount INTEGER," +
                "PRIMARY KEY(idInvoiceDetails)," +
                "FOREIGN KEY(idBill)REFERENCES bill(idBill)," +
                "FOREIGN KEY(idBook)REFERENCES book(idBook))";
        db.execSQL(invoiceDetails);

//        Tạo bảng người dùng
        String uses = "CREATE TABLE uses(" +
                "isUses INTEGER NOT NULL," +
                "usesName TEXT," +
                "password TEXT," +
                "email TEXT," +
                "PRIMARY KEY(idUses))";
        db.execSQL(uses);
    }
    public void themSach(){

    }

    public void addBook(uses uses){

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
