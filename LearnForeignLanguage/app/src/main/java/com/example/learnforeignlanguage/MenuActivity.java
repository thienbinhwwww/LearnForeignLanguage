package com.example.learnforeignlanguage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        AnhXa();
    }

    //Ánh xạ các biến
    private void AnhXa() {

    }

//    Ân vào phần biểu tượng người dùng
    public void clickUser(View view){
        Intent intent = new Intent(MenuActivity.this,UsesActivity.class);
        startActivity(intent);
    }

//    Chức năng bài học chủ đề
    public void baiHocChuDe(View view){
        Intent intent = new Intent(MenuActivity.this,UsesActivity.class);
        startActivity(intent);
    }

//    Chức năng bài học tự chọn
    public void  baiHocTuChon(View view){
        Intent intent = new Intent(MenuActivity.this,UsesActivity.class);
        startActivity(intent);
    }

//    Chức năng lịch sử học
    public void lichSuHoc(View view){
        Intent intent = new Intent(MenuActivity.this,UsesActivity.class);
        startActivity(intent);
    }
}