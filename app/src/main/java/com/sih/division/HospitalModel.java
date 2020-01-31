package com.sih.division;

import java.io.Serializable;

public class HospitalModel implements Serializable {
    private String hid;
    private String hname;
    private String hpass;
    private String dist;
    private String vaccount;
    private String number;
    private String longitude;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    private String latitude;
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String address;
    private byte[] photo;

    public String getHid() {
        return hid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
