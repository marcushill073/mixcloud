package com.example.mixcloud.model;

import com.google.auto.value.AutoValue;

import java.util.List;

import io.realm.RealmModel;
import io.realm.annotations.Ignore;

@AutoValue
public abstract class PlayList implements RealmModel {

    public abstract String name();

    public abstract List<Track> tracks();

    @Ignore
    public String sessionId;

    public static Builder builder() {
        return new AutoValue_PlayList.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder name(String value);

        public abstract Builder tracks(List<Track> value);

        public abstract PlayList builder();

    }
}
