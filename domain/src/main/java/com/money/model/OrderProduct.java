package com.money.model;

import java.util.Date;

public class OrderProduct extends OrderProductKey {
    private Date addTime;

    private Short state;

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }
}