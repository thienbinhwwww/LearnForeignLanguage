package com.example.bookmanagement.ma2_0.theLoai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.bookmanagement.R;
import com.example.bookmanagement.Sql.Sqlite;
import com.example.bookmanagement.Sql.bookDao;
import com.example.bookmanagement.Sql.categoryDao;
import com.example.bookmanagement.model.book;
import com.example.bookmanagement.model.category;

import java.util.ArrayList;
import java.util.List;

public class theLoaiActivity extends AppCompatActivity {
    theLoaiAdapter adapter;
    List<category> list = new ArrayList<>();
    List<book> listSach = new ArrayList<>();

    Sqlite sqlite = new Sqlite(this);
    final com.example.bookmanagement.Sql.bookDao bookDao = new bookDao(sqlite);
    final com.example.bookmanagement.Sql.categoryDao categoryDao = new categoryDao(sqlite);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai);

        list = categoryDao.getAllCategory();
        listSach = bookDao.getAllBook();

        ListView list_theLoai = findViewById(R.id.list_theLoai_theLoai);
        adapter = new theLoaiAdapter(list,listSach,this);
        list_theLoai.setAdapter(adapter);
    }
}