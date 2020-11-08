package com.example.learningsupport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.learningsupport.Fragment.DangKyHocFragment;
import com.example.learningsupport.Fragment.KhoaHocFragment;
import com.example.learningsupport.Fragment.LichHocFragment;
import com.example.learningsupport.Fragment.LichThiFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CourseActivity extends AppCompatActivity {
    ViewPager pager;
    final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_course);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        KhoaHocFragment fragment2 = new KhoaHocFragment();
        fragmentTransaction.add(R.id.frameContext, fragment2);
        fragmentTransaction.commit();

        initView();

    }

    private void initView() {
        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            int a = item.getItemId();
                if(a==R.id.navigation_home) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    KhoaHocFragment fragment2 = new KhoaHocFragment();
                    fragmentTransaction.add(R.id.frameContext, fragment2);
                    fragmentTransaction.commit();
                    return true;
                }else if(a==R.id.navigation_dashboard) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    DangKyHocFragment fragment2 = new DangKyHocFragment();
                    fragmentTransaction.add(R.id.frameContext, fragment2);
                    fragmentTransaction.commit();
                    return true;
                }else if(a==R.id.navigation_notifications){
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    LichHocFragment fragment2 = new LichHocFragment();
                    fragmentTransaction.add(R.id.frameContext, fragment2);
                    fragmentTransaction.commit();
                    return true;
                }else if(a==R.id.navigation_date) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    LichThiFragment fragment2 = new LichThiFragment();
                    fragmentTransaction.add(R.id.frameContext, fragment2);
                    fragmentTransaction.commit();
                    return true;
                }
                return false;
            }
    };
}