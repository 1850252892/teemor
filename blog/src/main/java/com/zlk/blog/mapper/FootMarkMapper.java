package com.zlk.blog.mapper;

import com.zlk.blog.entity.FootMark;

public interface FootMarkMapper {
    int insert(FootMark record);

    int insertSelective(FootMark record);
}