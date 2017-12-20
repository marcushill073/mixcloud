package com.example.mixcloud.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

@AutoValue
public abstract class Track implements Parcelable {

    public abstract String key();

    @Nullable
    public abstract String createdTime();

    public abstract long audioLength();

    @Nullable
    public abstract String slug();

    public abstract int favoriteCount();

    public abstract int listenerCount();

    @Nullable
    public abstract String name();

    @Nullable
    public abstract String url();

    public abstract Pictures pictures();

    @Nullable
    public abstract User user();

    @Nullable
    public abstract List<Tag> tags();

    public abstract int repostCount();

    @Nullable
    public abstract String updatedTime();

    public abstract int commentCount();

    public static  Builder builder() {
        return new AutoValue_Track.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder key(String value);

        public abstract Builder createdTime(String value);

        public abstract Builder audioLength(long value);

        public abstract Builder slug(String value);

        public abstract Builder favoriteCount(int value);

        public abstract Builder listenerCount(int value);

        public abstract Builder name(String value);

        public abstract Builder url(String url);

        public abstract Builder pictures(Pictures pictures);

        public abstract Builder user(User value);

        public abstract Builder tags(List<Tag> value);

        public abstract Builder repostCount(int value);

        public abstract Builder updatedTime(String value);

        public abstract Builder commentCount(int value);

        public abstract Track build();
    }

    public static JsonAdapter<Track> jsonAdapter(Moshi moshi) {
        return new AutoValue_Track.MoshiJsonAdapter(moshi);
    }

}
