package com.example.mixcloud.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

@AutoValue
public abstract class PlayList  implements Parcelable {

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

    public static JsonAdapter<PlayList> jsonAdapter(Moshi moshi) {
        return new AutoValue_PlayList.MoshiJsonAdapter(moshi);
    }

}
