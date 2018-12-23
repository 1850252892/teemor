package com.zlk.blog.controller;

import com.alibaba.fastjson.JSON;
import com.zlk.blog.emiltool.EmailService;
import com.zlk.blog.entity.BGroupKey;
import com.zlk.blog.entity.BOther;
import com.zlk.blog.entity.Blog;
import com.zlk.blog.entity.UserEssay;
import com.zlk.blog.model.*;
import com.zlk.blog.service.BGroupService;
import com.zlk.blog.service.BOtherService;
import com.zlk.blog.service.BlogService;
import com.zlk.blog.service.UserEssayService;
import freemarker.template.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@SessionAttributes(value = { "username", "additem", "itemdata" })
@RestController
public class BlogController {
    private static Logger logger = LoggerFactory.getLogger(BlogController.class);
    @Autowired
    BlogService bs;
    @Autowired
    UserEssayService userEssayService;
    @Autowired
    BGroupService bGroupService;
    @Autowired
    BOtherService bOtherService;

    @Autowired
    EmailService es;
    @Autowired
    Configuration configuration; //freeMarker configuration

    /**
     * 新建文章
     * @param essayModel
     * @param
     * @return
     */
    @PostMapping("/admin/essay")
    public String createEssay(@RequestBody EssayModel essayModel){
        Blog blog=essayModel;
//        Map<String, Object> map=model.asMap();
//        String username= (String) map.get("username");
        logger.info("用户:"+essayModel.getUid()+"新建文章");

        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        Date date=new Date();
        String bId=sdf.format(date)+ new Random().nextInt(1000);
        logger.info("文章id："+bId);
        blog.setBid(bId);
        sdf=new SimpleDateFormat("yyyy-MM-dd");
        blog.setBdate(sdf.format(date));

        BOther bOther=new BOther(bId,0,0,0,0);

        UserEssay userEssay=new UserEssay();
        userEssay.setBid(bId);
        userEssay.setUid(essayModel.getUid());

        BGroupKey bGroupKey=new BGroupKey();
        bGroupKey.setBid(essayModel.getBid());
        bGroupKey.setGid(essayModel.getGid());

        String key=bs.createBlog(blog,bOther,userEssay,bGroupKey);//添加到数据库

        return key;
    }

    @PostMapping(value = "/api/note",consumes = "application/json;charset=utf-8")
    public void uploadNote(@RequestBody AppNoteModel noteModel){
        logger.info("用户:"+noteModel.getUid()+"上传笔记");
        Blog blog=new Blog();
        blog.setBid(noteModel.getBid());
        blog.setBdata(noteModel.getBdata());
        blog.setBdate(noteModel.getBdate());
        blog.setBtitle(noteModel.getBtitle());

        BOther bOther=new BOther(noteModel.getBid(),0,0,0,0);

        UserEssay userEssay=new UserEssay();
        userEssay.setBid(noteModel.getBid());
        userEssay.setUid(noteModel.getUid());

        BGroupKey bGroupKey=new BGroupKey();
        bGroupKey.setBid(noteModel.getBid());
        bGroupKey.setGid(noteModel.getGid());

        String key=bs.createBlog(blog,bOther,userEssay,bGroupKey);//添加到数据库
    }

    @GetMapping(value = "/api/note")
    public String getnote(String username){
        List<AppNoteModel> list=new ArrayList<>();
        list=bs.selectNoteModel(username);
        return JSON.toJSONString(list);
    }

    @PutMapping(value="/api/note")
    public String apiPutNote(@RequestBody AppNoteModel noteModel){
        Blog blog=new Blog();
        blog.setBtitle(noteModel.getBtitle());
        blog.setBdate(noteModel.getBdate());
        blog.setBdata(noteModel.getBdata());
        blog.setBid(noteModel.getBid());
        return bs.updateByPrimaryKeySelective(blog);
    }

    @DeleteMapping(value = "/api/note")
    public String deleteNote(@RequestBody String bid){
        return bs.deleteBlog(bid);
    }

