package com.example.baithi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class khachHangActivity extends AppCompatActivity {
    Sqlite sqlite ;
    viTriDao viTriDao ;
    List<ViTri> listKH;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khach_hang);
        anhXa();
        addList();
    }

    private void addList() {
        adapterKhachHang adapterKhachHang = new adapterKhachHang(listKH,this);
        listView.setAdapter(adapterKhachHang);
    }

    private void anhXa() {
        sqlite = new Sqlite(this);
        viTriDao = new viTriDao(sqlite);
        listKH = viTriDao.getVT();
        listView = findViewById(R.id.listKhachHang);
    }
    public void add(View view){
        final Dialog dialog;
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_them_vi_tri);
        dialog.show();

        Button btn_them = dialog.findViewById(R.id.button2);
        Button btn_huy = dialog.findViewById(R.id.button3);
        TextView edt_id = dialog.findViewById(R.id.edt_id);
        TextView edt_tieuDe = dialog.findViewById(R.id.edt_tieuDe);
        TextView edt_kinhDo = dialog.findViewById(R.id.edt_kinhDo);
        TextView edt_viDo = dialog.findViewById(R.id.edt_viDo);
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viTriDao.addVT(new ViTri(edt_tieuDe.getText().toString(),edt_id.getText().toString(),Double.parseDouble(edt_kinhDo.getText().toString()),Double.parseDouble(edt_viDo.getText().toString())));
                Toast.makeText(khachHangActivity.this,"Thêm thành công",Toast.LENGTH_LONG).show();
                dialog.dismiss();
                anhXa();
                addList();
            }
        });

        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }
}