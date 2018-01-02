package com.example.mixcloud.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;

@AutoValue
public abstract class PlayerMetaData {

    @Json(name = "hide_cover")
    public abstract boolean hideCover();

    @Json(name = "hide_tracklist")
    public abstract boolean hideTracklist();

    public abstract boolean mini();

    @Json(name = "hide_artwork")
    public abstract boolean hideArtwork();

    public abstract boolean light();

    public static Builder builder() {
        return new AutoValue_PlayerMetaData.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder hideCover(boolean value);

        public abstract Builder hideTracklist(boolean value);

        public abstract Builder mini(boolean value);

        public abstract Builder hideArtwork(boolean value);

        public abstract Builder light(boolean value);

        public abstract PlayerMetaData build();

    }
}