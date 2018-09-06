package com.zlk.blog.mapper;

import com.zlk.blog.entity.BOther;

public interface BOtherMapper {
    int deleteByPrimaryKey(String bid);

    int insert(BOther record);

    int insertSelective(BOther record);

    BOther selectByPrimaryKey(String bid);

    int updateByPrimaryKeySelective(BOther record);

    int updateByPrimaryKey(BOther record);
}