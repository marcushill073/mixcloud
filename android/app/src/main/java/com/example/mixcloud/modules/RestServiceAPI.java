package com.example.mixcloud.modules;

import com.example.mixcloud.model.Feed;
import com.example.mixcloud.model.User;

import java.net.MalformedURLException;

import dagger.Component;
import rx.Observable;

public interface RestServiceAPI {

    Observable<User> fetchUser();

    Observable<Feed> fetchPopularFeed(String type);

    Observable<Feed> fetchNextFeedPage(String type, String url) throws MalformedURLException;
}
