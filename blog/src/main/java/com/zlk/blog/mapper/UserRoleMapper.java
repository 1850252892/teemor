package com.zlk.blog.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserRoleMapper {

    @Insert("insert into tb_role values(#{uId},#{role})")
    int insert(@Param("uId") String uId, @Param("role") String role);

    @Delete("delete from tb_role where uId=#{uId} and role=#{role}")
    int delete(@Param("uId") String uId, @Param("role") String role);

    @Select("select role from tb_role where uId=#{uId}")
    List<String> select(String uId);

    @Select("select pwd from tb_user where uId=#{uId}")
    String selectPwd(String uId);
}
