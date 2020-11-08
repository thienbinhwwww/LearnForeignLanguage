package com.example.baithi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class ThemViTriActivity extends AppCompatActivity {
    Button btn_them,btn_huy;
    EditText edt_tieuDe,edt_id,edt_kinhDo,edt_viDo;
    Sqlite sqlite;
    viTriDao viTriDao;
    List<ViTri> listViTri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_vi_tri);

        anhXa();


    }

    private void anhXa() {
        sqlite = new Sqlite(this);
        viTriDao = new viTriDao(sqlite);
        listViTri = viTriDao.getVT();

        btn_them = findViewById(R.id.button2);
        btn_huy = findViewById(R.id.button3);
        edt_tieuDe = findViewById(R.id.edt_tieuDe);
        edt_id = findViewById(R.id.edt_id);
        edt_kinhDo = findViewById(R.id.edt_kinhDo);
        edt_viDo = findViewById(R.id.edt_viDo);
    }

    public void them(View view){
        if(edt_tieuDe.getText().toString().isEmpty()||edt_id.getText().toString().isEmpty()||edt_kinhDo.getText().toString().isEmpty()||edt_viDo.getText().toString().isEmpty()){
            Toast.makeText(ThemViTriActivity.this,"Không để trống dữ liệu",Toast.LENGTH_LONG).show();
        }else {
            if(edt_tieuDe.getText().toString().length()<5){
                Toast.makeText(ThemViTriActivity.this,"Tiêu đề yêu cầu tối thiểu 5 ký tụ",Toast.LENGTH_LONG).show();
            }else {
                viTriDao.addVT(new ViTri(edt_tieuDe.getText().toString(),edt_id.getText().toString(),Double.parseDouble(edt_kinhDo.getText().toString()),Double.parseDouble(edt_viDo.getText().toString())));

                Toast.makeText(ThemViTriActivity.this,"Thêm thành công",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(ThemViTriActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        }
    }

    public void huy(View view){
        Intent intent = new Intent(ThemViTriActivity.this, MapsActivity.class);
        startActivity(intent);
    }
}