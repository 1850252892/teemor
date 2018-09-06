package com.zlk.blog.mapper;

import com.zlk.blog.entity.Picture;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PictureMapper {

    @Insert("insert into tb_picture (uid,url,bid,pname) values (#{uid},#{url},#{bid},#{pname})")
    int insert(Picture picture);

    @Select("select * from tb_picture where uid=#{uid}")
    List<Picture> select(String uid);

    @Delete("delete from tb_picture where bid=#{bid}")
    int delete(String bid);

}
