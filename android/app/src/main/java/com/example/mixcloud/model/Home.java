package com.example.mixcloud.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;

import com.example.mixcloud.R;

public enum Home implements Type, Parcelable {
    POPULAR("popular"), HOT("popular/hot"), NEW("new");

    private final String value;

    Home(String value) {
        this.value = value;
    }

    Home(Parcel in) {
        value = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Home> CREATOR = new Creator<Home>() {
        @Override
        public Home createFromParcel(Parcel in) {
            return Home.valueOf(in.readString());
        }

        @Override
        public Home[] newArray(int size) {
            return new Home[size];
        }
    };

    public String getValue() {
        return value;
    }

    @DrawableRes
    public int getImageResource(int i) {

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

    @Override
    public Type[] getValues() {
        return Home.values();
    }

    @Override
    public int getOrdinal() {
        return this.ordinal();
    }
}