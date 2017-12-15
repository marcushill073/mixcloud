package com.example.mixcloud.modules;

import android.content.Context;

import com.example.mixcloud.R;
import com.example.mixcloud.model.Feed;
import com.example.mixcloud.model.Track;
import com.example.mixcloud.model.User;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import rx.Observable;

@Module
public class ServiceModule {

    private final ServiceModuleImpl serviceModule;

    public ServiceModule(ServiceModuleImpl serviceModule) {
       this.serviceModule = serviceModule;
    }

    @Provides
    public RestServiceAPI fetchUser() {
        return serviceModule;

    }
}
