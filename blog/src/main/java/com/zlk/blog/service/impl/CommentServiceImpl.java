package com.zlk.blog.service.impl;

import com.zlk.blog.entity.BComment;
import com.zlk.blog.mapper.BCommentMapper;
import com.zlk.blog.model.BCommentModel;
import com.zlk.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    BCommentMapper bCommentMapper;

    @Override
    public String createBcomment(BComment bComment) {
        int key=bCommentMapper.insert(bComment);
        if (key>0)
            return "TRUE";
        return "FALSE";
    }

    @Override
    public List<BCommentModel> selectBcomment(String bId) {
        return bCommentMapper.selectBcommentModel(bId);
    }

    @Override
    public String deleteBcomment(String bcId) {
        int key=bCommentMapper.deleteByPrimaryKey(bcId);
        if (key>0)
            return "TRUE";
        return "FALSE";
    }

    @Override
    public int deleteBcommentAll(String bid) {
        return bCommentMapper.deleteBcommentAll(bid);
    }

    @Override
    public String praiseComment(String bid) {
        BComment bComment=bCommentMapper.selectByPrimaryKey(bid);
        bComment.setGreat(bComment.getGreat()+1);
        int key= bCommentMapper.updateByPrimaryKey(bComment);
        if (key>0)
            return ""+bComment.getGreat();
        return "FALSE";
    }
}
