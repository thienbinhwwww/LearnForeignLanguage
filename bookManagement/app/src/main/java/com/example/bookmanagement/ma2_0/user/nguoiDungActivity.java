package com.example.bookmanagement.ma2_0.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bookmanagement.R;
import com.example.bookmanagement.ma2_0.menuActivity;

public class nguoiDungActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences sha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);

        suKien();
    }

    private void suKien(){
        TextView tv_id = findViewById(R.id.tv_nguoiDung_id);
        TextView tv_gamil = findViewById(R.id.tv_nguoiDung_gmail);
        TextView tv_name = findViewById(R.id.tv_nguoiDung_tenND);
        sharedPreferences =getSharedPreferences("uses",MODE_PRIVATE);
        sha =getSharedPreferences("phong",MODE_PRIVATE);
        int id = sharedPreferences.getInt("id",0);
        String userName = sha.getString("userName","");
        String email = sharedPreferences.getString("email","");

        tv_id.setText(String.valueOf(id));
        tv_gamil.setText(email);
        tv_name.setText(userName);

        Button btn_sua = findViewById(R.id.btn_nguoiDung_chinhSua);
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(nguoiDungActivity.this,ChinhSuaActivity.class);
                startActivity(intent);
            }
        });

        Button btn_doipass = findViewById(R.id.btn_nguoiDung_doiPass);
        btn_doipass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(nguoiDungActivity.this,doiPasswordActivity.class);
                startActivity(intent);
            }
        });

        Button btn_huy = findViewById(R.id.btn_nguoiDung_huy);
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(nguoiDungActivity.this, menuActivity.class);
                startActivity(intent);
            }
        });
    }

}