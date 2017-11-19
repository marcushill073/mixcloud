package com.example.mixcloud.modules;

import android.content.Context;

import com.example.mixcloud.R;
import com.example.mixcloud.model.User;

import dagger.Module;
import dagger.Provides;
import rx.Observable;

@Module
public class ServiceModule {

    private final String token;

    public ServiceModule(Context context) {
        token = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
                .getString(context.getString(R.string.token), null);
    }

    @Provides
    public Observable<User> fetchUser() {
        RestService restService =
                ServiceGenerator.createService(RestService.class, token);
        return restService.fetchUser();

    }
}
