package com.example.gameshow.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gameshow.PrepareGameActivity;
import com.example.gameshow.R;

public class FuntionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funtions);
    }

    public void join(View view){
        Intent intent = new Intent(FuntionsActivity.this, GameActivity.class);
        startActivity(intent);
    }

    public void click(View view){
        Intent intent = new Intent(FuntionsActivity.this, PrepareGameActivity.class);
        startActivity(intent);
    }
}