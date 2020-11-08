package com.example.baithi;

import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    Sqlite sqlite;
    viTriDao viTriDao;
    List<ViTri> listViTri;

    Double kinh;
    Double vi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        anhXa();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button btn = findViewById(R.id.btn_add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapsActivity.this,ThemViTriActivity.class);
                startActivity(intent);
            }
        });
    }

    private void anhXa() {
        sqlite = new Sqlite(this);
        viTriDao = new viTriDao(sqlite);
        listViTri = viTriDao.getVT();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
            GoogleMap mMap = googleMap;
            LatLng sydney = new LatLng(kinh, vi);
            mMap.addMarker(new MarkerOptions().position(sydney).title("địa chỉ"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

}