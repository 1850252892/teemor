package com.zlk.blog.controller;

import com.alibaba.fastjson.JSON;
import com.zlk.blog.entity.BComment;
import com.zlk.blog.model.BCommentModel;
import com.zlk.blog.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;


@RestController
@SessionAttributes(value = { "username", "additem", "itemdata" })
public class CommentController {
    private static Logger logger= LoggerFactory.getLogger(CommentController.class);
    @Autowired
    CommentService commentService;

    @PostMapping(value = "/comment")
    public String apiSubmitComment(@RequestBody BComment bComment){
//        Map<String, Object> map=model.asMap();
//        String uId= (String) map.get("username");
//        logger.info("用户："+uId+" 发表评论，文章Id:"+bComment.getBid());
//        bComment.setUid(uId);//用户名
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        bComment.setBcdate(format.format(date));
        format=new SimpleDateFormat("yyyyMMddHHmmss");
        String id=format.format(date)+ new Random().nextInt(1000);
        bComment.setBcid(id);//评论id
        logger.info("评论Id:"+id);
        bComment.setGreat(0);
        String result=commentService.createBcomment(bComment);
        return  result;
    }

    @GetMapping(value = "/comment")
    public String apiGetCommentList(@RequestParam("bid") String bId){
        logger.info("请求获取文章："+bId+" 的全部评论");
        List<BCommentModel> list=commentService.selectBcomment(bId);
        return JSON.toJSONString(list);
    }

    public String deleteComment(String cId){
        logger.info("删除评论："+cId);
        String result=commentService.deleteBcomment(cId);
        logger.info("删除结果为："+result);
        return result;
    }

    public String deleteCommentAll(String bId){
        logger.info("删除文章:"+bId+" 的全部评论");
        int result=commentService.deleteBcommentAll(bId);
        if (result<0){
            logger.error("删除操作出错");
            return "FALSE";
        }
        logger.info("删除结果为：共删除了"+result+"条数据");
        return "TRUE";
    }

    @PutMapping("/comment/praise")
    public String apiPraiseComment(@RequestBody String bid){

        return commentService.praiseComment(bid);
    }
}
