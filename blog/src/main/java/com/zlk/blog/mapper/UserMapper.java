package com.zlk.blog.mapper;

import com.zlk.blog.entity.User;
import com.zlk.blog.provider.UserPro;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(String uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    @SelectProvider(type = UserPro.class, method ="getUserByMap" )
    List<User> selectByMap(Map<String, String> m);
}