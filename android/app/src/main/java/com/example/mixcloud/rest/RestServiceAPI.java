package com.example.mixcloud.rest;

import com.example.mixcloud.model.Feed;
import com.example.mixcloud.model.MetaData;
import com.example.mixcloud.model.User;

import java.net.MalformedURLException;

import rx.Observable;

public interface RestServiceAPI {

    Observable<User> fetchUser();

    Observable<Feed> fetchTabFeed(String type, String search);

    Observable<Feed> fetchNextTabPage(String type, String search, String url) throws MalformedURLException;

    Observable<Feed> fetchFeed(String user, String type);

    Observable<Feed> fetchNextPage(String user, String type, String url) throws MalformedURLException;

    Observable<MetaData> fetchMetaData(String url);

}