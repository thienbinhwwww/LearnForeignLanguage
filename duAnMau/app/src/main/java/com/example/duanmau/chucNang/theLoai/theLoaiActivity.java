package com.example.duanmau.chucNang.theLoai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.duanmau.R;
import com.example.duanmau.Sql.Sqlite;
import com.example.duanmau.doiTuong.sach;
import com.example.duanmau.doiTuong.theLoaiSach;
import com.example.duanmau.tienich;

import java.util.ArrayList;
import java.util.List;

public class theLoaiActivity extends AppCompatActivity {
    theLoaiAdapter adapter;
    List<theLoaiSach> list = new ArrayList<>();
    List<sach> listSach = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai);
        ListView list_theLoai = findViewById(R.id.list_theLoai_theLoai);
        tienich ti = new tienich();
        ti.getData(listSach,this);
        int so=0;
        if(list.size()==0){
            list.add(new theLoaiSach(listSach.get(0).getTheLoai(),1));
            so=1;
        }
        for (int i=so;i<listSach.size();i++){
            boolean ktra = true;
            for (int j=0;j<list.size();j++){
                if(list.get(j).getTheLoai().equals(listSach.get(i).getTheLoai())) {
                    int temp = list.get(j).getSoLuong();
                    list.get(j).setSoLuong(temp+1);
                    ktra = false;
                }
            }
            if(ktra){
                list.add(new theLoaiSach(listSach.get(i).getTheLoai(),1));
            }
        }


        adapter = new theLoaiAdapter(list,this);
        list_theLoai.setAdapter(adapter);


    }
}