package com.example.learningsupport.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.learningsupport.R;


public class DangKyHocFragment extends Fragment {
    private View rootview;


    public DangKyHocFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_dang_ky_hoc,container,false);
        initView();
        return rootview;
    }

    private void initView() {
    }
}