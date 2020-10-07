package com.example.duanmau.chucNang.nguoiDung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.duanmau.R;

public class chinhSuaThongTinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua_thong_tin);
        Button btn_sua=findViewById(R.id.btn_nguoiDung_chinhSua_xacNhan);
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(chinhSuaThongTinActivity.this,nguoiDungActivity.class);
                startActivity(intent);
            }
        });
    }
}