package com.example.duanmau.chucNang.theLoai;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.duanmau.R;
import com.example.duanmau.chucNang.qlSach.quaLySachAdapter;
import com.example.duanmau.chucNang.qlSach.quanLySachActivity;
import com.example.duanmau.doiTuong.sach;
import com.example.duanmau.doiTuong.theLoaiSach;

import java.util.ArrayList;
import java.util.List;

public class theLoaiAdapter extends BaseAdapter {
    List<theLoaiSach> list;
    List<sach> listSach = new ArrayList<>();
    List<sach> listChiTiet = new ArrayList<>();
    Context context;

    public theLoaiAdapter(List<theLoaiSach> list,Context context) {
        this.list = list;
        this.context= context;
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

        theLoaiSach sach = list.get(position);
        tv_theLoai.setText(sach.getTheLoai());
        tv_soLuong.setText(String.valueOf(sach.getSoLuong()));


        return convertView;
    }
}
