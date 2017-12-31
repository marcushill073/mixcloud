package com.example.mixcloud;

import android.os.IBinder;
import android.webkit.WebView;


public interface PlayerService  {

     void loadDataWithBaseURL(WebView webView, String htmlDoc);
}
