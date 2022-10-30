package com.greenfoxacademy.guardiansofthegalaxy.model;

public class RocketShip {
    private Integer caliber25;
    private Integer caliber30;
    private Integer caliber50;
    private String shipstatus;
    private Boolean ready;

    public RocketShip() {
        this.caliber25 = 0;
        this.caliber30 = 0;
        this.caliber50 = 0;
        this.shipstatus = "empty";
        this.ready = false;
    }

    public RocketShip(Integer caliber25, Integer caliber30, Integer caliber50) {
        this.caliber25 = caliber25;
        this.caliber30 = caliber30;
        this.caliber50 = caliber50;
        this.shipstatus = this.getStatus();
        this.ready = this.checkIsReady();
    }

    private String getStatus() {
        if (this.getCargo() == 0) return "empty";
        if (this.getCargo() == 100) return "full";
        if (this.getCargo() > 100) return "overloaded";
        return this.getCargo() + "%";
    }

    private Integer getCargo() {
        return (this.caliber25 + this.caliber30 + this.caliber50) / 125;
    }

    public Boolean checkIsReady() {
        return this.shipstatus.equals("full");
    }

    public Integer getCaliber25() {
        return this.caliber25;
    }

    public void setCaliber25(Integer caliber25) {
        this.caliber25 = caliber25;
    }

    public Integer getCaliber30() {
        return this.caliber30;
    }

    public void setCaliber30(Integer caliber30) {
        this.caliber30 = caliber30;
    }

    public Integer getCaliber50() {
        return this.caliber50;
    }

    public void setCaliber50(Integer caliber50) {
        this.caliber50 = caliber50;
    }

    public String getShipstatus() {
        return this.shipstatus;
    }

    public void setShipstatus(String shipstatus) {
        this.shipstatus = shipstatus;
    }

    public Boolean getReady() {
        return this.ready;
    }

    public void setReady(Boolean ready) {
        this.ready = ready;
    }
}