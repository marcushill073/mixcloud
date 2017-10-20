package com.example.mixcloud.model;

import com.google.auto.value.AutoValue;

import io.realm.RealmModel;
import io.realm.annotations.Ignore;

@AutoValue
public abstract class Track implements RealmModel {

    public abstract Artist artist();
    public abstract String trackName();
    public abstract Double duration();
    public abstract String label();
    public abstract String genre();
    public abstract boolean isExplicit();

    @Ignore
    public String sessionId;

    public static Track.Builder builder() {
        return new AutoValue_Track.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder artist(Artist value);

        public abstract Builder trackName(String value);

        public abstract Builder duration(Double value);

        public abstract Builder label(String value);

        public abstract Builder genre(String value);

        public abstract Builder isExplicit(boolean value);

        public abstract Track builder();
    }
}
