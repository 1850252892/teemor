package com.zlk.blog.service;

import com.zlk.blog.entity.BComment;
import com.zlk.blog.model.BCommentModel;

import java.util.List;

public interface CommentService {
    /**
     * 添加一条评论信息
     * @param bComment
     * @return
     */
    String createBcomment(BComment bComment);

    /**
     * 查询一篇文章的全部评论
     * @param bId
     * @return
     */
    List<BCommentModel> selectBcomment(String bId);

    /**
     * 删除某一条评论
     * @param bcId
     * @return
     */
    String deleteBcomment(String bcId);

    /**
     * 删除某篇文章的全部评论
     * @param bid
     * @return
     */
    int deleteBcommentAll(String bid);

    /**
     * 赞该评论
     * @param bid
     * @return
     */
    String praiseComment(String bid);
}
