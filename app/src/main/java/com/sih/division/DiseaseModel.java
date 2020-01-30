package com.sih.division;

import java.io.Serializable;

public class DiseaseModel implements Serializable {
    private String did, hid, dname, patcount, date;

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String name) {
        this.dname = name;
    }

    public String getPatcount() {
        return patcount;
    }

    public void setPatcount(String patcount) {
        this.patcount = patcount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }
}
