package com.example.mixcloud.activities;

import android.app.Activity;
import android.os.Bundle;

import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactNativeHost;

import javax.annotation.Nullable;

public class CustomAppDelegate extends ReactActivityDelegate{
    public CustomAppDelegate(Activity activity, @Nullable String mainComponentName) {
        super(activity, mainComponentName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void loadApp(String appKey) {
        super.loadApp(appKey);
    }

    @Override
    protected ReactNativeHost getReactNativeHost() {
        return super.getReactNativeHost();
    }
}
