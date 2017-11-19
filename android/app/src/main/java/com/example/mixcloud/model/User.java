package com.example.mixcloud.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class User {

    public abstract int cloudcastCount();

    public abstract int favoriteCount();

    public abstract int followerCount();

    public abstract int followingCount();

    public abstract boolean isPremium();

    public abstract String key();

    public abstract String name();

    public abstract Pictures pictures();

    public abstract String url();

    public abstract String username();

    public static Builder builder() {
        return new AutoValue_User.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder cloudcastCount(int value);

        public abstract Builder favoriteCount(int value);

        public abstract Builder followerCount(int value);

        public abstract Builder followingCount(int value);

        public abstract Builder isPremium(boolean value);

        public abstract Builder key(String value);

        public abstract Builder name(String value);

        public abstract Builder pictures(Pictures value);

        public abstract Builder url(String value);

        public abstract Builder username(String value);

        public abstract User build();
    }

    public static TypeAdapter<User> typeAdapter(Gson gson) {
        return new AutoValue_User.GsonTypeAdapter(gson);
    }

}
