package vn.poly.edu.qlsachpn.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.poly.edu.qlsachpn.model.NguoiDung;

public class UserDAO {

    private MySqlite mySqlite;

    public UserDAO(MySqlite mySqlite) {
        this.mySqlite = mySqlite;
    }


    // add

    public boolean addUser(NguoiDung nguoiDung) {
        // xin quyen 1
        SQLiteDatabase sqLiteDatabase = mySqlite.getWritableDatabase();
        // ghép cặp giá trị vào tên cột 2
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", nguoiDung.username);
        contentValues.put("name", nguoiDung.ten);
        contentValues.put("password", nguoiDung.password);
        contentValues.put("numberPhone", nguoiDung.sdt);
        // truy vấn 3
        long kq = sqLiteDatabase.insert("USER", null, contentValues);

        if (kq > 0) return true;
        else return false;

    }

    // update
    public boolean updateUser(NguoiDung nguoiDung) {
        // xin quyen 1
        SQLiteDatabase sqLiteDatabase = mySqlite.getWritableDatabase();
        // ghép cặp giá trị vào tên cột 2
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", nguoiDung.username);
        contentValues.put("name", nguoiDung.ten);
        contentValues.put("password", nguoiDung.password);
        contentValues.put("numberPhone", nguoiDung.sdt);
        // truy vấn 3
        long kq = sqLiteDatabase.update("USER", contentValues, "username=?",
                new String[]{nguoiDung.username});

        if (kq > 0) return true;
        else return false;

    }

    // del
    public boolean delUser(String username) {
        // xin quyen 1
        SQLiteDatabase sqLiteDatabase = mySqlite.getWritableDatabase();

        // truy vấn 3
        long kq = sqLiteDatabase.delete("USER", "username=?",
                new String[]{username});

        if (kq > 0) return true;
        else return false;

    }

    // get all

    public List<NguoiDung> getAllUsers() {
        List<NguoiDung> nguoiDungList = new ArrayList<>();
        String sql = "SELECT * FROM USER";
        Cursor cursor = mySqlite.getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String username = cursor.getString(0);
                String name = cursor.getString(1);
                String password = cursor.getString(2);
                String std = cursor.getString(3);

                NguoiDung nguoiDung = new NguoiDung();
                nguoiDung.username = username;
                nguoiDung.ten = name;
                nguoiDung.password = password;
                nguoiDung.sdt = std;

                nguoiDungList.add(nguoiDung);
                cursor.moveToNext();
            }
        }

        return nguoiDungList;
    }


    public List<NguoiDung> timKiemUsername(String TimUsername) {
        List<NguoiDung> nguoiDungList = new ArrayList<>();
        String sql = "SELECT * FROM USER WHERE username LIKE '%" + TimUsername + "%'";
        Cursor cursor = mySqlite.getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String username = cursor.getString(0);
                String name = cursor.getString(1);
                String password = cursor.getString(2);
                String std = cursor.getString(3);

                NguoiDung nguoiDung = new NguoiDung();
                nguoiDung.username = username;
                nguoiDung.ten = name;
                nguoiDung.password = password;
                nguoiDung.sdt = std;

                nguoiDungList.add(nguoiDung);
                cursor.moveToNext();
            }
        }

        return nguoiDungList;
    }


}
