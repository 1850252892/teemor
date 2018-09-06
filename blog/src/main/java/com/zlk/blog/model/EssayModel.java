package com.zlk.blog.model;

import com.zlk.blog.entity.Blog;

public class EssayModel extends Blog {
    private String gid;
    private String gname;
    private String uid;
    private String nickname;
    private int collect;
    private int great;
    private int diss;
    private int browse;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }

    public int getGreat() {
        return great;
    }

    public void setGreat(int great) {
        this.great = great;
    }

    public int getDiss() {
        return diss;
    }

    public void setDiss(int diss) {
        this.diss = diss;
    }

    public int getBrowse() {
        return browse;
    }

    public void setBrowse(int browse) {
        this.browse = browse;
    }
}
