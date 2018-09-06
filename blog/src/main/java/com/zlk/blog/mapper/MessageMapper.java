package com.zlk.blog.mapper;

import com.zlk.blog.entity.Message;

public interface MessageMapper {
    int insert(Message record);

    int insertSelective(Message record);
}