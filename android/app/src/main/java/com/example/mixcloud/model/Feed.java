package com.example.mixcloud.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

@AutoValue
public abstract class Feed {

    @Nullable
    public abstract Paging paging();

    public abstract List<Track> data();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new AutoValue_Feed.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {

        public abstract Builder data(List<Track> value);

        public abstract Builder paging(Paging value);

        public abstract Feed build();
    }

    public static JsonAdapter<Feed> jsonAdapter(Moshi moshi) {
        return new AutoValue_Feed.MoshiJsonAdapter(moshi);
    }

}
