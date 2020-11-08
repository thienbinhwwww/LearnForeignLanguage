package com.example.bookmanagement.ma2_0.topBanChay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.bookmanagement.R;
import com.example.bookmanagement.Sql.Sqlite;
import com.example.bookmanagement.Sql.bookDao;
import com.example.bookmanagement.Sql.categoryDao;
import com.example.bookmanagement.Sql.invoiceDetailsDao;
import com.example.bookmanagement.ma2_0.quanLySach.quaLySachAdapter;
import com.example.bookmanagement.ma2_0.quanLySach.quanLySachActivity;
import com.example.bookmanagement.model.book;
import com.example.bookmanagement.model.category;
import com.example.bookmanagement.model.invoiceDetails;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class topBanChayActivity extends AppCompatActivity {
    List<book> listBook,listBookShow;
    List<category> listCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_ban_chay);
        Sqlite sqlite = new Sqlite(this);
        bookDao bookDao = new bookDao(sqlite);
        categoryDao categoryDao = new categoryDao(sqlite);
        listBook = bookDao.getAllBook();
        listCategory = categoryDao.getAllCategory();
        listBookShow = new ArrayList<>();

        int soLuong[]= new int[listBook.size()];
        for(int i=0;i<listBook.size();i++){
            soLuong[i]=bookDao.soLuong(listBook.get(i).getIdBook());
        }

        int temp = soLuong[0];
        for (int i = 0 ; i < listBook.size()-1; i++) {
            for (int j = i + 1; j < listBook.size(); j++) {
                if (soLuong[i] < soLuong[j]) {
                    temp = soLuong[j];
                    soLuong[j] = soLuong[i];
                    soLuong[i] = temp;
                }
            }
        }

        listBookShow.clear();
        try {
            for (int i=0;i<10;i++){
                for(int j=0;j<listBook.size();j++){
                    if(listBook.get(j).getIdBook()==soLuong[i]){
                        listBookShow.add(listBook.get(j));
                    }
                }
            }
        }catch (Exception e){}

        quaLySachAdapter adapter = new quaLySachAdapter(listBook,listCategory,this);
        ListView listView = findViewById(R.id.listView_topSach);
        listView.setAdapter(adapter);
    }
}