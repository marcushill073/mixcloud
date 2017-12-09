package com.example.mixcloud.modules;


import com.example.mixcloud.activities.HomeActivity;
import com.example.mixcloud.model.Feed;
import com.example.mixcloud.model.Track;
import com.example.mixcloud.model.User;

import java.util.List;

import dagger.Component;
import rx.Observable;

@Component (modules = {ServiceModule.class})
public interface DataComponent {

    RestServiceAPI getRestAPI();

    void inject(HomeActivity homeActivity);
}
