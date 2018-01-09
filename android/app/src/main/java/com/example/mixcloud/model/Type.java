package com.example.mixcloud.model;


import android.os.Parcelable;
import android.support.annotation.DrawableRes;

public interface Type extends Parcelable {

    String getValue();

    @DrawableRes
    int getImageResource(int i);

    Type[] getValues();

    int getOrdinal();

}

