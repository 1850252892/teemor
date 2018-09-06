package com.zlk.blog.mapper;

import com.zlk.blog.entity.BComment;
import com.zlk.blog.model.BCommentModel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BCommentMapper {
    int deleteByPrimaryKey(String bcid);

    int insert(BComment record);

    int insertSelective(BComment record);

    BComment selectByPrimaryKey(String bcid);

    int updateByPrimaryKeySelective(BComment record);

    int updateByPrimaryKeyWithBLOBs(BComment record);

    int updateByPrimaryKey(BComment record);

    @Select("select * from tb_bcomment a,tb_user b where a.bId=#{bid} and a.uId=b.uId")
    List<BCommentModel> selectBcommentModel(String bid);

    @Delete("delete from tb_bcomment where bId=#{bid}")
    int deleteBcommentAll(String bid);
}