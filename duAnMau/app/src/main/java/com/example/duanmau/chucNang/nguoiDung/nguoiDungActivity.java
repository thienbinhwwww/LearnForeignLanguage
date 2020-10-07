package com.example.duanmau.chucNang.nguoiDung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.duanmau.R;

public class nguoiDungActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);
        Button btn_sua = findViewById(R.id.btn_nguoiDung_chinhSua);
        Button btn_doiPass = findViewById(R.id.btn_nguoiDung_doiPass);
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(nguoiDungActivity.this,chinhSuaThongTinActivity.class);
                startActivity(intent);
            }
        });
        btn_doiPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(nguoiDungActivity.this,doiPassActivity.class);
                startActivity(intent);
            }
        });
    }
}