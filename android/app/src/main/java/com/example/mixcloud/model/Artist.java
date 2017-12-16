package com.example.mixcloud.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class Artist implements Parcelable {

    public abstract String name();

    public abstract Country country();

    public static Artist.Builder builder() {
        return new AutoValue_Artist.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder name(String value);

        public abstract Builder country(Country value);

        public abstract Artist build();

    }

    public static JsonAdapter<Artist> jsonAdapter(Moshi moshi) {
        return new AutoValue_Artist.MoshiJsonAdapter(moshi);
    }

}
