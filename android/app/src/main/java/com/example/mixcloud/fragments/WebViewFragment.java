package com.example.mixcloud.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.mixcloud.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewFragment extends Fragment {

    private static final String TRACK_URL = "url";
    @BindView(R.id.webview)
    public WebView webview;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.activity_webview, container, false);
            ButterKnife.bind(this, view);
            return view;

        } else {
            return view;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        webview.setWebChromeClient(new WebChromeClient());
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return super.shouldInterceptRequest(view, request);
            }
        });
        Log.d("track url", getArguments().getString(TRACK_URL));
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(getArguments().getString(TRACK_URL));

    }

    public static WebViewFragment newInstance(String url) {
        WebViewFragment fragment = new WebViewFragment();
        fragment.setRetainInstance(true);
        Bundle bundle = new Bundle();
        bundle.putString(TRACK_URL, url);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void setTrackUrl(String url) {
        webview.loadUrl(url);
    }
}
