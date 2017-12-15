package com.example.mixcloud.modules;

import android.content.Context;
import android.util.Log;

import com.example.mixcloud.model.Feed;
import com.example.mixcloud.model.User;

import java.net.MalformedURLException;
import java.net.URL;

import dagger.Provides;
import rx.Observable;

public class ServiceModuleImpl implements RestServiceAPI {

    private final RestService restService;


    public ServiceModuleImpl(Context context) {
        restService =
                ServiceGenerator.createService(RestService.class, context);

    }

    @Override
    public Observable<User> fetchUser() {
        return restService.fetchUser();
    }

    @Override
    public Observable<Feed> fetchPopularFeed(String type) {
        return restService.fetchPopularFeed(type);
    }

    @Override
    public synchronized Observable<Feed> fetchNextFeedPage(String type, String path) throws MalformedURLException {
        URL url = new URL(path);
        Log.d("next page", path);

        String[] query = url.getQuery().split("&");
        int offset = 0;
        int limit = 0;
        if (query.length == 3) {
            limit = Integer.valueOf(query[1].split("=")[1]);
            offset = Integer.valueOf(query[2].split("=")[1]);
        }
        return restService.fetchNextPopularFeedPage(type, limit, offset);

    }
}
