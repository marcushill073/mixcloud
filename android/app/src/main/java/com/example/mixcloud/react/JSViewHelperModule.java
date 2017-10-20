package com.example.mixcloud.react;

import android.content.Intent;

import com.example.mixcloud.activities.HomeActivity;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;


import timber.log.Timber;


public class JSViewHelperModule extends ReactContextBaseJavaModule {

    private static final String HOME = "home";

    public JSViewHelperModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "JSViewHelperModule";
    }

    @ReactMethod
    public void startNativeView(String activity) {
        switch (activity) {

            case HOME:
                Intent intent = new Intent(getCurrentActivity(), HomeActivity.class);
                getCurrentActivity().startActivity(intent);
                getCurrentActivity().finish();
                break;
            default:
                Timber.e("Unknown Activity");
                break;
        }
    }

}
