package com.example.mixcloud;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.IntentService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.MediaSessionManager;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.mixcloud.activities.HomeActivity;

import java.io.FileDescriptor;
import java.util.Iterator;
import java.util.List;

import static com.facebook.react.common.ReactConstants.TAG;

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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
