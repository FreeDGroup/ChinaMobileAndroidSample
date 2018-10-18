# [Travelflan](https://www.travelflan.com/) Widget Android Usage Sample For China-mobile
![Platform](https://img.shields.io/badge/platform-Android-orange.svg)
![Languages](https://img.shields.io/badge/language-Java-orange.svg)

## Introduction

We provides the web for chatbot services.
Ultimately, we will recommend using it through the Native sdk.
Until then, we recommend using the webview module in your Android app.

## Quick Start

1. Insert [WebViewClientImpl](https://github.com/jiwoniy/ChinaMobileAndroidSample/blob/master/app/src/main/java/com/example/jiwonyoon/widgetandroidtest/WebViewClientImpl.java), [JavaScriptInterface](https://github.com/jiwoniy/ChinaMobileAndroidSample/blob/master/app/src/main/java/com/example/jiwonyoon/widgetandroidtest/JavaScriptInterface.java) source file in your application.
If you want to know more about how to use it, please refer to the official [Android document](https://developer.android.com/guide/webapps/webview)
2. Declare and implement your source code refer to the usage below in your Activity.

## Usage
```
// Declare Your Activity
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
```

In [JavaScriptInterface](https://github.com/jiwoniy/ChinaMobileAndroidSample/blob/master/app/src/main/java/com/example/jiwonyoon/widgetandroidtest/JavaScriptInterface.java) this script should be excute.
```
@Override
    public void onPageFinished(WebView view, String url) {
        String setScript = "window.webViewBridge.setEnv('android')";
        view.loadUrl("javascript:" + setScript);
        String script = "window.webViewBridge.send({type:'initialize', provider_id: 13})";
        view.loadUrl("javascript:" + script);
        super.onPageFinished(view, url);
    }
```
