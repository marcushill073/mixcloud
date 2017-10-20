package com.example.mixcloud.model;

import com.google.auto.value.AutoValue;

import io.realm.RealmModel;

@AutoValue
public abstract class User implements RealmModel {

    public abstract String name();

    public abstract int age();

    public abstract Address address();

    public abstract MemberType memberType();

    public abstract PlayList playLists();

    public static User.Builder builder() {
        return new AutoValue_User.Builder().age(0);
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder name(String value);

        public abstract Builder age(int value);

        public abstract Builder address(Address value);

        public abstract Builder memberType(MemberType value);

        public abstract Builder playLists(PlayList value);

        public abstract User builder();

    }

}
