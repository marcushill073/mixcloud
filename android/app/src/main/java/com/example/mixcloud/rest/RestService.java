package com.example.mixcloud.rest;

import com.example.mixcloud.model.Feed;
import com.example.mixcloud.model.MetaData;
import com.example.mixcloud.model.User;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface RestService {

    @GET("me")
    Observable<User> fetchUser();

    @GET("search")
    Observable<Feed> fetchSearchFeed( @Query("q") String searchString, @Query("type") String type);

    @GET("search")
    Observable<Feed> fetchNextSearchPage( @Query("limit") int limit, @Query("offset") int offset, @Query("q") String searchString, @Query("type") String type);

    @GET("{user}/{feed}")
    Observable<Feed> fetchFeed(@Path("user") String user, @Path("feed") String navigation);

    @GET("{user}/{feed}")
    Observable<Feed> fetchNextFeedPage(@Path("user") String user, @Path("feed") String navigation, @Query("limit") int limit, @Query("offset") int offset);

    @GET("oembed")
    Observable<MetaData> fetchMetaData(@Query("url") String url);
}
