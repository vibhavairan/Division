package com.sih.division;

import java.io.Serializable;

public class BlogModel implements Serializable {
    private String bid;
    private String cont;
    private String title;
    private String abs;

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    private String Uid;
    private String udist;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbs() {
        return abs;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }


    public String getUdist() {
        return udist;
    }

    public void setUdist(String udist) {
        this.udist = udist;
    }

}
