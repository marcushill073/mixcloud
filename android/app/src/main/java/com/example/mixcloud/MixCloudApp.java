package com.example.mixcloud;

import android.app.Application;

import com.example.mixcloud.react.DispatchRequestPackage;
import com.example.mixcloud.react.JSViewHelperPackage;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import java.util.Arrays;
import java.util.List;

public class MixCloudApp extends Application implements ReactApplication {

    public ReactNativeHost reactNativeHost;

    @Override
    public void onCreate() {
        super.onCreate();
        reactNativeHost = new ReactNativeHost(this) {
            @Override
            public boolean getUseDeveloperSupport() {
                return true;
            }

            @Override
            protected List<ReactPackage> getPackages() {
                return Arrays.<ReactPackage>asList(
                        new MainReactPackage(),
                        new JSViewHelperPackage(),
                        new DispatchRequestPackage()
                );
            }
        };
        SoLoader.init(this, /* native exopackage */ false);

    }

    @Override
    public ReactNativeHost getReactNativeHost() {
        return reactNativeHost;
    }

}
