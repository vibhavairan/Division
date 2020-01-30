package com.sih.division;

import java.io.Serializable;

public class HospitalModel implements Serializable {
    private String hid, hname, hpass, dist, vaccount;

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public String getHpass() {
        return hpass;
    }

    public void setHpass(String hpass) {
        this.hpass = hpass;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getVaccount() {
        return vaccount;
    }

    public void setVaccount(String vaccount) {
        this.vaccount = vaccount;
    }
}
