package com.example.mixcloud.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import javax.annotation.Nullable;

@AutoValue
public abstract class Pictures implements Parcelable {

    @Json(name="extra_large")
    @Nullable
    public abstract String extraLarge();

    @Nullable
    public abstract String large();

    @Nullable
    public abstract String medium();

    @Nullable
    public abstract String mediumMobile();

    @Nullable
    public abstract String small();

    @Nullable
    public abstract String thumbnail();

    public static Builder builder() {
        return new AutoValue_Pictures.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder extraLarge(String value);

        public abstract Builder large(String value);

        public abstract Builder medium(String value);

        public abstract Builder mediumMobile(String value);

        public abstract Builder small(String value);

        public abstract Builder thumbnail(String value);

        public abstract Pictures build();
    }

    public static JsonAdapter<Pictures> jsonAdapter(Moshi moshi) {
        return new AutoValue_Pictures.MoshiJsonAdapter(moshi);
    }

}