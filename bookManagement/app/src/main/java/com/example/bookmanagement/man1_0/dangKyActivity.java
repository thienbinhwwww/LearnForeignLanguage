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
import com.example.bookmanagement.model.uses;

import java.util.ArrayList;


public class dangKyActivity extends AppCompatActivity {
    final String DATABASE_NAME = "bookManagementData.sqlite";
    SQLiteDatabase database;
    ArrayList<uses> list = new ArrayList<>();
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        readDataUses();

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
            ContentValues contentValues = new ContentValues();
            contentValues.put("idUses",Integer.parseInt(edt_id.getText().toString()));
            contentValues.put("usesName",edt_usesName.getText().toString());
            contentValues.put("password",edt_pass.getText().toString());
            contentValues.put("email",edt_gmail.getText().toString());

            SQLiteDatabase database = Database.initDatabase(this,DATABASE_NAME);
            database.insert("uses",null,contentValues);

            readDataUses();
            Toast.makeText(dangKyActivity.this,"Đăng ký thành công",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,loginActivity.class);

            sharedPreferences =getSharedPreferences("phong",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String user = edt_usesName.getText().toString();
            String pass = edt_pass.getText().toString();
            editor.putString("userName", user);
            editor.putString("password", pass);
            editor.putBoolean("save", true);
            editor.commit();
            startActivity(intent);
        }
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