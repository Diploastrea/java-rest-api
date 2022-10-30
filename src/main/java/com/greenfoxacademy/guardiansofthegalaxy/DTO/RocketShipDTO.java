package com.greenfoxacademy.guardiansofthegalaxy.DTO;

public class RocketShipDTO {
    private String received;
    private Integer amount;
    private String shipstatus;
    private Boolean ready;

    public String getReceived() {
        return this.received;
    }

    public void setReceived(String received) {
        this.received = received;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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