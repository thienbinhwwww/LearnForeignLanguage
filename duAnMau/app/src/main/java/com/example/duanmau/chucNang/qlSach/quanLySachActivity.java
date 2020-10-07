package com.example.duanmau.chucNang.qlSach;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.duanmau.R;
import com.example.duanmau.Sql.Sqlite;
import com.example.duanmau.doiTuong.sach;
import com.example.duanmau.tienich;

import java.util.ArrayList;
import java.util.List;

public class quanLySachActivity extends AppCompatActivity {
    quaLySachAdapter adapter;
    List<sach> list = new ArrayList<>();
    tienich ti = new tienich();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_sach);
        ti.getData(list,this);
        addList();
        themSach();
    }

    private void addList() {
        ListView list_quanLySach = findViewById(R.id.list_quanLySach);
        adapter = new quaLySachAdapter(list,quanLySachActivity.this);
        list_quanLySach.setAdapter(adapter);
    }


    //    kích vào nút thêm sách
    private void themSach() {
        Button btn_them= findViewById(R.id.btn_qlSach_them);
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialogSua = new Dialog(quanLySachActivity.this);
                dialogSua.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogSua.setContentView(R.layout.layout_them_sach);
                dialogSua.show();
                Button btn_xacNhan = dialogSua.findViewById(R.id.btn_qlSach_them_xacNhan);
                Button btn_huy = dialogSua.findViewById(R.id.btn_qlSach_them_huy);
                btn_xacNhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText edt_ten = dialogSua.findViewById(R.id.edt_qlSach_them_ten);
                        EditText edt_theLoai = dialogSua.findViewById(R.id.edt_qlSach_them_theLoai);
                        EditText edt_donGia = dialogSua.findViewById(R.id.edt_qlSach_them_donGia);
                        EditText edt_soLuong = dialogSua.findViewById(R.id.edt_qlSach_them_soLuong);


                        boolean ktra =true;
                        for (int i=0;i<list.size();i++){
                            if((list.get(i).getTenSach().equals(edt_ten.getText().toString()))&&(list.get(i).getTheLoai().equals(edt_theLoai.getText().toString()))){
                                int temp = list.get(i).getSoLuong();
                                list.get(i).setSoLuong(temp+Integer.parseInt(edt_soLuong.getText().toString()));
                                int ID = list.get(i).getID();
                                Sqlite database = new Sqlite(quanLySachActivity.this);
                                database.QueryData("UPDATE Sach SET SoLuong = '"+temp+Integer.parseInt(edt_soLuong.getText().toString())+"'WHERE ID = '"+ID+"' ");
                                ktra = false;
                            }
                        }
                        if(ktra){
                            Sqlite sqlite = new Sqlite(quanLySachActivity.this);
                            sach sach = new sach(
                                    edt_ten.getText().toString(),
                                    edt_theLoai.getText().toString(),
                                    Double.parseDouble(edt_donGia.getText().toString()),
                                    Integer.parseInt(edt_soLuong.getText().toString()));
                            sqlite.themSach(sach);
                        }

                        dialogSua.dismiss();
                        Toast.makeText(quanLySachActivity.this,"Thêm thành công",Toast.LENGTH_LONG).show();
                        Intent intent =  new Intent(quanLySachActivity.this, quanLySachActivity.class);
                        startActivity(intent);
                    }
                });
                btn_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogSua.dismiss();
                    }
                });
            }
        });
    }


    private void themData() {
        Sqlite sqlite = new Sqlite(this);
        sach sach = new sach("Chào tuổi 17","Tâm lý - Tình cảm",90000,25);
        sqlite.themSach(sach);
    }


}