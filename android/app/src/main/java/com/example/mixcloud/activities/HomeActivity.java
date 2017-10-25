package com.example.mixcloud.activities;

import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.example.mixcloud.MixCloudApp;
import com.example.mixcloud.R;
import com.example.mixcloud.adapters.DrawerAdapter;
import com.example.mixcloud.react.JSDispatchRequestModule;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity {


    @Inject
    JSDispatchRequestModule jsDispatchRequestModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        MixCloudApp.getApp().getJsDataComponent().inject(this);

        ListView listView = (ListView) findViewById(R.id.left_drawer);
        DrawerAdapter adapter = new DrawerAdapter(this);
        listView.setAdapter(adapter);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
    }
}
