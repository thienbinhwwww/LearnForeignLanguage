package com.example.bookmanagement.ma2_0.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmanagement.R;
import com.example.bookmanagement.Sql.Database;
import com.example.bookmanagement.model.uses;

import java.util.ArrayList;

public class doiPasswordActivity extends AppCompatActivity {
    final String DATABASE_NAME = "bookManagementData.sqlite";
    SQLiteDatabase database;
    ArrayList<uses> list = new ArrayList<>();
    SharedPreferences sha,sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_password);
        readDataUses();
        suKien();
    }

    private void suKien() {
        Button btn_doiPass = findViewById(R.id.btn_nguoidung_doiPass_xacNhan);
        btn_doiPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv_pass = findViewById(R.id.edt_nguoiDung_doiPass_pass);
                TextView tv_passMoi = findViewById(R.id.edt_nguoiDung_doiPass_passMoi);
                TextView tv_passMoiLai = findViewById(R.id.edt_nguoiDung_doiPass_passMoiLai);

                sha =getSharedPreferences("uses",MODE_PRIVATE);
                final int id = sha.getInt("id",0);
                int index = 0;
                for (int i=0;i<list.size();i++){
                    if(list.get(i).getIdUses()==id){
                        index = i;
                    }
                }

                boolean ktra = true;

                if(!tv_pass.getText().toString().equals(list.get(index).getPassword())){
                    Toast.makeText(doiPasswordActivity.this,"Sai mật khẩu, xin mời nhập lại",Toast.LENGTH_LONG).show();
                    ktra = false;
                }
                if(!tv_passMoi.getText().toString().equals(tv_passMoiLai.getText().toString())){
                    Toast.makeText(doiPasswordActivity.this,"Mật khẩu mới không trùng khớp",Toast.LENGTH_LONG).show();
                    ktra = false;
                }
                if (ktra){
                    list.get(index).setPassword(tv_passMoi.getText().toString());
                    Toast.makeText(doiPasswordActivity.this,"Đã đổi mật khẩu thành công",Toast.LENGTH_LONG).show();
                }

            }
        });
        Button btn_huy = findViewById(R.id.btn_nguoiDung_doiPass_huy);
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void readDataUses() {
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM uses", null);
        list.clear();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int ID = cursor.getInt(0);
            String Name = cursor.getString(1);
            String pass = cursor.getString(2);
            String email = cursor.getString(3);
            list.add(new uses(ID, Name, pass,email));
            cursor.moveToNext();
        }
        cursor.close();
    }

}