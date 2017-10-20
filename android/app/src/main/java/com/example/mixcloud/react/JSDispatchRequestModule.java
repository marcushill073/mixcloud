package com.example.mixcloud.react;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

import javax.inject.Inject;

import dagger.Module;

@Module
public class JSDispatchRequestModule extends ReactContextBaseJavaModule {

    @Inject
    public JSDispatchRequestModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "JSDispatchRequestModule";
    }

    public synchronized void fetchUser(int id){

    }

}
