package com.example.duanmau.taiKhoan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.duanmau.R;
import com.example.duanmau.loginActivity;

public class quenPassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_pass);
        Button btn_quenPass = findViewById(R.id.btn_quenPass_quenPass);
        btn_quenPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(quenPassActivity.this, loginActivity.class);
                startActivity(intent);
            }
        });
    }
}