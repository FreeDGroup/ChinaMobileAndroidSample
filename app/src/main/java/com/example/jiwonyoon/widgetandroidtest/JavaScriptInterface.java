package com.example.jiwonyoon.widgetandroidtest;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;

public class JavaScriptInterface {
    protected MainActivity parentActivity;
    protected WebView mWebView;


    public JavaScriptInterface(MainActivity _activity, WebView _webView)  {
        parentActivity = _activity;
        mWebView = _webView;
    }

    @JavascriptInterface
    public void messageHandlers(String msg){
        // can communication with web
    }
}
