package com.example.mixcloud.modules;

import android.content.Context;
import android.util.Log;

import com.example.mixcloud.BuildConfig;
import com.example.mixcloud.model.Feed;
import com.example.mixcloud.model.MetaData;
import com.example.mixcloud.model.User;
import com.example.mixcloud.rest.OfflineMockInterceptor;
import com.example.mixcloud.rest.RestService;
import com.example.mixcloud.rest.RestServiceAPI;

import java.net.MalformedURLException;
import java.net.URL;

import rx.Observable;

public class ServiceModuleImpl implements RestServiceAPI {

    private final RestService restService;

    public ServiceModuleImpl(Context context) {
        if (BuildConfig.BUILD_TYPE == "mock") {
            restService = ServiceGenerator.createService(RestService.class,new OfflineMockInterceptor(context));
        } else {
            restService = ServiceGenerator.createService(RestService.class, context);
        }
    }

    @Override
    public Observable<User> fetchUser() {
        return restService.fetchUser();
    }

    @Override
    public Observable<Feed> fetchHomeFeed(String type) {
        return restService.fetchHomeFeed(type);
    }

    @Override
    public synchronized Observable<Feed> fetchNextHomePage(String type, String path) throws MalformedURLException {
        GetOffSet getOffSet = new GetOffSet(path).invoke();
        int limit = getOffSet.getLimit();
        int offset = getOffSet.getOffset();
        return restService.fetchHomeFeedPage(type, limit, offset);

    }

    @Override
    public Observable<Feed> fetchFeed(String user, String navigation) {
        return restService.fetchFeed(user, navigation.toLowerCase());
    }

    @Override
    public Observable<Feed> fetchNextFeedPage(String user, String navigation, String url) throws MalformedURLException {
        GetOffSet getOffSet = new GetOffSet(url).invoke();
        int limit = getOffSet.getLimit();
        int offset = getOffSet.getOffset();
        return restService.fetchNextFeedPage(user, navigation, limit, offset);
    }

    @Override
    public Observable<MetaData> fetchMetaData(String url) {
        return restService.fetchMetaData(url);
    }

    private class GetOffSet {
        private String path;
        private int offset;
        private int limit;

        public GetOffSet(String path) {
            this.path = path;
        }

        public int getOffset() {
            return offset;
        }

        public int getLimit() {
            return limit;
        }

        public GetOffSet invoke() throws MalformedURLException {
            URL url = new URL(path);
            Log.d("next page", path);

            String[] query = url.getQuery().split("&");
            offset = 0;
            limit = 0;
            for (int i = 0; i < query.length; i++) {
                if (query[i].split("=")[0].contains("limit")) {
                    limit = Integer.valueOf(query[i].split("=")[1]);
                }
                if (query[i].split("=")[0].contains("offset")) {
                    offset = Integer.valueOf(query[i].split("=")[1]);
                }
            }
            return this;
        }
    }
}
