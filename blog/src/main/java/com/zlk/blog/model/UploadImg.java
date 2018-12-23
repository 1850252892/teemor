package com.zlk.blog.model;

import java.util.Arrays;

public class UploadImg {
    private Integer errno;
    private String[] data;

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ImgInfo [error=" + errno + ", url=" + Arrays.toString(data) + "]";
    }
}
