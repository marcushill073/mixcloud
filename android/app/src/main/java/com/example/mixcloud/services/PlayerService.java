package com.example.mixcloud.services;

import android.os.IBinder;
import android.webkit.WebView;


public interface PlayerService  {

     void loadDataWithBaseURL(WebView webView, String htmlDoc);
}
