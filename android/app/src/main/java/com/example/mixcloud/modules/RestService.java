package com.example.mixcloud.modules;

import com.example.mixcloud.model.User;

import retrofit2.http.POST;
import rx.Observable;

public interface RestService {

    @POST("marcushill073")
    Observable<User> fetchUser();
}
