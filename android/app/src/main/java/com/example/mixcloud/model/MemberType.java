package com.example.mixcloud.model;

enum MemberType {

    STANDARD("standard"), Premium("premium");

    private final String memberType;

    MemberType(String value) {
        this.memberType = value;
    }
}
