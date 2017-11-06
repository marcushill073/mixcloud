package com.example.mixcloud;

import android.app.Application;

import com.example.mixcloud.modules.AppModule;
import com.example.mixcloud.modules.DaggerDataComponent;
import com.example.mixcloud.modules.DataComponent;
import com.example.mixcloud.modules.ReactManagerInstanceModule;
import com.example.mixcloud.modules.UserModule;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.soloader.SoLoader;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MixCloudApp extends Application implements ReactApplication{

    @Inject
    ReactNativeHost reactNativeHost;

    static DataComponent dataComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, /* native exopackage */ false);
       Realm.init(this);

        dataComponent = DaggerDataComponent.builder()
                .userModule(new UserModule())
                .appModule(new AppModule(this))
                .reactManagerInstanceModule(new ReactManagerInstanceModule(this))
                .build();
        dataComponent.inject(this);
    }

    public static DataComponent getDataComponent() {
        return dataComponent;
    }

    @Override
    public ReactNativeHost getReactNativeHost() {
        return reactNativeHost;
    }
}
