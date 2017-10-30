package com.example.mixcloud.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;


public class User extends RealmObject {

    @Ignore
    private int sessionId;

    private int cloudcastCount;
    private int favoriteCount;
    private int followerCount;
    private int followingCount;
    private boolean isPremium;
    private String key;
    private String name;
    private Pictures pictures;
    private String url;
    private String username;

//    private RealmList<PlayList> playLists;


    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getCloudcastCount() {
        return cloudcastCount;
    }

    public void setCloudcastCount(int cloudcastCount) {
        this.cloudcastCount = cloudcastCount;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pictures getPictures() {
        return pictures;
    }

    public void setPictures(Pictures pictures) {
        this.pictures = pictures;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public RealmList<PlayList> getPlayLists() {
//        return playLists;
//    }
//
//    public void setPlayLists(RealmList<PlayList> playLists) {
//        this.playLists = playLists;
//    }
}
