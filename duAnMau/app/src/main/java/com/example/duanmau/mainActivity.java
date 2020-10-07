package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.example.duanmau.Sql.Sqlite;
import com.example.duanmau.chucNang.doanhThu.quanLyDoanhThuActivity;
import com.example.duanmau.chucNang.hoaDon.quanLyHoaDonActivity;
import com.example.duanmau.chucNang.khachHang.khackHangActivity;
import com.example.duanmau.chucNang.nguoiDung.nguoiDungActivity;
import com.example.duanmau.chucNang.qlSach.quanLySachActivity;
import com.example.duanmau.chucNang.theLoai.theLoaiActivity;
import com.example.duanmau.doiTuong.sach;

public class mainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chayChuongTrinh();
    }



    private void chayChuongTrinh() {
        ConstraintLayout lay_quanLySach = findViewById(R.id.lay_quanLySah);
        ConstraintLayout lay_KhachHang = findViewById(R.id.lay_khachHang);
        ConstraintLayout lay_quanLyHoaDon = findViewById(R.id.lay_quanLyHoaDon);
        ConstraintLayout lay_quanLyDoanhThu = findViewById(R.id.lay_doanhThu);
        ConstraintLayout lay_nguoiDung = findViewById(R.id.lay_nguoiDung);
        ConstraintLayout lay_theLoai = findViewById(R.id.lay_theLoai);

        lay_quanLySach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(mainActivity.this, quanLySachActivity.class);
                startActivity(intent);
            }
        });
        lay_KhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(mainActivity.this, khackHangActivity.class);
                startActivity(intent);
            }
        });
        lay_quanLyHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(mainActivity.this, quanLyHoaDonActivity.class);
                startActivity(intent);
            }
        });
        lay_quanLyDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(mainActivity.this, quanLyDoanhThuActivity.class);
                startActivity(intent);
            }
        });
        lay_nguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(mainActivity.this, nguoiDungActivity.class);
                startActivity(intent);
            }
        });
        lay_theLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(mainActivity.this, theLoaiActivity.class);
                startActivity(intent);
            }
        });

    }
}