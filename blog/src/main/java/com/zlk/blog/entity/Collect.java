package com.zlk.blog.entity;

public class Collect extends CollectKey {
    private String gid;

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid == null ? null : gid.trim();
    }
}