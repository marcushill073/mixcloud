package com.example.mixcloud.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;

import com.example.mixcloud.R;

public enum Search implements Type, Parcelable {

    TAG("tag"), USER("user"), CLOUDCAST("cloudcast"), ARTIST("artist") ;

    private String value;

    Search(String value) {
        this.value = value;
    }

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

    Search(Parcel in) {
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

    public static final Creator<Search> CREATOR = new Creator<Search>() {
        @Override
        public Search createFromParcel(Parcel in) {
            return Search.valueOf(in.readString());
        }

        @Override
        public Search[] newArray(int size) {
            return new Search[size];
        }
    };

    @Override
    public Type[] getValues() {
        return Search.values();
    }

    @Override
    public int getOrdinal() {
        return this.ordinal();
    }
}
