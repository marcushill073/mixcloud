package com.example.mixcloud.activities;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.mixcloud.BR;
import com.example.mixcloud.DataComponent;
import com.example.mixcloud.MixCloudApp;
import com.example.mixcloud.R;
import com.example.mixcloud.adapters.DrawerAdapter;
import com.example.mixcloud.model.User;
import com.example.mixcloud.react.DispatchRequestModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import io.realm.Realm;

public class HomeActivity extends AppCompatActivity implements DispatchRequestModule.OnDownloadCompleteListener {


    private DataComponent dataComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewDataBinding homeActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);


        ListView listView = (ListView) findViewById(R.id.left_drawer);
        DrawerAdapter adapter = new DrawerAdapter(this);
        listView.setAdapter(adapter);

        MixCloudApp.getApp().getReactNativeHost().getReactInstanceManager().getCurrentReactContext()
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit("JSDispatchRequestModule", "USER");

        Realm realm = Realm.getDefaultInstance();
        User user = realm.where(User.class).findFirst();

        homeActivityBinding.setVariable(BR.user, user);
        homeActivityBinding.executePendingBindings();
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError() {

    }
}
