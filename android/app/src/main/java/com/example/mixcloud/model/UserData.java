package com.example.mixcloud.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

@AutoValue
public abstract class UserData {

    public abstract User from();

    public abstract String title();

    public abstract String url();

    public abstract String key();

    @Nullable
    public abstract String createdTime();

    @Nullable
    public abstract List<Track> cloudCasts();

    public static Builder builder() {
        return new AutoValue_UserData.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder from(User value);

        public abstract Builder title(String value);

        public abstract Builder url(String value);

        public abstract Builder key(String value);

        public abstract Builder createdTime(String value);

        public abstract Builder cloudCasts(List<Track> value);

        public abstract UserData build();

    }

    public static JsonAdapter<UserData> jsonAdapter(Moshi moshi) {
        return new AutoValue_UserData.MoshiJsonAdapter(moshi);
    }
}
