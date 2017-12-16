package com.example.mixcloud.modules;

import dagger.Module;
import dagger.Provides;

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
