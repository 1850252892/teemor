package com.zlk.blog.service.impl;

import com.zlk.blog.entity.BGroupKey;
import com.zlk.blog.entity.BOther;
import com.zlk.blog.entity.Blog;
import com.zlk.blog.entity.UserEssay;
import com.zlk.blog.mapper.*;
import com.zlk.blog.model.AppNoteModel;
import com.zlk.blog.model.EssayModel;
import com.zlk.blog.model.EssayModelTo;
import com.zlk.blog.model.GroupByDateModel;
import com.zlk.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogMapper bm;
    @Autowired
    BOtherMapper bOtherMapper;
    @Autowired
    UserEssayMapper userEssayMapper;
    @Autowired
    BGroupMapper bGroupMapper;
    @Autowired
    BCommentMapper bCommentMapper;

    public String createBlog(Blog blog) {
        int key=bm.insert(blog);
        if (key>0)
            return "TRUE";
        return "FALSE";
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public String createBlog(Blog blog, BOther bother, UserEssay userEssay, BGroupKey bGroupKey) {
        int key;
        bm.insert(blog);
        bOtherMapper.insert(bother);
        userEssayMapper.insert(userEssay);
        key=bGroupMapper.insert(bGroupKey);
        if (key>0)
        return "TRUE";
        else
            return "FALSE";
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public String deleteBlog(String bId) {
        bGroupMapper.deleteByBid(bId);
        userEssayMapper.deleteByBid(bId);
        bOtherMapper.deleteByPrimaryKey(bId);
        bCommentMapper.deleteBcommentAll(bId);
        int key=bm.deleteByPrimaryKey(bId);
        System.out.println("key="+key);
        if (key>0)
            return "TRUE";
        return "FALSE";
    }

    @Override
    public EssayModel selectBlogById(String bId) {
        return bm.selectEssayByBid(bId);
    }

    @Override
    public List<EssayModelTo> selectBlogByUid(Map<String,String> map) {
        List<EssayModelTo> list=bm.selectEssayList(map);
        Collections.reverse(list);
        return list;
    }

    @Override
    public List<EssayModelTo> selectEssayList(Map<String,String> map) {
        List<EssayModelTo> list=bm.selectEssayList(map);
        Collections.reverse(list);
        return list;
    }

    @Override
    public String updateByPrimaryKeySelective(Blog record) {
        int key=bm.updateByPrimaryKeySelective(record);
        if (key>0)
            return "TRUE";
        return "FALSE";
    }

    @Override
    public List<GroupByDateModel> selectGroupByDate(String uId) {
        List<GroupByDateModel> list=bm.selectGroupByDateModel(uId);
        Collections.reverse(list);
        return list;
    }

    @Override
    public List<AppNoteModel> selectNoteModel(String username) {
        return bm.selectNoteModel(username);
    }
}
