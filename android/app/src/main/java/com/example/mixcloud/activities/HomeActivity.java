package com.example.mixcloud.activities;

import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.mixcloud.MixCloudApp;
import com.example.mixcloud.R;
import com.example.mixcloud.adapters.DrawerAdapter;
import com.example.mixcloud.react.DispatchRequestModule;
import com.facebook.react.ReactNativeHost;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity implements DispatchRequestModule.OnDownloadCompleteListener {

    @Inject
    ReactNativeHost reactNativeHost;

    @Inject
    public View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MixCloudApp.getDataComponent().inject(this);


        ListView listView = (ListView) findViewById(R.id.left_drawer);
        DrawerAdapter adapter = new DrawerAdapter(this);
        listView.setAdapter(adapter);


        listView.addHeaderView(view);
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError() {

    }

    @BindingAdapter("android:src")
    public static void downloadImage(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).into(view);
    }
}
