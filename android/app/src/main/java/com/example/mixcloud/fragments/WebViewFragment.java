package com.example.mixcloud.fragments;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.mixcloud.R;
import com.example.mixcloud.model.MetaData;
import com.example.mixcloud.services.PlayerIntentService;

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

        MetaData metaData = getArguments().getParcelable(TRACK_URL);

        play(metaData);
    }

    private void play(MetaData metaData) {
        ServiceConnection serviceConnection = new ServiceConnection() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                PlayerIntentService playerService = ((PlayerIntentService.PlayerBinder) service).getService();
                playerService.loadWebView((AppCompatActivity) getActivity(), webview, metaData.html());
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        Intent intent = new Intent(getActivity(), PlayerIntentService.class);
        getActivity().startService(intent);
        getActivity().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public static WebViewFragment newInstance(MetaData metaData) {
        WebViewFragment fragment = new WebViewFragment();
        fragment.setRetainInstance(true);
        Bundle bundle = new Bundle();
        bundle.putParcelable(TRACK_URL, metaData);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void setTrackUrl(MetaData metaData) {
        play(metaData);
    }
}
