package com.example.duanmau.chucNang.nguoiDung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.duanmau.R;

public class doiPassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_pass);
        Button btn_doiPass=findViewById(R.id.btn_nguoidung_doiPass_xacNhan);
        btn_doiPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(doiPassActivity.this,nguoiDungActivity.class);
                startActivity(intent);
            }
        });
    }
}