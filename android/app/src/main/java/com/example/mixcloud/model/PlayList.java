package com.example.mixcloud.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

@AutoValue
public abstract class PlayList  {

    public abstract String name();

    public abstract List<Track> tracks();

    public static Builder builder() {
        return new AutoValue_PlayList.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder name(String value);

        public abstract Builder tracks(List<Track> value);

        public abstract PlayList builder();

    }

    public static TypeAdapter<PlayList> typeAdapter(Gson gson) {
        return new AutoValue_PlayList.GsonTypeAdapter(gson);
    }

}
