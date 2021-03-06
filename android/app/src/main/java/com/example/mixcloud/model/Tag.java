package com.example.mixcloud.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class Tag implements Parcelable {

    public abstract String name();

    public abstract String key();

    public abstract String url();

    public static Builder builder() {
        return new AutoValue_Tag.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder name(String value);

        public abstract Builder key(String value);

        public abstract Builder url(String value);

        public abstract Tag build();

    }

    public static JsonAdapter<Tag> jsonAdapter(Moshi moshi) {
        return new AutoValue_Tag.MoshiJsonAdapter(moshi);
    }

}
