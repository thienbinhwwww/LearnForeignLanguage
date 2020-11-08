package com.example.bookmanagement.ma2_0.hoaDon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bookmanagement.R;
import com.example.bookmanagement.Sql.Sqlite;
import com.example.bookmanagement.Sql.billDao;
import com.example.bookmanagement.Sql.bookDao;
import com.example.bookmanagement.Sql.categoryDao;
import com.example.bookmanagement.Sql.invoiceDetailsDao;
import com.example.bookmanagement.ma2_0.hoaDon.hoaDonChiTiet.hoaDonChiTietActivity;
import com.example.bookmanagement.model.bill;
import com.example.bookmanagement.model.book;
import com.example.bookmanagement.model.invoiceDetails;

import java.util.List;

public class quanLyHoaDonAdapter extends BaseAdapter {
    List<bill> listBill;
    List<book> listBook;
    List<invoiceDetails> listInvoiceDetails;
    Context context;
    SharedPreferences sharedPreferences;

    public quanLyHoaDonAdapter(List<bill> listBill, List<book> listBook, List<invoiceDetails> listInvoiceDetails, Context context,SharedPreferences sharedPreferences) {
        this.listBill = listBill;
        this.listBook = listBook;
        this.listInvoiceDetails = listInvoiceDetails;
        this.context = context;
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public int getCount() {
        return listBill.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_list_view_ql_hoa_don,viewGroup,false);

        Sqlite sqlite = new Sqlite(context);
        invoiceDetailsDao detailsDao = new invoiceDetailsDao(sqlite);
        TextView tv_id = view.findViewById(R.id.tv_qlHoaDon_listView_id);
        TextView tv_ngay = view.findViewById(R.id.tv_qlHoaDon_listView_ngay);
        TextView tv_thanhTien = view.findViewById(R.id.tv_qlHoaDon_listView_thanhTien);
        TextView tv_time = view.findViewById(R.id.tv_time);

        final int idBill;
        double thanhTien = 0;

        idBill = listBill.get(i).getIdBill();
        for (int j=0;j<listInvoiceDetails.size();j++){
            Log.e("idBill 1", String.valueOf(listInvoiceDetails.get(j).getIdBill()));
            Log.e("idBill 2", String.valueOf(idBill));
            if(listInvoiceDetails.get(j).getIdBill()==idBill){
                thanhTien += detailsDao.thanhTien(listInvoiceDetails.get(j).getIdInvoiceDetails(),listInvoiceDetails.get(j).getIdBook());
                Log.e("thành tiền", String.valueOf(thanhTien));
            }
        }

        tv_id.setText(String.valueOf(idBill));
        tv_ngay.setText(listBill.get(i).getDate());
        tv_thanhTien.setText(String.valueOf(thanhTien));
        tv_time.setText(listBill.get(i).getTime());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.putInt("idBill",idBill);
                editor.commit();

                Intent intent = new Intent(context, hoaDonChiTietActivity.class);
                context.startActivity(intent);
            }
        });


        return view;
    }
}
