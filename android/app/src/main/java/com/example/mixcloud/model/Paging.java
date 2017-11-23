package com.example.mixcloud.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import javax.annotation.Nullable;

@AutoValue
public abstract class Paging {

    @Nullable
    public abstract String next();

    public static Builder builder() {
        return new AutoValue_Paging.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder next(String value);

        public abstract Paging build();
    }

    public static JsonAdapter<Paging> jsonAdapter(Moshi moshi) {
        return new AutoValue_Paging.MoshiJsonAdapter(moshi);
    }
}