package com.example.mixcloud.activities;

import android.os.Bundle;
import android.util.Log;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.ReactContext;

import javax.annotation.Nullable;

public abstract class BaseReactActivity extends ReactActivity
        implements ReactInstanceManager.ReactInstanceEventListener {

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     * e.g. "MoviesApp"
     */
    protected  @Nullable abstract String getMainComponentName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getReactInstanceManager().removeReactInstanceEventListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getReactInstanceManager().addReactInstanceEventListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onReactContextInitialized(ReactContext context) {
        Log.d(BaseReactActivity.class.getName(), "reactcontext initialized");
    }
}