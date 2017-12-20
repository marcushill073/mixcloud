package com.example.mixcloud.modules;

import com.example.mixcloud.model.Feed;
import com.example.mixcloud.model.User;
import com.example.mixcloud.model.UserFeed;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface RestService {

    @POST("me")
    Observable<User> fetchUser();

    @GET("{feed}")
    Observable<Feed> fetchHomeFeed(@Path("feed") String type);

    @GET("{feed}")
    Observable<Feed> fetchHomeFeedPage(@Path("feed") String type, @Query("limit") int limit, @Query("offset") int offset);

    @GET("{user}/{feed}")
    Observable<Feed> fetchFeed(@Path("user") String user, @Path("feed") String navigation);

    @GET("{user}/{feed}")
    Observable<Feed> fetchNextFeedPage(@Path("user") String user, @Path("feed") String navigation, @Query("limit") int limit, @Query("offset") int offset);

    @GET("{user}/{feed}")
    Observable<UserFeed> fetchUserFeed(@Path("user") String user, @Path("feed") String navigation);

    @GET("{user}/{feed}")
    Observable<UserFeed> fetchNextUserFeedFeedPage(@Path("user") String user, @Path("feed") String navigation, @Query("limit") int limit, @Query("offset") int offset);
}
