package com.zlk.blog.entity;

public class BComment {
    private String bcid;

    private String bid;

    private String uid;



    private String bcdate;

    private Integer great;

    private String bcdata;

    public String getBcid() {
        return bcid;
    }

    public void setBcid(String bcid) {
        this.bcid = bcid == null ? null : bcid.trim();
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid == null ? null : bid.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getBcdate() {
        return bcdate;
    }

    public void setBcdate(String bcdate) {
        this.bcdate = bcdate;
    }

    public Integer getGreat() {
        return great;
    }

    public void setGreat(Integer great) {
        this.great = great;
    }

    public String getBcdata() {
        return bcdata;
    }

    public void setBcdata(String bcdata) {
        this.bcdata = bcdata == null ? null : bcdata.trim();
    }
}