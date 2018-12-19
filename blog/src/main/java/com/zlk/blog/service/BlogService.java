package com.zlk.blog.service;

import com.github.pagehelper.PageInfo;
import com.zlk.blog.entity.BGroupKey;
import com.zlk.blog.entity.BOther;
import com.zlk.blog.entity.Blog;
import com.zlk.blog.entity.UserEssay;
import com.zlk.blog.model.*;

import java.util.List;
import java.util.Map;

public interface BlogService {

    /**
     *发布新文章
     * @param blog 新建的文章信息
     * @return 添加状态
     */
    String createBlog(Blog blog, BOther bother, UserEssay userEssay, BGroupKey bGroupKey);

    /**
     *删除文章
     * @param bId 要删除的博客Id
     * @return 删除状态
     */
    String deleteBlog(String bId);

    /**
     *查询文章信息
     * @param bId 要查询的博客Id
     * @return 博客信息
     */
    EssayModel selectBlogById(String bId);

    /**
     *查询用户的博客
     * @param uId 要查询的用户Id
     * @return
     */
    List<EssayModelTo> selectBlogByUid(Map<String, String> map);

    /**
     * 查询该分组下的全部文章
     * @param gid 分组id
     * @return
     */
    List<EssayModelTo>selectEssayList(Map<String, String> M);

    /**
     * 更新博客信息
     * @param record
     * @return
     */
    String updateByPrimaryKeySelective(Blog record);


    /**
     * 按时间分组查询用户文章
     * @param uId
     * @return
     */
    List<GroupByDateModel> selectGroupByDate(String uId);


    List<AppNoteModel> selectNoteModel(String username);

    PageInfo<Blog> selectArticles(ArticleSelect select);
}
