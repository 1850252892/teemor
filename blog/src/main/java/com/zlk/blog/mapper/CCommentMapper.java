package com.zlk.blog.mapper;

import com.zlk.blog.entity.CComment;

public interface CCommentMapper {
    int deleteByPrimaryKey(String ccid);

    int insert(CComment record);

    int insertSelective(CComment record);

    CComment selectByPrimaryKey(String ccid);

    int updateByPrimaryKeySelective(CComment record);

    int updateByPrimaryKeyWithBLOBs(CComment record);

    int updateByPrimaryKey(CComment record);
}