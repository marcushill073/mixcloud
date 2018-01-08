package com.example.mixcloud.model;


import android.support.annotation.DrawableRes;

public interface Type {

    public String getValue();

    @DrawableRes
    int getImageResource(int i);

    Type[] getValues();

}

