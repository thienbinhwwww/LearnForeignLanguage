package com.example.bookmanagement.man1_0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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
import com.example.bookmanagement.model.uses;

import java.util.ArrayList;
import java.util.List;

public class quenPassActivity extends AppCompatActivity {
    final String DATABASE_NAME = "bookManagementData.sqlite";
    SQLiteDatabase database;
    List<uses> list = new ArrayList<>();
    final Sqlite sqlite= new Sqlite(this);;
    final com.example.bookmanagement.Sql.usesDao usesDao = new usesDao(sqlite);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_pass);
        list = usesDao.getAllUsers();
    }

    public void layPass(View view){
        EditText edt_usesName = findViewById(R.id.edt_quenPass_tenDangNhap);
        EditText edt_pass = findViewById(R.id.edt_quenPass_pass);
        EditText edt_pass_lai = findViewById(R.id.edt_quenPass_passLai);
        EditText edt_gmail = findViewById(R.id.edt_quenPass_gmail);

        boolean dieuKien = true;

        if(edt_usesName.getText().toString().length()==0||edt_pass.getText().toString().length()==0||edt_pass_lai.getText().toString().length()==0||edt_gmail.getText().toString().length()==0){
            Toast.makeText(quenPassActivity.this,"Không để trống các ô nhập",Toast.LENGTH_LONG).show();
            dieuKien = false;
        }
        if(!edt_pass.getText().toString().equals(edt_pass_lai.getText().toString())){
            Toast.makeText(quenPassActivity.this,"Mật khẩu không trùng khớp, mời nhập lại",Toast.LENGTH_LONG).show();
            dieuKien = false;
        }
        int usesID = 0;
        int index = 0;
        for (int i=0;i<list.size();i++){
            if(!list.get(i).getUsesName().equals(edt_usesName.getText().toString())){
                Toast.makeText(quenPassActivity.this,"Tên đăng nhập không tồn tại",Toast.LENGTH_LONG).show();
                dieuKien = false;
            }else {
                usesID = list.get(i).getIdUses();
                index = i;
            }
            if(!list.get(i).getGmail().equals(edt_gmail.getText().toString())){
                Toast.makeText(quenPassActivity.this,"Emai không tồn tại",Toast.LENGTH_LONG).show();
                dieuKien = false;
            }
        }
        if(dieuKien){
            list.get(index).setPassword(edt_pass.getText().toString());

            boolean dkTemp = usesDao.updateUses(new uses(usesID,list.get(index).getUsesName(),list.get(index).getPassword(),list.get(index).getGmail()));
            if (dkTemp){
                Toast.makeText(quenPassActivity.this,"Lấy lại mật khẩu thành công",Toast.LENGTH_LONG).show();
            }
        }
    }

}