    /**
     * 获取文章列表
     * @param
     * @param username
     * @return
     */
    @GetMapping("/essaylist")
    public String apiGetBlogListByKey(String username){
        List<EssayModelTo> blogList;
        Map<String,String> map=new HashMap<>();
        map.put("username",username);
        blogList=bs.selectBlogByUid(map);
        return JSON.toJSONString(blogList);
    }

    /**
     * 删除文章
     * @param bId
     * @param
     * @return
     */
    @DeleteMapping ("/admin/essay")
    public String apiDeleteEssay(@RequestBody String bId){
//        Map<String,Object> m=model.asMap();
//        String username= (String) m.get("username");
//        UserEssay userEssay=new UserEssay();
//        userEssay.setUid(username);
//        userEssay.setBid(bId);
//        logger.warn("用户："+username+"执行删除文章："+bId);
//        String statues=userEssayService.delete(userEssay);
//        if (statues.equals("FALSE")){
//            logger.error("操作失败");
//            return statues;
//        }
       String statues =bs.deleteBlog(bId);
        logger.info("删除结果："+statues);
        return statues;
    }

    /**
     * 获取文章内容
     * @param bId
     * @return
     */
    @GetMapping(value = "/essay")
    public String apiGetEssayData(String bId){
        logger.info("获取文章："+bId+" 内容");
        EssayModel blog=bs.selectBlogById(bId);
        bOtherService.addBrowse(bId);//文章浏览次数加一
        if (blog==null){
            logger.error("获取文章失败");
            return JSON.toJSONString("{'status':'false'");
        }
        logger.info("文章标题为："+blog.getBtitle());
        return JSON.toJSONString(blog);
    }

    /**
     * 获取按时间归档信息
     * @param uId
     * @return
     */
    @GetMapping(value = "/groupbydate")
    public String apiGetGroupByDate(String uId){
       logger.info("获取用户："+uId+" 的归档信息");
        List<GroupByDateModel> list=bs.selectGroupByDate(uId);


        return JSON.toJSONString(list);
    }

    @GetMapping(value = "/api/essay/date")
    public String apiGetEssayFordate(@RequestParam("value") String date){


        Map<String,String> map1=new HashMap<>();
        map1.put("date",date);
        List<EssayModelTo> list=bs.selectEssayList(map1);
        return JSON.toJSONString(list);
    }

    @GetMapping(value = "/api/essay/category")
    public String apiGetEssayCategory(@RequestParam("value") String date){


        Map<String,String> map=new HashMap<>();
        map.put("category",date);
        List<EssayModelTo> list=bs.selectEssayList(map);
        return JSON.toJSONString(list);
    }
    @PutMapping(value = "/essay/great")
    public String apiPutGreat(@RequestBody Map<String,String> map){
        if (map.get("statu").equals("diss")){
            return bOtherService.addDiss(map.get("bid"));
        }else return bOtherService.addPraise(map.get("bid"));
    }

    @GetMapping(value = "/essay/essayother")
    public String apiGetEssayOther(@RequestParam("bid")String bid){
        BOther bOther=bOtherService.selectBother(bid);
        return JSON.toJSONString(bOther);
    }

    /**
     *
     *
     *
     * @param select
     * @return
     */
    @GetMapping("/article/getArticles")
    public String apiGetAriticles(ArticleSelect select){
        List<Blog> blogList;
        blogList=bs.selectArticles4P(select);
        return JSON.toJSONString(blogList);
    }

    @GetMapping("/article/getArticle")
    public String apiGetAriticle(ArticleSelect select){
        List<Blog> blogList;
        blogList=bs.selectArticles(select);
        return JSON.toJSONString(blogList.get(0));
    }

    @GetMapping("/getGroupByDate")
    public String apiGetGroupByDate(){
        List<ArticleByDate> list=bs.selectGroupByDate();
        return JSON.toJSONString(list);
    }

}
