package com.zlk.blog.mapper;

import com.zlk.blog.entity.Blog;
import com.zlk.blog.model.*;
import com.zlk.blog.provider.EssayPro;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

public interface BlogMapper {
    int deleteByPrimaryKey(String bid);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(String bid);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKeyWithBLOBs(Blog record);

    int updateByPrimaryKey(Blog record);

    @Select("select * \n"+
            "from tb_blog a,tb_bgroup b,tb_group c,tb_useressay d,tb_user e,tb_bother f\n"+
            "where a.bId=#{bid} and a.bId=b.bId and b.gId=c.gId and d.bId=a.bId and e.uId=d.uId and a.bId=f.bId")
    EssayModel selectEssayByBid(String bid);

    @Select("select * from tb_blog,tb_useressay where tb_useressay.uId=#{uid} and tb_blog.bId=tb_useressay.bId")
    List<Blog> selectBlogByUid(String uid);

    @Select("select * from tb_bgroup,tb_blog where tb_bgroup.gId=#{gid} and tb_group.bId=tb_blog.bId")
    List<Blog> selectBlogByGid(String gid);

    @Select("SELECT DATE_FORMAT( bDate, \"%Y-%m\" ) date, COUNT( * ) count\n" +
            "FROM tb_blog,tb_useressay\n" +
            "WHERE uId=#{uId} and tb_blog.bId=tb_useressay.bId\n" +
            "GROUP BY DATE_FORMAT( bDate, \"%Y-%m\" )")
    List<GroupByDateModel> selectGroupByDateModel(String uId);

    @SelectProvider(type = EssayPro.class,method = "getEssayList")
//    @Select("SELECT a.bid bid, a.btitle btitle, a.bdata bdata, a.bdate bdate, d.browse browse, f.nickname nickname, f.uid uid FROM tb_blog a, tb_useressay c, tb_bother d, tb_user f WHERE (a.bdate like #{date}\"%\" and a.bid=c.bid and a.bid=d.bid and c.uid=f.uid)")
    List<EssayModelTo> selectEssayList(Map<String, String> map);

    @Select("select * from tb_useressay a,tb_blog b,tb_bgroup c where a.uid=#{username} and a.bid=b.bid and b.bid=c.bid")
    List<AppNoteModel> selectNoteModel(String username);

    List<Blog> selectArticles(ArticleSelect select);
}