package com.example.mixcloud.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;

import com.example.mixcloud.R;
import com.google.auto.value.AutoValue;

import java.io.Serializable;

public enum Type implements Parcelable {
    POPULAR("popular"), HOT("popular/hot"), NEW("new");

    private final String value;

    Type(String value) {
        this.value = value;
    }

    Type(Parcel in) {
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

    public static final Creator<Type> CREATOR = new Creator<Type>() {
        @Override
        public Type createFromParcel(Parcel in) {
            return Type.valueOf(in.readString());
        }

        @Override
        public Type[] newArray(int size) {
            return new Type[size];
        }
    };

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