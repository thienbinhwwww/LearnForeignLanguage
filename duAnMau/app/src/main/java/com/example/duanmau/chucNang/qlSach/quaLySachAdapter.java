package com.example.duanmau.chucNang.qlSach;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.R;
import com.example.duanmau.Sql.Sqlite;
import com.example.duanmau.doiTuong.sach;
import com.example.duanmau.tienich;

import java.util.List;

public class quaLySachAdapter extends BaseAdapter {
    List<sach> list;
    Context context;
    tienich ti = new tienich();
    quaLySachAdapter adapter;

    public quaLySachAdapter(List<sach> list,Context context) {
        this.list = list;
        this.context = context;
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
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_view_quan_ly_sach,parent,false);
        TextView tv_ten = convertView.findViewById(R.id.tv_sach_tenSach);
        TextView tv_theLoai = convertView.findViewById(R.id.tv_sach_theLoai);
        TextView tv_donGia = convertView.findViewById(R.id.tv_sach_donGia);
        TextView tv_soLuong = convertView.findViewById(R.id.tv_sach_soLuong);

        sach sach = list.get(position);

        tv_ten.setText(sach.getTenSach());
        tv_theLoai.setText(sach.getTheLoai());
        tv_donGia.setText(String.valueOf(sach.getDonGia()));
        tv_soLuong.setText(String.valueOf(sach.getSoLuong()));

        final View finalConvertView = convertView;
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_lua_chon);
                dialog.show();
                Button btn_xoa = dialog.findViewById(R.id.btn_xoa);
                Button btn_sua = dialog.findViewById(R.id.btn_sua);
                btn_sua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Dialog dialogSua = new Dialog(context);
                        dialogSua.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialogSua.setContentView(R.layout.layout_sua_sach);
                        dialogSua.show();
                        Button btn_sua_xacNhan = dialogSua.findViewById(R.id.btn_qlSach_sua_xacnhan);
                        Button btn_sua_huy = dialogSua.findViewById(R.id.btn_qlSach_sua_huy);
                        final EditText edt_ten = dialogSua.findViewById(R.id.edt_qlSach_sua_ten);
                        final EditText edt_theLoai = dialogSua.findViewById(R.id.edt_qlSach_sua_theLoai);
                        final EditText edt_donGia = dialogSua.findViewById(R.id.edt_qlSach_sua_donGia);
                        final EditText edt_soLuong = dialogSua.findViewById(R.id.edt_qlSach_sua_soLuong);
                        final String ten = list.get(position).getTenSach();
                        String theLoai = list.get(position).getTheLoai();
                        Double donGia = list.get(position).getDonGia();
                        int soLuong = list.get(position).getSoLuong();
                        edt_ten.setText(ten);
                        edt_theLoai.setText(theLoai);
                        edt_donGia.setText(String.valueOf(donGia));
                        edt_soLuong.setText(String.valueOf(soLuong));
                        btn_sua_huy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogSua.dismiss();
                            }
                        });
                        btn_sua_xacNhan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int ID = list.get(position).getID();
                                Sqlite database = new Sqlite(context);
                                database.QueryData("UPDATE Sach SET Ten = '"+edt_ten.getText().toString()+"'WHERE ID = '"+ID+"' ");
                                database.QueryData("UPDATE Sach SET TheLoai = '"+edt_theLoai.getText().toString()+"'WHERE ID = '"+ID+"' ");
                                database.QueryData("UPDATE Sach SET DonGia = '"+Double.parseDouble(edt_donGia.getText().toString())+"'WHERE ID = '"+ID+"' ");
                                database.QueryData("UPDATE Sach SET SoLuong = '"+Integer.parseInt(edt_soLuong.getText().toString())+"'WHERE ID = '"+ID+"' ");
                                ti.getData(list,context);
                                dialogSua.dismiss();
                                Toast.makeText(context,"Đã sửa thành công",Toast.LENGTH_LONG).show();
                            }
                        });
                        dialog.dismiss();
                    }
                });
                btn_xoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ti.xoaDataSach(position,list,context);
                        Toast.makeText(context,"Xóa thành công",Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                        Intent intent =  new Intent(context, quanLySachActivity.class);
                        context.startActivity(intent);
                    }
                });
                return false;
            }
        });

        return convertView;
    }
}
