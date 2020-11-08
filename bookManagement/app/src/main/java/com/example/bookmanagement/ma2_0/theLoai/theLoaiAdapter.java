package com.example.bookmanagement.ma2_0.theLoai;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmanagement.R;
import com.example.bookmanagement.Sql.Sqlite;
import com.example.bookmanagement.Sql.bookDao;
import com.example.bookmanagement.Sql.categoryDao;
import com.example.bookmanagement.model.book;
import com.example.bookmanagement.model.category;


import java.util.ArrayList;
import java.util.List;

public class theLoaiAdapter extends BaseAdapter {
    List<category> list;
    List<book> listSach = new ArrayList<>();
    Context context;

    public theLoaiAdapter(List<category> list,List<book> listSach, Context context) {
        this.list = list;
        this.context = context;
        this.listSach = listSach;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layuot_list_view_loai_sach,parent,false);
        TextView tv_theLoai = convertView.findViewById(R.id.tv_layLoaisach);
        TextView tv_soLuong = convertView.findViewById(R.id.tv_laySoluong);
        Sqlite sqlite = new Sqlite(context);
        final categoryDao categoryDao = new categoryDao(sqlite);
        int SoLuong=0;

        try {
            while (true){
                for (int i=0;i<listSach.size();i++){
                    if(listSach.get(i).getIdCategory()==list.get(position).getIdCategory()){
                        SoLuong++;
                    }
                }
                if(SoLuong==0){
                    categoryDao.dCategory(list.get(position).getIdCategory());
                    list=categoryDao.getAllCategory();
                }

                category sach = list.get(position);
                tv_theLoai.setText(sach.getCategory());
                tv_soLuong.setText(String.valueOf(SoLuong));
                break;
            }
        }catch (Exception e){
            Log.e("e",String.valueOf(e));
        }


        return convertView;
    }
}
