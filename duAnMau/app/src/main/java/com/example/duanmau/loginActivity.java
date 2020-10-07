package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.taiKhoan.dangKyActivity;
import com.example.duanmau.taiKhoan.quenPassActivity;

public class loginActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        su_kien();
    }

    private void su_kien() {
        Button btn_login = findViewById(R.id.btn_login_login);
        Button btn_dangKy = findViewById(R.id.btn_login_dangKy);
        TextView tv_quenpass = findViewById(R.id.tv_login_quenPass);
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
                if((edtName.getText().toString().equals("admin"))&&(edtPass.getText().toString().equals("admin"))){
                    Intent intent =  new Intent(loginActivity.this, mainActivity.class);
                    startActivity(intent);
                    Toast.makeText(loginActivity.this,"Đăng nhập thành công",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(loginActivity.this,"Đăng nhập thất bại",Toast.LENGTH_LONG).show();
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