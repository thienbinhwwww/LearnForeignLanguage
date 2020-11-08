package com.example.bookmanagement.Sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bookmanagement.model.invoiceDetails;

import java.util.ArrayList;
import java.util.List;

public class invoiceDetailsDao {

    Sqlite sqlite;

    public invoiceDetailsDao(Sqlite sqlite) {
        this.sqlite = sqlite;
    }

    public boolean addInvoiceDetails(invoiceDetails invoiceDetails){
        SQLiteDatabase sqLiteDatabase = sqlite.getWritableDatabase();
        // ghép cặp giá trị vào tên cột 2
        ContentValues contentValues = new ContentValues();
        contentValues.put("idInvoiceDetails",invoiceDetails.getIdInvoiceDetails());
        contentValues.put("idBook",invoiceDetails.getIdBook());
        contentValues.put("idBill",invoiceDetails.getIdBill());
        contentValues.put("amount",invoiceDetails.getAmount());

        // truy vấn 3
        long kq = sqLiteDatabase.insert("invoiceDetails", null, contentValues);

        if (kq > 0){
            return true;
        } else{
            return false;
        }
    }

    public boolean updateInvoiceDetails(invoiceDetails invoiceDetails){
        SQLiteDatabase sqLiteDatabase = sqlite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idInvoiceDetails",invoiceDetails.getIdInvoiceDetails());
        contentValues.put("idBook",invoiceDetails.getIdBook());
        contentValues.put("idBill",invoiceDetails.getIdBill());
        contentValues.put("amount",invoiceDetails.getAmount());
        // truy vấn 3
        long kq = sqLiteDatabase.update("invoiceDetails", contentValues, "idInvoiceDetails ="+new int[]{invoiceDetails.getIdInvoiceDetails()},null);

        if (kq > 0){
            return true;
        } else{
            return false;
        }
    }

    public boolean deInvoiceDetails(int id) {
        SQLiteDatabase sqLiteDatabase = sqlite.getWritableDatabase();
        // truy vấn 3
        long kq = sqLiteDatabase.delete("invoiceDetails", "idInvoiceDetails =" + new int[]{id}, null);

        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<invoiceDetails> getAllInvoiceDetails() {
        List<invoiceDetails> list = new ArrayList<>();
        String sql = "SELECT * FROM invoiceDetails";
        Cursor cursor = sqlite.getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int idInvoiceDetails = cursor.getInt(0);
                int idBook = cursor.getInt(1);
                int idBill = cursor.getInt(2);
                int amount = cursor.getInt(3);

                invoiceDetails invoiceDetails = new invoiceDetails();
                invoiceDetails.setIdInvoiceDetails(idInvoiceDetails);
                invoiceDetails.setIdBook(idBook);
                invoiceDetails.setIdBill(idBill);
                invoiceDetails.setAmount(amount);

                list.add(invoiceDetails);
                cursor.moveToNext();
            }
        }
        return list;
    }
    public String bookName(int idInvoiceDetails,int idBook){
        String sql = "SELECT B.bookName ,I.idInvoiceDetails FROM invoiceDetails I INNER JOIN book B ON I.idBook = B.idBook WHERE B.idBook="+idBook+" AND I.idInvoiceDetails="+idInvoiceDetails+";";
        Cursor cursor = sqlite.getReadableDatabase().rawQuery(sql,null);

        String name=null;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                name = cursor.getString(0);
                cursor.moveToNext();
            }
        }
        return name;
    }
    public Double thanhTien(int idInvoiceDetails,int idBook){
        String sql = "SELECT B.unitPrice*I.amount FROM invoiceDetails I INNER JOIN book B ON I.idBook = B.idBook WHERE B.idBook="+idBook+" AND I.idInvoiceDetails="+idInvoiceDetails+";";
        Cursor cursor = sqlite.getReadableDatabase().rawQuery(sql,null);

        Double tt=0.0;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                tt = cursor.getDouble(0);
                cursor.moveToNext();
            }
        }
        return tt;
    }
    public Double doanhThu(String tv){
        String sql = "SELECT B.unitPrice*I.amount FROM book B INNER JOIN invoiceDetails I ON B.idBook = I.idBook INNER JOIN bill Bi ON I.idBill = Bi.idBill WHERE Bi.date LIKE "+'"'+tv+'"'+";";
        Cursor cursor = sqlite.getReadableDatabase().rawQuery(sql,null);

        Double tt=0.0;
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
