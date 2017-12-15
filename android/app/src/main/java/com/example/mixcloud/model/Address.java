package com.example.mixcloud.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;


@AutoValue
public abstract class Address implements Parcelable {

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

    public static JsonAdapter<Address> jsonAdapter(Moshi moshi) {
        return new AutoValue_Address.MoshiJsonAdapter(moshi);
    }
}
