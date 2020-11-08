package com.example.bookmanagement.ma2_0.quanLySach;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
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

public class quaLySachAdapter extends BaseAdapter {
    List<book> listBook;
    List<category> listCategory;
    Context context;


    public quaLySachAdapter(List<book> listBook,List<category> listCategory, Context context) {
        this.listBook = listBook;
        this.listCategory = listCategory;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listBook.size();
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_view_quan_ly_sach,parent,false);
        TextView tv_ten = convertView.findViewById(R.id.tv_sach_tenSach);
        TextView tv_theLoai = convertView.findViewById(R.id.tv_sach_theLoai);
        TextView tv_donGia = convertView.findViewById(R.id.tv_sach_donGia);
        TextView tv_soLuong = convertView.findViewById(R.id.tv_sach_soLuong);
        Sqlite sqlite = new Sqlite(context);
        final bookDao bookDao = new bookDao(sqlite);
        final categoryDao categoryDao = new categoryDao(sqlite);


        final book book = listBook.get(position);
        String categoryT = null;
        for (int i = 0;i<listCategory.size();i++){
            if (book.getIdCategory()==listCategory.get(i).getIdCategory()){
                categoryT = listCategory.get(i).getCategory();
            }
        }


        tv_ten.setText(book.getBookName());
        tv_theLoai.setText(categoryT);
        tv_donGia.setText(String.valueOf(book.getUnitPrice()));
        tv_soLuong.setText(String.valueOf(book.getAmount()));

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

                        Log.e("pos", String.valueOf(position));

                        int indexCategory=0;
                        for (int i=0;i<listCategory.size();i++){
                            if(listCategory.get(i).getCategory().equals(edt_theLoai.getText().toString())){
                                indexCategory = i;
                            }
                        }

                        final String ten = listBook.get(position).getBookName();
                        String theLoai = listCategory.get(indexCategory).getCategory();
                        Double donGia = listBook.get(position).getUnitPrice();
                        int soLuong = listBook.get(position).getAmount();
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
                                book book =  new book();
                                boolean DK = true;
                                int temp=0;
                                for (int i=0;i<listCategory.size();i++){
                                    if((listCategory.get(i).getCategory().equals(edt_theLoai.getText().toString()))){
                                        book.setIdCategory(listCategory.get(i).getIdCategory());
                                        DK=false;
                                        break;
                                    }
                                }

                                boolean ktra=true;
                                while (DK){
                                    for (int i=0;i<listCategory.size();i++) {
                                        if (temp == listCategory.get(i).getIdCategory() ){
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

                                book.setIdBook((listBook.get(position).getIdBook()));
                                book.setBookName(edt_ten.getText().toString());
                                book.setUnitPrice(Double.parseDouble(edt_donGia.getText().toString()));
                                book.setAmount(Integer.parseInt(edt_soLuong.getText().toString()));

                                bookDao.updateBook(book);
                                listBook = bookDao.getAllBook();
                                listCategory = categoryDao.getAllCategory();
                                dialogSua.dismiss();
                                Toast.makeText(context,"Sửa thành công",Toast.LENGTH_LONG).show();
                            }
                        });
                        dialog.dismiss();
                    }
                });
                btn_xoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean ktTemp = bookDao.deBook(listBook.get(position).getIdBook());

                        if(ktTemp) {
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                            listBook = bookDao.getAllBook();
                            Intent intent = new Intent(context,quanLySachActivity.class);
                            context.startActivity(intent);
                        }
                    }
                });
                return false;
            }
        });
        return convertView;
    }
}
