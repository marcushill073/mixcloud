package com.example.mixcloud.model;

import com.google.auto.value.AutoValue;

import io.realm.RealmModel;

@AutoValue
public abstract class Pictures implements RealmModel {

    public abstract String extraLarge();
    public abstract String large();
    public abstract String medium();
    public abstract String mediumMobile();
    public abstract String small();
    public abstract String thumbnail();

    public static Pictures.Builder builder() {
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
    }
}
