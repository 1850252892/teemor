package com.zlk.blog.entity;

import java.util.Date;

public class CComment {
    private String ccid;

    private String uid;

    private Date ccdate;

    private Integer great;

    private String ccdata;

    public String getCcid() {
        return ccid;
    }

    public void setCcid(String ccid) {
        this.ccid = ccid == null ? null : ccid.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public Date getCcdate() {
        return ccdate;
    }

    public void setCcdate(Date ccdate) {
        this.ccdate = ccdate;
    }

    public Integer getGreat() {
        return great;
    }

    public void setGreat(Integer great) {
        this.great = great;
    }

    public String getCcdata() {
        return ccdata;
    }

    public void setCcdata(String ccdata) {
        this.ccdata = ccdata == null ? null : ccdata.trim();
    }
}