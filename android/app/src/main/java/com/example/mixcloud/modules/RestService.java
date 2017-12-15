package com.example.mixcloud.modules;

import com.example.mixcloud.model.Feed;
import com.example.mixcloud.model.Track;
import com.example.mixcloud.model.User;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface RestService {

    @POST("marcushill073")
    Observable<User> fetchUser();

    @GET("{feed}")
    Observable<Feed> fetchPopularFeed(@Path("feed")String type);

    @GET("{feed}")
    Observable<Feed> fetchNextPopularFeedPage(@Path("feed") String type, @Query("limit") int limit, @Query("offset") int offset);
}
