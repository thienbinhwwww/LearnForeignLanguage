package com.example.duanmau.Sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.duanmau.doiTuong.sach;

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
        String sach ="CREATE TABLE Sach (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Ten TEXT," +
                "TheLoai TEXT," +
                "DonGia REAL," +
                "SoLuong INTEGER)";
        db.execSQL(sach);
        String hoadon ="CREATE TABLE HoaDon (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Ngay TEXT," +
                "TongTien REAL," +
                "idKhachHang TEXT)";
        db.execSQL(hoadon);

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
    public void themSach(sach sach){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("booName",sach.getTenSach());
        values.put("TheLoai",sach.getTheLoai());
        values.put("DonGia",sach.getDonGia());
        values.put("SoLuong",sach.getSoLuong());

        db.insert("book",null,values);
    }

    public void addBook(){

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
