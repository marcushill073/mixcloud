package com.example.mixcloud.model;

import android.support.annotation.DrawableRes;

import com.example.mixcloud.R;

import java.io.Serializable;

public enum Type implements Serializable {
    POPULAR("popular"), HOT("popular/hot"), NEW("new");

    private final String value;

    Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @DrawableRes
    public static int getImageResource(int i) {

        switch (i) {
            case 0:
                return R.drawable.ic_iconmonstr_whats_hot_1;
            case 1:
                return R.drawable.ic_iconmonstr_favorite_2;
            case 2:
                return R.drawable.ic_iconmonstr_star_5;
                default:
                    return 0;
        }
    }
}