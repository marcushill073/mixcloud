package com.example.mixcloud.modules;


import com.example.mixcloud.model.Type;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class TypeImpl implements Type {

    private static final String TYPEIMPL = ".typeimpl";

    public abstract Type type();

    public static Builder builder() {
        return new AutoValue_TypeImpl.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {

        public abstract Builder type(Type type);

        public abstract TypeImpl build();
    }

    public static JsonAdapter<TypeImpl> jsonAdapter(Moshi moshi) {
        return new AutoValue_TypeImpl.MoshiJsonAdapter(moshi);
    }

    @Override
    public String getValue() {
        return this.type().getValue();
    }

    @Override
    public int getImageResource(int i) {
        return this.type().getImageResource(i);
    }

    @Override
    public Type[] getValues() {
        return this.type().getValues();
    }

    @Override
    public int getOrdinal() {
        return this.type().getOrdinal();
    }

}
