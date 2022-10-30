package com.greenfoxacademy.guardiansofthegalaxy.model;

public class Arrow {
    private Double distance;
    private Double time;
    private Double speed;

    public Arrow() {}

    public Arrow(Double distance, Double time) {
        this.distance = distance;
        this.time = time;
        this.speed = this.distance / this.time;
    }

    public Double getDistance() {
        return this.distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getTime() {
        return this.time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public Double getSpeed() {
        return this.speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }
}