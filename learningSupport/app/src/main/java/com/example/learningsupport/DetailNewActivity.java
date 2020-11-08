package com.example.learningsupport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DetailNewActivity extends AppCompatActivity {
    WebView webView;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_new);

        intent = getIntent();
        String link = intent.getStringExtra("linkURL");

        webView = findViewById(R.id.webView_detailNew);
        webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient());
    }
}