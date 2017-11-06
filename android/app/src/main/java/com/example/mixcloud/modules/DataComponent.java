package com.example.mixcloud.modules;

import android.view.View;

import com.example.mixcloud.MixCloudApp;
import com.example.mixcloud.activities.HomeActivity;
import com.example.mixcloud.activities.MainActivity;
import com.example.mixcloud.model.User;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;

import javax.inject.Singleton;

import dagger.Component;
import io.realm.RealmResults;

@Singleton
@Component (modules = {ReactManagerInstanceModule.class, AppModule.class, UserModule.class})
public interface DataComponent {
    void inject(MixCloudApp mixCloudApp);

    void inject(HomeActivity homeActivity);

    void inject(MainActivity mainActivity);

    void inject(UserModule userModule);

    ReactNativeHost getReactNativeHost();

    View fetchUser();

    MixCloudApp providesApplication();

}
