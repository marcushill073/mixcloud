package com.example.mixcloud.activities;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.mixcloud.BR;
import com.example.mixcloud.DataComponent;
import com.example.mixcloud.MixCloudApp;
import com.example.mixcloud.R;
import com.example.mixcloud.adapters.DrawerAdapter;
import com.example.mixcloud.model.User;
import com.example.mixcloud.react.DispatchRequestModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class HomeActivity extends AppCompatActivity implements DispatchRequestModule.OnDownloadCompleteListener {


    private DataComponent dataComponent;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);


        ListView listView = (ListView) findViewById(R.id.left_drawer);
        DrawerAdapter adapter = new DrawerAdapter(this);
        listView.setAdapter(adapter);

        MixCloudApp.getApp().getReactNativeHost().getReactInstanceManager().getCurrentReactContext()
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit("JSDispatchRequestModule", "USER");

        Realm realm = Realm.getDefaultInstance();
        RealmResults<User> users = realm.where(User.class).findAll();
        user = users.isEmpty() ? null : users.get(0);
        users.addChangeListener(new RealmChangeListener<RealmResults<User>>() {
            @Override
            public void onChange(RealmResults<User> results) {
                user = results.isEmpty() ? null : results.get(0);
            }
        });

        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.item_drawer_header, null, false);
        binding.setVariable(BR.user, user);
        binding.executePendingBindings();

        listView.addHeaderView(binding.getRoot());
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError() {

    }
}
