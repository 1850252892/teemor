package com.zlk.blog.mapper;

import com.zlk.blog.entity.UserEssay;
import org.apache.ibatis.annotations.Delete;

public interface UserEssayMapper {

    int deleteByPrimaryKey(UserEssay userEssay);

    int insert(UserEssay userEssay);

    int insertSelective(UserEssay userEssay);

    @Delete("delete from tb_useressay where bId=#{bid}")
    int deleteByBid(String bid);

}
