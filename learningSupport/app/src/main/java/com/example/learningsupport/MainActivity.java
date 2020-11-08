package com.example.learningsupport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout layoutCourse = findViewById(R.id.Layout_main_Course);
        ConstraintLayout layoutMap = findViewById(R.id.Layout_main_maps);
        ConstraintLayout layoutNew = findViewById(R.id.Layout_main_news);
        ConstraintLayout layoutSocial = findViewById(R.id.Layout_main_social);

        layoutCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CourseActivity.class);
                startActivity(intent);
            }
        });
        layoutMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });
        layoutNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NewActivity.class);
                startActivity(intent);
            }
        });
        layoutSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SocialActivity.class);
                startActivity(intent);
            }
        });
    }
}