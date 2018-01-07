package com.example.mixcloud.modules;


import com.example.mixcloud.activities.HomeActivity;
import com.example.mixcloud.fragments.HomeFragment;
import com.example.mixcloud.rest.RestServiceAPI;

import dagger.Component;

@Component (modules = {ServiceModule.class})
public interface DataComponent {

    RestServiceAPI getRestAPI();

    void inject(HomeActivity homeActivity);

    void inject(HomeFragment homeFragment);
}
