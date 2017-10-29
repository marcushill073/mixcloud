package com.example.mixcloud;

import android.app.Activity;
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

import io.realm.Realm;
import io.realm.react.RealmReactPackage;

public class MixCloudApp extends Application implements ReactApplication {


    private static MixCloudApp app;

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return true;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage(),
                    new RealmReactPackage(),
                    new JSViewHelperPackage(),
                    new DispatchRequestPackage()
            );
        }
    };


    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, /* native exopackage */ false);
        Realm.init(this);
        app = this;
    }

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    public static MixCloudApp getApplicationContext(Activity activity) {
        return (MixCloudApp) activity.getApplicationContext();
    }

    public static MixCloudApp getApp() {
        return app;
    }
}
