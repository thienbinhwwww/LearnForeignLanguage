package com.example.bookmanagement.ma2_0.quanLySach;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bookmanagement.R;
import com.example.bookmanagement.Sql.Sqlite;
import com.example.bookmanagement.Sql.bookDao;
import com.example.bookmanagement.Sql.categoryDao;
import com.example.bookmanagement.model.book;
import com.example.bookmanagement.model.category;

import java.util.ArrayList;
import java.util.List;

public class quanLySachActivity extends AppCompatActivity {
    quaLySachAdapter adapter;
    List<book> list = new ArrayList<>();
    List<category> categoryList = new ArrayList<>();
    Sqlite sqlite = new Sqlite(this);
    final com.example.bookmanagement.Sql.bookDao bookDao = new bookDao(sqlite);
    final com.example.bookmanagement.Sql.categoryDao categoryDao = new categoryDao(sqlite);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_sach);

        list = bookDao.getAllBook();
        categoryList = categoryDao.getAllCategory();

        addList();
        themSach();
    }

    public void addList() {
        ListView list_quanLySach = findViewById(R.id.list_quanLySach);
        adapter = new quaLySachAdapter(list,categoryList,quanLySachActivity.this);
        adapter.notifyDataSetChanged();
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
                        EditText edt_id = dialogSua.findViewById(R.id.edt_qlSach_them_ID);

                        book book =  new book();
                        boolean DK = true;
                        int temp=0;
                        for (int i=0;i<categoryList.size();i++){
                            if((categoryList.get(i).getCategory().equals(edt_theLoai.getText().toString()))){
                                book.setIdCategory(categoryList.get(i).getIdCategory());
                                DK=false;
                                break;
                            }
                        }

                        boolean ktra=true;
                        while (DK){
                            for (int i=0;i<categoryList.size();i++) {
                                if (temp == categoryList.get(i).getIdCategory() ){
                                    ktra = false;
                                }
                            }
                            if (!ktra){
                                temp++;
                                ktra = true;
                            }else {
                                book.setIdCategory(temp);
                                categoryDao.adCategory(new category(temp,edt_theLoai.getText().toString()));
                                break;
                            }
                        }

                        book.setIdBook(Integer.parseInt(edt_id.getText().toString()));
                        book.setBookName(edt_ten.getText().toString());
                        book.setUnitPrice(Double.parseDouble(edt_donGia.getText().toString()));
                        book.setAmount(Integer.parseInt(edt_soLuong.getText().toString()));

                        boolean checkID = true;
                        for (int i=0;i<list.size();i++){
                                if(book.getIdBook()==list.get(i).getIdBook()){
                                    Toast.makeText(quanLySachActivity.this,"ID đã tồn tại",Toast.LENGTH_LONG).show();
                                    checkID = false;
                                    break;
                                }
                        }

                        while (true){
                            if(ktraRong(edt_id.getText().toString())&&ktraRong(edt_ten.getText().toString())&&ktraRong(edt_theLoai.getText().toString())&&ktraRong(edt_donGia.getText().toString())&&ktraRong(edt_soLuong.getText().toString())){
                                bookDao.addBook(book);
                                dialogSua.dismiss();
                                Toast.makeText(quanLySachActivity.this,"Thêm thành công",Toast.LENGTH_LONG).show();
                                list = bookDao.getAllBook();
                                addList();
                                break;
                            }else {
                                Toast.makeText(quanLySachActivity.this,"không để trống dữ liệu",Toast.LENGTH_LONG).show();
                            }
                        }


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

    public boolean  ktraRong(String data){
        if(data.isEmpty()){
            return false;
        }else {
            return true;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        list = bookDao.getAllBook();
        addList();
    }
}