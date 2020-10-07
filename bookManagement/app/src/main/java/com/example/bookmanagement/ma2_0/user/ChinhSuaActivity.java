package com.example.bookmanagement.ma2_0.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmanagement.R;
import com.example.bookmanagement.Sql.Database;
import com.example.bookmanagement.model.uses;

import java.util.ArrayList;

public class ChinhSuaActivity extends AppCompatActivity {

    final String DATABASE_NAME = "bookManagementData.sqlite";
    SQLiteDatabase database;
    ArrayList<uses> list = new ArrayList<>();
    SharedPreferences sha,sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua);
        readDataUses();
        suKien();
    }

    private void suKien() {
        final TextView tv_usesName = findViewById(R.id.edt_nguoiDung_chinhSua_usesName);
        final TextView tv_email = findViewById(R.id.edt_nguoiDung_chinhSua_email);

        sha =getSharedPreferences("uses",MODE_PRIVATE);
        final int id = sha.getInt("id",0);
        int index = 0;
        for (int i=0;i<list.size();i++){
            if(list.get(i).getIdUses()==id){
                index = i;
            }

        }

        tv_usesName.setText(list.get(index).getUsesName());
        tv_email.setText(list.get(index).getGmail());


        Button btn_huy = findViewById(R.id.btn_nguoiDung_chinhSua_huy);
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btn_chinhSua = findViewById(R.id.btn_nguoiDung_chinhSua_xacNhan);
        final int finalIndex = index;
        btn_chinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean ktraName=true;
                boolean ktraEmail=true;
                for (int i=0;i<list.size();i++) {
                    if (list.get(i).getUsesName().equals(tv_usesName.getText().toString())) {
                        ktraName = false;
                    }
                    if (list.get(i).getGmail().equals(tv_email.getText().toString())) {
                        ktraEmail = false;
                    }
                }
                if(tv_usesName.getText().toString().equals(list.get(finalIndex).getUsesName())){
                    ktraName = true;
                }
                if(tv_email.getText().toString().equals(list.get(finalIndex).getGmail())){
                    ktraEmail = true;
                }
                if(ktraName) {
                    if(ktraEmail) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("idUses", id);
                        contentValues.put("usesName", tv_usesName.getText().toString());
                        contentValues.put("password", list.get(finalIndex).getPassword());
                        contentValues.put("email", tv_email.getText().toString());
                        SQLiteDatabase database = Database.initDatabase(ChinhSuaActivity.this, DATABASE_NAME);
                        database.update("uses", contentValues, "idUses = " + id, null);

                        sharedPreferences = getSharedPreferences("phong", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("userName", tv_usesName.getText().toString());
                        sha = getSharedPreferences("uses", MODE_PRIVATE);
                        SharedPreferences.Editor edi = sha.edit();
                        edi.putString("email", tv_email.getText().toString());
                        edi.commit();

                        Intent intent = new Intent(ChinhSuaActivity.this, nguoiDungActivity.class);
                        startActivity(intent);
                        Toast.makeText(ChinhSuaActivity.this,"Chỉnh sửa thành công",Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(ChinhSuaActivity.this,"Email đã tồn tại",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(ChinhSuaActivity.this,"Tên đăng nhập đã tồn tại",Toast.LENGTH_LONG).show();
                }
            }

        });
    }
    private void readDataUses() {
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM uses", null);
        list.clear();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int ID = cursor.getInt(0);
            String Name = cursor.getString(1);
            String pass = cursor.getString(2);
            String email = cursor.getString(3);
            list.add(new uses(ID, Name, pass,email));
            cursor.moveToNext();
        }
        cursor.close();
    }

}