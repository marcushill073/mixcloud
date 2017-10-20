package com.example.mixcloud.model;

import com.google.auto.value.AutoValue;

import io.realm.RealmModel;
import io.realm.annotations.Ignore;

@AutoValue
public abstract class Address implements RealmModel {

    public abstract State state();
    public abstract Country country();
    public abstract String postcode();

    @Ignore
    public String sessionId;

    public static Builder builder() {
        return new AutoValue_Address.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder state(State value);

        public abstract Builder country(Country value);

        public abstract Builder postcode(String value);

        public abstract Address build();
    }
}
