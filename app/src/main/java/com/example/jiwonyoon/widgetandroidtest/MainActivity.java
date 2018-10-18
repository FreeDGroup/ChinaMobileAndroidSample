package com.example.jiwonyoon.widgetandroidtest;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.view.View;

public class MainActivity extends Activity {
    private WebView tfWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // set Widget WebView
        setTFWebView();

        // This is for controle webview
        final Button button = findViewById(R.id.button_id);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(tfWebView.getVisibility() == View.GONE) {
                    tfWebView.setVisibility(View.VISIBLE);
                } else {
                    tfWebView.setVisibility(View.GONE);
                }
            }
        });
    }

    private void setTFWebView() {
        tfWebView = (WebView) findViewById(R.id.webview);

        // allow javascript
        WebSettings webSettings = tfWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        WebViewClientImpl webViewClient = new WebViewClientImpl(this);
        tfWebView.setVisibility(View.GONE);
        tfWebView.setWebViewClient(webViewClient);
        tfWebView.addJavascriptInterface(new JavaScriptInterface(this, tfWebView),"androidHandler");
        tfWebView.loadUrl("https://widget-cm.travelflan.com.cn");
    }

    @Override
    public void onBackPressed() {
        // block back button
        if(tfWebView.canGoBack()) {
            tfWebView.goBack();
        } else {
            // define your task!
//            super.onBackPressed();
        }
    }
}
