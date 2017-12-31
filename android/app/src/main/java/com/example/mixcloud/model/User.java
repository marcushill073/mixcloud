package com.example.mixcloud.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.Serializable;

@AutoValue
public abstract class User implements Parcelable, Serializable {

    public abstract int cloudcastCount();

    public abstract int favoriteCount();

    public abstract int followerCount();

    public abstract int followingCount();

    public abstract boolean isPremium();

    public abstract String key();

    public abstract String name();

    public abstract Pictures pictures();

    public abstract String url();

    public abstract String username();

    public static Builder builder() {
        return new AutoValue_User.Builder()
                .cloudcastCount(0)
                .followerCount(0)
                .followingCount(0)
                .favoriteCount(0)
                .isPremium(false);
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder cloudcastCount(int value);

        public abstract Builder favoriteCount(int value);

        public abstract Builder followerCount(int value);

        public abstract Builder followingCount(int value);

        public abstract Builder isPremium(boolean value);

        public abstract Builder key(String value);

        public abstract Builder name(String value);

        public abstract Builder pictures(Pictures value);

        public abstract Builder url(String value);

        public abstract Builder username(String value);

        public abstract User build();
    }

    public static JsonAdapter<User> jsonAdapter(Moshi moshi) {
        return new AutoValue_User.MoshiJsonAdapter(moshi);
    }

}
