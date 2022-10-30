package com.greenfoxacademy.guardiansofthegalaxy.model;

public class Error {
    private String error;

    public Error() {}

    public Error(String error) {
        this.error = error;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }
}