package com.example.mixcloud.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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

    public void loadWebView(AppCompatActivity context, WebView webview, String htmlDoc) {
        webview.setWebChromeClient(new WebChromeClient());
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setAppCacheEnabled(true);

        Log.d(PlayerIntentService.class.getSimpleName(), htmlDoc.replace("%2F&amp;hide_cover=1","%2F&amp;hide_cover=1&amp;hide_tracklist=1&amp;mini=1&amp;light=0"));
        webview.loadDataWithBaseURL(null, htmlDoc.replace("height=\"120\"", "height=\"60\"").replace("%2F&amp;hide_cover=1","%2F&amp;hide_cover=1&amp;hide_tracklist=1&amp;mini=1&amp;light=0"), "text/html", "utf-8", null);

    }

    @Override
    public int checkPermission(String permission, int pid, int uid) {
        return super.checkPermission(permission, pid, uid);
    }
}
