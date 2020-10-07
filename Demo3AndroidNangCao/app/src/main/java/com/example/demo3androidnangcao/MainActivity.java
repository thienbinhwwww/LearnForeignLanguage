package com.example.demo3androidnangcao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvContact = findViewById(R.id.lvContact);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 999) {
            if (ContextCompat.checkSelfPermission(
                    MainActivity.this, Manifest.permission.READ_CONTACTS)
                    == PackageManager.PERMISSION_GRANTED) {
                layDanhBa();
            } else {
                Toast.makeText(getApplicationContext(), "Cần cấp quyền để xem danh bạ", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == 888) {
            if (ContextCompat.checkSelfPermission(
                    MainActivity.this, Manifest.permission.READ_CALL_LOG)
                    == PackageManager.PERMISSION_GRANTED) {
                layCallLog();
            } else {
                Toast.makeText(getApplicationContext(), "Cần cấp quyền để xem danh bạ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void getContact(View view) {
        Log.e("sa","aa");
        layDanhBa();
        Log.e("sa","ab");
    }


    public void getCallLog(View view) {
        if (ContextCompat.checkSelfPermission(
                MainActivity.this, Manifest.permission.READ_CALL_LOG)
                == PackageManager.PERMISSION_GRANTED) {
            layCallLog();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.READ_CALL_LOG, Manifest.permission.WRITE_CALL_LOG}, 888);
        }

    }


    //    public void getMediaStore(View view) {
//
//    }
    public void layDanhBa() {
        List<String> list = new ArrayList<>();
        Uri uri = Uri.parse("content://contacts/people");
        CursorLoader cursorLoader =
                new CursorLoader(this, uri, null, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            String s = "";
            String cotID = ContactsContract.Contacts._ID;
            int hangID = cursor.getColumnIndex(cotID);
            s += cursor.getString(hangID);
            String cotTen = ContactsContract.Contacts.DISPLAY_NAME;
            int hangTen = cursor.getColumnIndex(cotTen);
            s += " - ";
            s += cursor.getString(hangTen);
            list.add(s);
            cursor.moveToNext();
        }
        cursor.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        lvContact.setAdapter(adapter);
    }


    public void layCallLog() {
        ArrayList<String> list = new ArrayList<>();
        Uri uri = CallLog.Calls.CONTENT_URI;
        CursorLoader cursorLoader =
                new CursorLoader(this, uri, null, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String date = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE));
            String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
            String duration = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DURATION));
            Log.e("Data", date + ": " + number + ": " + duration);
            String s = "";
            String cotDate = CallLog.Calls.DATE;
            int hangDate = cursor.getColumnIndex(cotDate);
            s += cursor.getString(hangDate);
            String cotNumber = CallLog.Calls.NUMBER;
            int hangNumber = cursor.getColumnIndex(cotNumber);
            s += " - ";
            s += cursor.getString(hangNumber);
            String cotDuration = CallLog.Calls.DURATION;
            int hangDuration = cursor.getColumnIndex(cotDuration);
            s += " - ";
            s += cursor.getString(hangDuration);
            cursor.moveToNext();
            list.add(s);
        }
        cursor.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        lvContact.setAdapter(adapter);
    }
}


