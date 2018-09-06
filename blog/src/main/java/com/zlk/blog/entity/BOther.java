package com.zlk.blog.entity;

public class BOther {
    private String bid;

    private Integer collect;

    private Integer great;

    private Integer diss;

    private Integer browse;

    public BOther(String bid,int collect,int great,int diss,int browse){
        this.bid=bid;
        this.collect=collect;
        this.great=great;
        this.diss=diss;
        this.browse=browse;
    }

    public BOther(){

    }


    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid == null ? null : bid.trim();
    }

    public Integer getCollect() {
        return collect;
    }

    public void setCollect(Integer collect) {
        this.collect = collect;
    }

    public Integer getGreat() {
        return great;
    }

    public void setGreat(Integer great) {
        this.great = great;
    }

    public Integer getDiss() {
        return diss;
    }

    public void setDiss(Integer diss) {
        this.diss = diss;
    }

    public Integer getBrowse() {
        return browse;
    }

    public void setBrowse(Integer browse) {
        this.browse = browse;
    }
}