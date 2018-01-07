package com.example.mixcloud.rest;

import com.example.mixcloud.model.Feed;
import com.example.mixcloud.model.MetaData;
import com.example.mixcloud.model.User;

import java.net.MalformedURLException;

import rx.Observable;

public interface RestServiceAPI {

    Observable<User> fetchUser();

    Observable<Feed> fetchHomeFeed(String type);

    Observable<Feed> fetchNextHomePage(String type, String url) throws MalformedURLException;

    Observable<Feed> fetchFeed(String user, String navigation);

    Observable<Feed> fetchNextFeedPage(String user, String navigation, String url) throws MalformedURLException;

    Observable<MetaData> fetchMetaData(String url);

}