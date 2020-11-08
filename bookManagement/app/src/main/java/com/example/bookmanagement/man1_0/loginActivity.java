package com.example.bookmanagement.man1_0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmanagement.R;
import com.example.bookmanagement.Sql.Database;
import com.example.bookmanagement.Sql.Sqlite;
import com.example.bookmanagement.Sql.usesDao;
import com.example.bookmanagement.ma2_0.menuActivity;
import com.example.bookmanagement.model.uses;

import java.util.ArrayList;
import java.util.List;

public class loginActivity extends AppCompatActivity {
    final String DATABASE_NAME = "bookManagementData.sqlite";
    SQLiteDatabase database;
    List<uses> list = new ArrayList<>();
    final Sqlite sqlite= new Sqlite(this);
    final usesDao usesDao = new usesDao(sqlite);

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        list = usesDao.getAllUsers();


        su_kien();
    }



    private void su_kien() {
        Button btn_login = findViewById(R.id.btn_login_login);
        Button btn_dangKy = findViewById(R.id.btn_login_dangKy);
        final EditText edtName = findViewById(R.id.edt_login_tenDangNhap);
        final EditText edtPass = findViewById(R.id.edt_login_pass);
        final CheckBox cb_pass = findViewById(R.id.cb_login_nhoPass);
        TextView tv_quenPass = findViewById(R.id.tv_login_quenPass);
        sharedPreferences =getSharedPreferences("phong",MODE_PRIVATE);

        boolean cb = sharedPreferences.getBoolean("save",false);
        if(cb){
            String user = sharedPreferences.getString("userName","");
            String pass = sharedPreferences.getString("password","");
            edtName.setText(user);
            edtPass.setText(pass);
        }
        cb_pass.setChecked(cb);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences =getSharedPreferences("phong",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String user = edtName.getText().toString();
                String pass = edtPass.getText().toString();
                boolean luu = cb_pass.isChecked();
                if (!luu) {
                    editor.clear();
                } else {
                    editor.putString("userName", user);
                    editor.putString("password", pass);
                    editor.putBoolean("save", luu);
                }
                editor.commit();

                sharedPreferences =getSharedPreferences("uses",MODE_PRIVATE);
                SharedPreferences.Editor edi = sharedPreferences.edit();
                boolean ktra = true;
                for (int i = 0;i<list.size();i++){
                    if((edtName.getText().toString().equals(list.get(i).getUsesName()))&&(edtPass.getText().toString().equals(list.get(i).getPassword()))){
                        Intent intent =  new Intent(loginActivity.this, menuActivity.class);
                        startActivity(intent);
                        edi.putInt("id",list.get(i).getIdUses());
                        edi.putString("email",list.get(i).getGmail());
                        edi.commit();
                        Toast.makeText(loginActivity.this,"Đăng nhập thành công",Toast.LENGTH_LONG).show();
                        ktra=false;
                    }
                }
                if(ktra){
                    Toast.makeText(loginActivity.this,"Đăng nhập thất bại",Toast.LENGTH_LONG).show();
                    edi.clear();
                }
            }
        });
        btn_dangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(loginActivity.this, dangKyActivity.class);
                startActivity(intent);
            }
        });
        tv_quenPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(loginActivity.this, quenPassActivity.class);
                startActivity(intent);
            }
        });


    }

}