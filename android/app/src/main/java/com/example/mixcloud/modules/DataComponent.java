package com.example.mixcloud.modules;


import com.example.mixcloud.activities.HomeActivity;
import com.example.mixcloud.model.User;

import dagger.Component;
import rx.Observable;

@Component (modules = {ServiceModule.class})
public interface DataComponent {

    Observable<User> fetchUser();

    void inject(HomeActivity homeActivity);
}
