package com.example.bookmanagement.man1_0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookmanagement.R;
import com.example.bookmanagement.Sql.Database;
import com.example.bookmanagement.Sql.Sqlite;
import com.example.bookmanagement.Sql.usesDao;
import com.example.bookmanagement.model.book;
import com.example.bookmanagement.model.uses;

import java.util.ArrayList;
import java.util.List;


public class dangKyActivity extends AppCompatActivity {
    final String DATABASE_NAME = "bookManagementData.sqlite";
    SQLiteDatabase database;
    List<uses> list = new ArrayList<>();
    final Sqlite sqlite= new Sqlite(this);;
    final com.example.bookmanagement.Sql.usesDao usesDao = new usesDao(sqlite);
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        list = usesDao.getAllUsers();

    }

    public void add(View view){
        EditText edt_id = findViewById(R.id.edt_dangKy_id);
        EditText edt_usesName = findViewById(R.id.edt_dangKy_tenDangNhap);
        EditText edt_pass = findViewById(R.id.edt_dangKy_pass);
        EditText edt_pass_lai = findViewById(R.id.edt_dangKy_passLai);
        EditText edt_gmail = findViewById(R.id.edt_dangKy_gmail);

        boolean dieuKien = true;

        if(edt_usesName.getText().toString().length()==0||edt_pass.getText().toString().length()==0||edt_pass_lai.getText().toString().length()==0){
            Toast.makeText(dangKyActivity.this,"Không để trống Tên đăng nhập và maatk khẩu",Toast.LENGTH_LONG).show();
            dieuKien = false;
        }
        if(!edt_pass.getText().toString().equals(edt_pass_lai.getText().toString())){
            Toast.makeText(dangKyActivity.this,"Mật khẩu không trùng khớp, mời nhập lại",Toast.LENGTH_LONG).show();
            dieuKien = false;
        }
        for (int i=0;i<list.size();i++){
            if(list.get(i).getUsesName().equals(edt_usesName.getText().toString())){
                Toast.makeText(dangKyActivity.this,"Tên đăng nhập đã tồn tại",Toast.LENGTH_LONG).show();
                dieuKien = false;
            }
            if(list.get(i).getGmail().equals(edt_gmail.getText().toString())){
                Toast.makeText(dangKyActivity.this,"email đã tồn tại",Toast.LENGTH_LONG).show();
                dieuKien = false;
            }
            if(list.get(i).getIdUses()== Integer.parseInt(edt_id.getText().toString())){
                Toast.makeText(dangKyActivity.this,"ID đã tồn tại",Toast.LENGTH_LONG).show();
                dieuKien = false;
            }
        }

        if (dieuKien){

            int id = Integer.parseInt(edt_id.getText().toString());
            String name = edt_usesName.getText().toString();
            String pass = edt_pass.getText().toString();
            String email = edt_gmail.getText().toString();

            boolean addTemp = usesDao.addUser(new uses(id,name,pass,email));

            if (addTemp){
                list = usesDao.getAllUsers();
                Toast.makeText(dangKyActivity.this,"Đăng ký thành công",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this,loginActivity.class);
                sharedPreferences =getSharedPreferences("phong",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String user = edt_usesName.getText().toString();
                String passt = edt_pass.getText().toString();
                editor.putString("userName", user);
                editor.putString("password", passt);
                editor.putBoolean("save", true);
                editor.commit();
                startActivity(intent);
            }
        }
    }

}