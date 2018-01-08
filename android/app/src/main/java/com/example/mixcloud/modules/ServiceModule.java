package com.example.mixcloud.modules;

import com.example.mixcloud.model.Type;
import com.example.mixcloud.rest.RestServiceAPI;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {

    private final ServiceModuleImpl serviceModule;
    private final TypeImpl type;

    public ServiceModule(ServiceModuleImpl serviceModule, TypeImpl type) {
       this.serviceModule = serviceModule;
       this.type = type;
    }

    @Provides
    public RestServiceAPI fetchAPI() {
        return serviceModule;

    }

    @Provides
    public Type fetchType() {
        return type;
    }
}
