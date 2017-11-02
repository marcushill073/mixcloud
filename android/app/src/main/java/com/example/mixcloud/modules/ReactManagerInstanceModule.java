package com.example.mixcloud.modules;

import android.app.Application;
import android.content.Context;

import com.example.mixcloud.MixCloudApp;
import com.example.mixcloud.react.DispatchRequestPackage;
import com.example.mixcloud.react.JSViewHelperPackage;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.react.RealmReactPackage;

@Singleton
@Module
public class ReactManagerInstanceModule {

    private final ReactNativeHost mReactNativeHost;

    public ReactManagerInstanceModule(MixCloudApp application) {
        mReactNativeHost = new ReactNativeHost(application) {
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
    }


    @Provides
    @Singleton
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }


}
