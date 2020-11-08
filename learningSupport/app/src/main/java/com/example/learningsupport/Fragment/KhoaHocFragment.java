package com.example.learningsupport.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.learningsupport.R;


public class KhoaHocFragment extends Fragment {
    private View rootview;
    Button btnView;

    public KhoaHocFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_khoa_hoc,container,false);
        initView();
        return rootview;
    }

    private void initView() {
        btnView = rootview.findViewById(R.id.btn_view_khoaHoc);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }
}