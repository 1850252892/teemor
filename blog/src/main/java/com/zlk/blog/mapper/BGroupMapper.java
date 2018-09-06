package com.zlk.blog.mapper;

import com.zlk.blog.entity.BGroupKey;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface BGroupMapper {
    int deleteByPrimaryKey(BGroupKey key);

    int insert(BGroupKey record);

    int insertSelective(BGroupKey record);

    @Delete("delete from tb_bgroup where bId=#{bid}")
    int deleteByBid(String bid);

    @Update("update tb_bgroup set gId=#{gid} where bId=#{bid}")
    int update(BGroupKey bGroupKey);

    @Delete("delete from tb_bgroup where gId=#{gid}")
    int deleteGroup(@Param("gid") String gid);
}