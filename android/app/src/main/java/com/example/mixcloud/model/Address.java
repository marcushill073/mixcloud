package com.example.mixcloud.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;


@AutoValue
public abstract class Address {

    public abstract State state();
    public abstract Country country();
    public abstract String postcode();

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

    public static TypeAdapter<Address> typeAdapter(Gson gson) {
        return new AutoValue_Address.GsonTypeAdapter(gson);
    }
}
