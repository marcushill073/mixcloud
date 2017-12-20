package com.example.mixcloud.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

@AutoValue
public abstract class UserFeed {

    @Nullable
    public abstract Paging paging();

    public abstract List<UserData> data();


    public static Builder builder() {
        return new AutoValue_UserFeed.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder paging(Paging value);

        public abstract Builder data(List<UserData> value);

        public abstract UserFeed build();

    }

    public static JsonAdapter<UserFeed> jsonAdapter(Moshi moshi) {
        return new AutoValue_UserFeed.MoshiJsonAdapter(moshi);
    }
}
