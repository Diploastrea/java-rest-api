package com.greenfoxacademy.guardiansofthegalaxy.model;

public class Groot {
    private String received;
    private final String translated = "I am Groot!";

    public Groot() {}

    public Groot(String received) {
        this.received = received;
    }

    public String getReceived() {
        return this.received;
    }

    public void setReceived(String received) {
        this.received = received;
    }

    public String getTranslated() {
        return this.translated;
    }
}