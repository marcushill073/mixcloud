package com.example.mixcloud.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class MetaData implements Parcelable {

    @Json(name = "image")
    public abstract String imageUrl();

    @Json(name = "author_name")
    public abstract String authorName();

    public abstract float height();

    public abstract String embed();

    public abstract String title();

    public abstract String url();

    public abstract String html();

    @Json(name = "author_url")
    public abstract String authorUrl();

    public static Builder builder() {
        return new AutoValue_MetaData.Builder()
                .height(0);
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder imageUrl(String value);

        public abstract Builder authorName(String value);

        public abstract Builder height(float value);

        public abstract Builder embed(String value);

        public abstract Builder title(String value);

        public abstract Builder url(String value);

        public abstract Builder html(String value);

        public abstract Builder authorUrl(String value);

        public abstract MetaData build();
    }

    public static JsonAdapter<MetaData> jsonAdapter(Moshi moshi) {
        return new AutoValue_MetaData.MoshiJsonAdapter(moshi);
    }
}
