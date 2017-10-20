package com.example.mixcloud.model;

import com.google.auto.value.AutoValue;

import io.realm.RealmModel;
import io.realm.annotations.Ignore;

@AutoValue
public abstract class Artist implements RealmModel {

    public abstract String name();

    public abstract Country country();

    @Ignore
    public String sessionId;

    public static Artist.Builder builder() {
        return new AutoValue_Artist.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder name(String value);

        public abstract Builder country(Country value);

        public abstract Artist build();

    }
}
