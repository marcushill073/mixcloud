package com.example.mixcloud;

import com.example.mixcloud.react.DispatchRequestModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    @Singleton
    DispatchRequestModule provideDispatchRequestModule() {
        DispatchRequestModule dispatchRequestModule = MixCloudApp.getApp().getReactNativeHost().getReactInstanceManager().getCurrentReactContext().getNativeModule(DispatchRequestModule.class);
        return dispatchRequestModule;
    }
}
