package com.example.mixcloud.modules;

import com.example.mixcloud.model.Feed;
import com.example.mixcloud.model.User;
import com.example.mixcloud.model.UserFeed;

import java.net.MalformedURLException;

import rx.Observable;

public interface RestServiceAPI {

    Observable<User> fetchUser();

    Observable<Feed> fetchHomeFeed(String type);

    Observable<Feed> fetchNextHomePage(String type, String url) throws MalformedURLException;

    Observable<Feed> fetchFeed(String user, String navigation);

    Observable<Feed> fetchNextFeedPage(String user, String navigation, String url) throws MalformedURLException;

    Observable<UserFeed> fetchUserFeed(String user, String navigation);

    Observable<UserFeed> fetchUserNextFeedPage(String user, String navigation, String url) throws MalformedURLException;
}
