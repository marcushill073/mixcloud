package com.example.mixcloud.modules;

import android.content.Context;

import com.example.mixcloud.R;
import com.example.mixcloud.model.Feed;
import com.example.mixcloud.model.Track;
import com.example.mixcloud.model.User;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import rx.Observable;

@Module
public class ServiceModule {

    private final Context context;

    public ServiceModule(Context context) {
       this.context = context;
    }

    @Provides
    public Observable<User> fetchUser() {
        RestService restService =
                ServiceGenerator.createService(RestService.class, context);
        return restService.fetchUser();

    }

    @Provides
    public Observable<Feed> fetchFeed() {
        RestService restService =
                ServiceGenerator.createService(RestService.class, context);
        return restService.fetchPopularFeed();
    }
}
