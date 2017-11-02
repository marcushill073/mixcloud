package com.example.mixcloud.modules;

import com.example.mixcloud.MixCloudApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module
public class AppModule {

    MixCloudApp mApplication;

    public AppModule(MixCloudApp application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    MixCloudApp providesApplication() {
        return mApplication;
    }
}