package com.example.mixcloud.modules;


import android.app.Application;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;

import com.example.mixcloud.BR;
import com.example.mixcloud.MixCloudApp;
import com.example.mixcloud.R;
import com.example.mixcloud.model.User;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import org.reactivestreams.Publisher;

import java.util.Observable;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.realm.Realm;

@Module
@Singleton
public class UserModule {

    @Inject
    public MixCloudApp context;
    @Inject
    public ReactNativeHost reactNativeHost;

    private Disposable disposable;

    public UserModule() {

    }
    @Singleton
    @Provides
    public View fetchUser() {

        MixCloudApp.getDataComponent().inject(this);

        dispatchRequest();

        Realm realm = Realm.getDefaultInstance();
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_drawer_header, null, false);

        realm.where(User.class).findAllAsync().asFlowable()
                .filter(users -> users.isLoaded())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> {
                    if(!users.isEmpty()) {
                        binding.setVariable(BR.user, users.get(0));
                        binding.executePendingBindings();
                    }
                });
        return binding.getRoot();
    }

    private void dispatchRequest() {
        reactNativeHost.getReactInstanceManager().getCurrentReactContext()
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit("JSDispatchRequestModule", "USER");
    }

}
