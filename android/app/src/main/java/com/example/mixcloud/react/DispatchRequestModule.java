package com.example.mixcloud.react;

import android.util.SparseArray;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;

public class DispatchRequestModule extends ReactContextBaseJavaModule {

    private int id;
    private ArrayList<Integer> requestIds = new ArrayList<>();
    private JSDispatchRequestModule jsModule;

    public DispatchRequestModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "JSDispatchRequestModule";
    }

    public synchronized void fetchUser(OnDownloadCompleteListener listener){
        jsModule = getReactApplicationContext().getJSModule(JSDispatchRequestModule.class);
        requestIds.add(id);
        jsModule.fetchUser(id, listener);
        id++;
    }

    public interface JSDispatchRequestModule extends JavaScriptModule {

        void fetchUser(int id, OnDownloadCompleteListener listener);
    }

    public interface OnDownloadCompleteListener {

        void onSuccess(int id);
        void onError(int id);
    }

}
