package com.zlk.blog.service.impl;

import com.zlk.blog.entity.Picture;
import com.zlk.blog.mapper.PictureMapper;
import com.zlk.blog.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    PictureMapper pictureMapper;
    @Override
    public int insert(Picture picture) {
        return pictureMapper.insert(picture);
    }

    @Override
    public List<Picture> select(String uid) {
        return pictureMapper.select(uid);
    }

    @Override
    public int delete(String bid) {
        return pictureMapper.delete(bid);
    }
}
