package com.example.bookmanagement.ma2_0.hoaDon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmanagement.R;
import com.example.bookmanagement.Sql.Sqlite;
import com.example.bookmanagement.Sql.billDao;
import com.example.bookmanagement.ma2_0.hoaDon.hoaDonChiTiet.themHoaHDChiTietActivity;
import com.example.bookmanagement.model.bill;
import com.example.bookmanagement.model.book;

import java.util.Calendar;
import java.util.List;

public class themHoaDonActivity extends AppCompatActivity {
    private DatePicker datePicker;
    List<bill> listBill;
    SharedPreferences sharedPreferences;
    int  time;
    int  time2;

    Sqlite sqlite = new Sqlite(this);
    com.example.bookmanagement.Sql.billDao billDao = new billDao(sqlite);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_hoa_don);
        listBill = billDao.getAllUBill();
    }

    public void chonNgay(View view){
        final Dialog dialog;
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_date_picker);
        dialog.show();

        Button btn_huy = dialog.findViewById(R.id.btn_qlHoaDon_them_dateHuy);
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month  = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        time = calendar.get(Calendar.HOUR);
        time2 = calendar.get(Calendar.MINUTE);


        DatePicker datePicker = dialog.findViewById(R.id.dateP_qlHoaDon_them_datePicker);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(final DatePicker datePicker, final int year, final int month, final int dayOfMonth) {
                Button btn_ok = dialog.findViewById(R.id.btn_qlHoaDon_them_dateOK);
                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        datePickerChange(datePicker,year,month,dayOfMonth);
                        dialog.dismiss();
                    }
                });
            }
        });


    }

    public void huy(View view){
        finish();
    }
    public void btn_them(View view){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());


        TextView edt_id = findViewById(R.id.edt_qlHoaDon_them_id);
        TextView edt_date = findViewById(R.id.edt_qlHoaDon_them_ngay);
        boolean ktraID = true;

        if(!edt_date.getText().toString().isEmpty()&&!edt_id.getText().toString().isEmpty()){
            for (int i=0;i<listBill.size();i++) {
                if(listBill.get(i).getIdBill()==Integer.parseInt(edt_id.getText().toString())){
                    ktraID = false;
                }
            }
        }else {
            Toast.makeText(this,"Không để trống các ô nhập",Toast.LENGTH_LONG).show();
        }

        if(ktraID){
            Log.e("time", time+":"+time2 );
            billDao.addBill(new bill(Integer.parseInt(edt_id.getText().toString()), edt_date.getText().toString(),time+":"+time2));
            Toast.makeText(this, "Thêm hóa đơn thành công", Toast.LENGTH_LONG).show();
            sharedPreferences =getSharedPreferences("hoaDon",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.putInt("idBill",Integer.parseInt(edt_id.getText().toString()));
            editor.commit();

            Intent intent = new Intent(this, themHoaHDChiTietActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "ID đã tồn tại", Toast.LENGTH_LONG).show();
        }
    }
    private void datePickerChange(final DatePicker datePicker, final int year, final int month, final int dayOfMonth) {
        Log.d("Date", "Year=" + year + " Month=" + (month + 1) + " day=" + dayOfMonth);
        EditText editText = findViewById(R.id.edt_qlHoaDon_them_ngay);
        editText.setText(dayOfMonth +"/" + (month + 1) + "/" + year);
    }
}