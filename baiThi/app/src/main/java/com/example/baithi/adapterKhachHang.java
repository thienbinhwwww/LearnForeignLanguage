package com.example.baithi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class adapterKhachHang extends BaseAdapter {
    List<ViTri> listKH;
    Context context;
    Sqlite sqlite = new Sqlite(context);

    public adapterKhachHang(List<ViTri> listKH, Context context) {
        this.listKH = listKH;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listKH.size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_list,viewGroup,false);
        TextView tv_id = view.findViewById(R.id.tv_id);
        TextView tv_ten = view.findViewById(R.id.tv_ten);
        TextView tv_diaChi = view.findViewById(R.id.tv_diaChi);

        tv_id.setText(listKH.get(i).getID());
        tv_ten.setText(listKH.get(i).getTen());
        tv_diaChi.setText(listKH.get());

        return view;
    }
}
