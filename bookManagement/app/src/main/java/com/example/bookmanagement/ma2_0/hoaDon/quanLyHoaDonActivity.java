package com.example.bookmanagement.ma2_0.hoaDon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.bookmanagement.R;
import com.example.bookmanagement.Sql.Sqlite;
import com.example.bookmanagement.Sql.billDao;
import com.example.bookmanagement.Sql.bookDao;
import com.example.bookmanagement.Sql.invoiceDetailsDao;
import com.example.bookmanagement.model.bill;
import com.example.bookmanagement.model.book;
import com.example.bookmanagement.model.invoiceDetails;

import java.util.List;

public class quanLyHoaDonActivity extends AppCompatActivity {
    List<book> listBook;
    List<bill> listBill;
    List<invoiceDetails> listInvoiceDetails;
    SharedPreferences sharedPreferences;
    Button btn_tim;
    EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_hoa_don);
        btn_tim= findViewById(R.id.btn_tim);
        edt = findViewById(R.id.edt_tim);
        final Sqlite sqlite = new Sqlite(this);


        addData();
        sharedPreferences = getSharedPreferences("hoaDon",MODE_PRIVATE);

        final quanLyHoaDonAdapter adapter = new quanLyHoaDonAdapter(listBill, listBook, listInvoiceDetails, this,sharedPreferences);

        ListView lv_hoaDon = findViewById(R.id.listView_qlHoaDon);
        lv_hoaDon.setAdapter(adapter);

        btn_tim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final bookDao bookDao = new bookDao(sqlite);
                final invoiceDetailsDao detailsDao = new invoiceDetailsDao(sqlite);
                final billDao billDao = new billDao(sqlite);
                listBill = billDao.timKiem(edt.getText().toString());
                ListView lv_hoaDon = findViewById(R.id.listView_qlHoaDon);
                lv_hoaDon.setAdapter(adapter);

            }
        });

    }

    private void addData() {
        Sqlite sqlite = new Sqlite(this);
        final bookDao bookDao = new bookDao(sqlite);
        final invoiceDetailsDao detailsDao = new invoiceDetailsDao(sqlite);
        final billDao billDao = new billDao(sqlite);

        listInvoiceDetails = detailsDao.getAllInvoiceDetails();
        listBill = billDao.getAllUBill();
        listBook = bookDao.getAllBook();
    }

    public void them(View view){
        Intent intent = new Intent(this,themHoaDonActivity.class);
        startActivity(intent);
    }
}