package com.example.mixcloud.modules;


import com.example.mixcloud.model.Type;

public class TypeImpl implements Type {

    private Type type;

    public TypeImpl(Type type) {
        this.type = type;
    }

    @Override
    public String getValue() {
        return type.getValue();
    }

    @Override
    public int getImageResource(int i) {
        return type.getImageResource(i);
    }

    @Override
    public Type[] getValues() {
        return type.getValues();
    }
}
