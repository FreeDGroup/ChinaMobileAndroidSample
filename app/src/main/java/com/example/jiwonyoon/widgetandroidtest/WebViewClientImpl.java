package com.example.jiwonyoon.widgetandroidtest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewClientImpl extends WebViewClient {

    private Activity activity = null;

    public WebViewClientImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        String setScript = "window.webViewBridge.setEnv('android')";
        view.loadUrl("javascript:" + setScript);
        String script = "window.webViewBridge.send({type:'initialize', provider_id: 13})";
        view.loadUrl("javascript:" + script);
        super.onPageFinished(view, url);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        // handle https://widget-cm.travelflan.com.cn url only
        if(url.indexOf("https://widget-cm.travelflan.com.cn") > -1 ) return false;

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(intent);
        return true;
    }

}