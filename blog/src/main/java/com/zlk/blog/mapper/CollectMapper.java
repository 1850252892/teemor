package com.zlk.blog.mapper;

import com.zlk.blog.entity.Collect;
import com.zlk.blog.entity.CollectKey;

public interface CollectMapper {
    int deleteByPrimaryKey(CollectKey key);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(CollectKey key);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);
}