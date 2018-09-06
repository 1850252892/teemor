package com.zlk.blog.entity;

public class Group {
    private String gid;

    private String uid;

    private String gname;

    private String glabel;

    private Integer ggrade;

    private String gparent;

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid == null ? null : gid.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname == null ? null : gname.trim();
    }

    public String getGlabel() {
        return glabel;
    }

    public void setGlabel(String glabel) {
        this.glabel = glabel == null ? null : glabel.trim();
    }

    public Integer getGgrade() {
        return ggrade;
    }

    public void setGgrade(Integer ggrade) {
        this.ggrade = ggrade;
    }

    public String getGparent() {
        return gparent;
    }

    public void setGparent(String gparent) {
        this.gparent = gparent == null ? null : gparent.trim();
    }
}