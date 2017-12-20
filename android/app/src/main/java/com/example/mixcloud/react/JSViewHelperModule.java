package com.example.mixcloud.react;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.mixcloud.R;
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
    public void startNativeView(String activity, String token) {
        switch (activity) {

            case HOME:
                SharedPreferences.Editor sharedPreferences = getCurrentActivity().getSharedPreferences(getCurrentActivity()
                        .getString(R.string.app_name), Context.MODE_PRIVATE).edit();
                sharedPreferences.putString(getCurrentActivity().getString(R.string.token), token).commit();
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
