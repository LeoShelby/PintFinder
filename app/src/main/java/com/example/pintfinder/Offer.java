package com.example.pintfinder;

public class Offer {
    private String pubName;
    private String name;
    private String description;
    private String expireTime;

    public Offer(String pubName, String name, String description, String expireTime) {
        this.pubName = pubName;
        this.name = name;
        this.description = description;
        this.expireTime = expireTime;
    }

    public String getPubName() {
        return pubName;
    }

    public void setPubName(String pubName) {
        this.pubName = pubName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }
}
