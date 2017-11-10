package com.example.mixcloud.modules;


import com.example.mixcloud.MixCloudApp;
import com.example.mixcloud.model.User;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

@Module
@Singleton
public class UserModule {

    @Inject
    public ReactNativeHost reactNativeHost;

    private User user = new User();
    private RealmResults<User> users;

    public UserModule() {

    }


    @Singleton
    @Provides
    public User fetchUser() {

        MixCloudApp.getDataComponent().inject(this);
        reactNativeHost.getReactInstanceManager().getCurrentReactContext()
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit("JSDispatchRequestModule", "USER");

        Realm realm = Realm.getDefaultInstance();
        users = realm.where(User.class).findAll();
        user = users.isEmpty() ? user : users.get(0);
        users.addChangeListener(new RealmChangeListener<RealmResults<User>>() {
            @Override
            public void onChange(RealmResults<User> results) {
                user = results.isEmpty() ? user : results.get(0);
            }
        });
        return user;
    }

}
