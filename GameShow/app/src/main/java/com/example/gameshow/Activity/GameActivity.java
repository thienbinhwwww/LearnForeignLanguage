package com.example.gameshow.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gameshow.R;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    public void createNewTeam(View view){
        Intent intent = new Intent(GameActivity.this, CreateNewTeamActivity.class);
        startActivity(intent);
    }
}