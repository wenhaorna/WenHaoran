package com.app.workone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_web_view);
        WebView webView = (WebView) findViewById(R.id.webview);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        WebSettings settings = webView.getSettings();
         settings.setJavaScriptEnabled(true);
         settings.setJavaScriptCanOpenWindowsAutomatically(true);
         webView.loadUrl(url);
    }
}
