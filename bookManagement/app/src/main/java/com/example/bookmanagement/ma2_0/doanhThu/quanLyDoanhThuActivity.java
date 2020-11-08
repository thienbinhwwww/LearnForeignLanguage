package com.example.bookmanagement.ma2_0.doanhThu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.bookmanagement.R;
import com.example.bookmanagement.Sql.Sqlite;
import com.example.bookmanagement.Sql.invoiceDetailsDao;

import java.util.Calendar;

public class quanLyDoanhThuActivity extends AppCompatActivity {
    Sqlite sqlite = new Sqlite(this);
    invoiceDetailsDao detailsDao = new invoiceDetailsDao(sqlite);
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_doanh_thu);
        TextView tv_dtNgay = findViewById(R.id.tv_doanhThuNgay);
        TextView tv_dtThang = findViewById(R.id.tv_doanhThuThang);
        TextView tv_dtNam = findViewById(R.id.tv_doanhThuNam);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month  = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        String tv_ngay = String.valueOf(day+"/"+(month+1)+"/"+year);
        String tv_thang = String.valueOf("%/"+(month+1)+"/"+year);
        String tv_nam = "%/"+String.valueOf(year);

        Log.e("ngay", tv_ngay+"-"+tv_thang+"-"+tv_nam);
        Log.e("dt",String.valueOf(detailsDao.doanhThu(tv_ngay))+"-"+String.valueOf(detailsDao.doanhThu(tv_thang))+"-"+String.valueOf(detailsDao.doanhThu(tv_nam)));
        tv_dtNgay.setText(String.valueOf(detailsDao.doanhThu(tv_ngay)));
        tv_dtThang.setText(String.valueOf(detailsDao.doanhThu(tv_thang)));
        tv_dtNam.setText(String.valueOf(detailsDao.doanhThu(tv_nam)));

    }


}