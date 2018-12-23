package com.zlk.blog.controller;

import com.alibaba.fastjson.JSON;
import com.zlk.blog.entity.Group;
import com.zlk.blog.model.GroupModel;
import com.zlk.blog.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SessionAttributes(value={"username"})
@RestController
public class GroupController {
    private static Logger logger = LoggerFactory.getLogger(GroupController.class);
    @Autowired
    GroupService groupService;

    /**
     * 新建分组
     * @param group
     * @param
     * @return
     */
    @PostMapping(value = "/admin/group",produces = "application/json;charset=utf-8")
    public String apiCreateGroup(@RequestBody Group group){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
        String gid=simpleDateFormat.format(new Date());
        group.setGid(gid);
        String status=groupService.addGroup(group);
        if (status=="FALSE")
            logger.error("创建分组失败");
        logger.info("创建分组成功，分组Id为："+status);
        return "TRUE";
    }

    @PostMapping(value = "/api/category")
    public String uploadCategory(@RequestBody Group group){
        logger.info("创建分组成功，分组Id为："+group.getGname());
        String status=groupService.addGroup(group);
        if (status=="FALSE")
            logger.error("上传分组失败");
        logger.info("创建分组成功，分组Id为："+status);
        return "TRUE";
    }

    /**
     * 删除分组
     * @param
     * @return
     */
    @DeleteMapping(value = "/admin/group")
    public String apiDeleteGroup(@RequestBody String gId){
//        String uId= (String) map.get("uId");
//        Integer gId= Integer.valueOf((String) map.get("gId"));
//        logger.info("gid="+gId+"  uId"+uId);
        String status=groupService.deleteGroup(gId);
        return status;
    }

    /**
     * 获取分组列表
     * @param uId
     * @return
     */
//    @GetMapping(value = "/selectGroupList")
    @GetMapping(value = "/grouplist")
    public String apiSelectGroupList(@RequestParam("uId") String uId){
        logger.info(uId+"请求数据");
        List<Group> groupList=groupService.selectGroupList(uId);
        String result= JSON.toJSONString(groupList);
        return result;
    }

    /**
     * 获取分组模型信息
     * @param username
     * @return
     */
//    @GetMapping(value = "/selectGroupModel")
    @GetMapping(value = "/groupmodel")
    public String apiSelectGroupModel(String username){
        logger.info(username+"请求groupModel");
        List<GroupModel> groupModels=groupService.selectGroupModel(username);
        String result=JSON.toJSONString(groupModels);
        return result;
    }

    /**
     * 修改分组信息
     * @param g
     * @return
     */
//    @PostMapping(value = "/admin/updateGroup",produces = "application/json;charset=utf-8")
    @PutMapping(value = "/admin/group")
    public String apiUpdateGroup(@RequestBody  Group g){
        logger.info("修改分组"+g.getGid()+"信息");
        logger.info("修改名字为"+g.getGname());
        logger.info("修改描述为"+g.getGlabel());

        String status=groupService.updateGroupData(g);
        return status;
    }

    @GetMapping(value = "/api/category")
    public String apiCategrory(@RequestParam("gid") String gid){

        return JSON.toJSONString(groupService.selectGroup(gid));
    }
}
