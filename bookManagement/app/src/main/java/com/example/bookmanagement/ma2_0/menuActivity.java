package com.example.bookmanagement.ma2_0;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bookmanagement.R;
import com.example.bookmanagement.ma2_0.doanhThu.quanLyDoanhThuActivity;
import com.example.bookmanagement.ma2_0.hoaDon.quanLyHoaDonActivity;
import com.example.bookmanagement.ma2_0.quanLySach.quanLySachActivity;
import com.example.bookmanagement.ma2_0.theLoai.theLoaiActivity;
import com.example.bookmanagement.ma2_0.user.nguoiDungActivity;
import com.example.bookmanagement.ma2_0.topBanChay.topBanChayActivity;

public class menuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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
                Intent intent =  new Intent(menuActivity.this, quanLySachActivity.class);
                startActivity(intent);
            }
        });
        lay_KhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(menuActivity.this, topBanChayActivity.class);
                startActivity(intent);
            }
        });
        lay_quanLyHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(menuActivity.this, quanLyHoaDonActivity.class);
                startActivity(intent);
            }
        });
        lay_quanLyDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(menuActivity.this, quanLyDoanhThuActivity.class);
                startActivity(intent);
            }
        });
        lay_nguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(menuActivity.this, nguoiDungActivity.class);
                startActivity(intent);
            }
        });
        lay_theLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(menuActivity.this, theLoaiActivity.class);
                startActivity(intent);
            }
        });

    }
}