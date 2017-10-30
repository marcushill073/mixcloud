package com.example.mixcloud.model;

import com.google.auto.value.AutoValue;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class Pictures extends RealmObject {

    @Ignore
    private int sessionId;
    
    private String extraLarge;
    private String large;
    private String medium;
    private String mediumMobile;
    private String small;
    private String thumbnail;

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getExtraLarge() {
        return extraLarge;
    }

    public void setExtraLarge(String extraLarge) {
        this.extraLarge = extraLarge;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getMediumMobile() {
        return mediumMobile;
    }

    public void setMediumMobile(String mediumMobile) {
        this.mediumMobile = mediumMobile;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
