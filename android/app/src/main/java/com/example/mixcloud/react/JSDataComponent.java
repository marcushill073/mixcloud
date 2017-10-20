package com.example.mixcloud.react;

import android.support.v7.app.AppCompatActivity;

import dagger.Component;

@Component (modules = JSDispatchRequestModule.class)
public interface JSDataComponent {
    void inject(AppCompatActivity activity);
}
