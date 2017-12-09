package com.example.mixcloud.react;

import android.util.SparseArray;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;

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
    public void onSuccess() {
        //do nothing
    }

    @ReactMethod
    public void onError(ReadableMap error){
        //todo
    }
    public interface OnDownloadCompleteListener {

        void onSuccess();
        void onError();
    }

}
