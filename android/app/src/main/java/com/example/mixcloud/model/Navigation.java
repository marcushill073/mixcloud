package com.example.mixcloud.model;


import android.support.annotation.DrawableRes;

import com.example.mixcloud.R;

import java.io.Serializable;

public enum Navigation implements Serializable {

    HOME("Home"), FEED("Feed"), PLAYLISTS("Playlists"), FOLLOWERS("Followers"),
    FAVORITES("Favorites"), FOLLOWING("Following"), CLOUDCASTS("Cloudcasts"), LISTENS("Listens");

    private final String value;

    Navigation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @DrawableRes
    public int getResourceDrawable() {
        switch (this) {
            case HOME:
                return R.drawable.ic_iconmonstr_home_6;
            case FEED:
                return R.drawable.ic_iconmonstr_video_2;
            case CLOUDCASTS:
                return R.drawable.ic_iconmonstr_disc_5;
            case PLAYLISTS:
                return R.drawable.ic_iconmonstr_disc_7;
            case FOLLOWERS:
                return R.drawable.ic_iconmonstr_user_29;
            case FAVORITES:
                return R.drawable.ic_iconmonstr_star_5;
            case FOLLOWING:
                return R.drawable.ic_iconmonstr_user_1;
            case LISTENS:
                return R.drawable.ic_iconmonstr_headphones_1;
                default: return 0;
        }
    }
}
