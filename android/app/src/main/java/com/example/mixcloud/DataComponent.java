package com.example.mixcloud;

import android.support.v7.app.AppCompatActivity;

import com.example.mixcloud.react.DispatchRequestModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (modules = DataModule.class)
public interface DataComponent {
    void inject(AppCompatActivity activity);

    DispatchRequestModule provideDispatchRequestModule();
}
