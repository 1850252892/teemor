package com.zlk.blog.service;

import com.zlk.blog.entity.Picture;

import java.util.List;

public interface PictureService {
     int insert(Picture picture);

     List<Picture> select(String uid);

     int delete(String bid);

}
