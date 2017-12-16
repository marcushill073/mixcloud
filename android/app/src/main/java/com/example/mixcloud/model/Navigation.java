package com.example.mixcloud.model;

import java.io.Serializable;

public enum Navigation implements Serializable {

    FEED("feed"), PLAYLISTS("playlists"), COMMENTS("comments"), FOLLOWERS("followers"),
    FAVORITES("favorites"), FOLLOWING("following"), CLOUDCASTS("cloudcasts"),
    LISTENS("listens");

    private final String value;

    Navigation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
