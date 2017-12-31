package com.example.mixcloud;

import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.FileDescriptor;

public class PlayerIntentService extends IntentService {


    public PlayerIntentService() {
        super("PlayerIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // use AIDL
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new PlayerBinder();
    }


    public class PlayerBinder extends Binder {
        public PlayerIntentService getService() {
            return PlayerIntentService.this;
        }
    }

    public void loadWebView(WebView webview, String htmlDoc) {
        webview.setWebChromeClient(new WebChromeClient());
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setAppCacheEnabled(true);
        webview.loadDataWithBaseURL(null, htmlDoc, "text/html", "utf-8", null);;
    }
}
