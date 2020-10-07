package com.example.duanmau;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Window;

import com.example.duanmau.Sql.Sqlite;
import com.example.duanmau.doiTuong.sach;

import java.util.List;

public class tienich {


    public void getData(List<sach> list,Context context) {
        Sqlite database = new Sqlite(context);
        Cursor dataSach = database.GetData("SELECT * FROM Sach");
        list.clear();
        while (dataSach.moveToNext()){
            sach sach = new sach();
            sach.setID(dataSach.getInt(0));
            sach.setTenSach(dataSach.getString(1));
            sach.setTheLoai(dataSach.getString(2));
            sach.setDonGia(dataSach.getDouble(3));
            sach.setSoLuong(dataSach.getInt(4));
            list.add(sach);

        }
    }
    public void xoaDataSach(int pos,List<sach> list,Context context){
        int ID = list.get(pos).getID();
        Sqlite database = new Sqlite(context);
        database.QueryData("DELETE FROM Sach WHERE id ='"+ID+"'");
        getData(list,context);
    }
}
