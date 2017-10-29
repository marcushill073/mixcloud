package com.example.mixcloud.react;

import android.app.Activity;
import android.util.SparseArray;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;

@ReactModule(name = "DispatchRequestModule")
public class DispatchRequestModule extends ReactContextBaseJavaModule {

    private int id;
    private SparseArray<OnDownloadCompleteListener> requestIds = new SparseArray<>();
    private DeviceEventManagerModule.RCTDeviceEventEmitter jsModule;

    public DispatchRequestModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "DispatchRequestModule";
    }

    @ReactMethod
    public void onSuccess(int id) {
        Activity activity = getCurrentActivity();
        if(!activity.isFinishing()){
            OnDownloadCompleteListener listener = requestIds.get(id);
            if(listener != null){
                listener.onSuccess();
            }
        }
    }

    @ReactMethod
    public void onError(int id, ReadableMap error){
        Activity activity = getCurrentActivity();
        if(!activity.isFinishing()){
            OnDownloadCompleteListener listener = requestIds.get(id);
            if(listener != null){
                listener.onError();
            }
        }
    }
    public interface OnDownloadCompleteListener {

        void onSuccess();
        void onError();
    }

}
