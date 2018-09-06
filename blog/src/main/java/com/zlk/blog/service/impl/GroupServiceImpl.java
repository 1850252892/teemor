package com.zlk.blog.service.impl;

import com.zlk.blog.entity.Group;
import com.zlk.blog.mapper.BGroupMapper;
import com.zlk.blog.mapper.GroupMapper;
import com.zlk.blog.model.GroupModel;
import com.zlk.blog.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupMapper gm;
    @Autowired
    BGroupMapper bGroupMapper ;

    @Override
    public String addGroup(Group group) {
        int key=gm.addGroup(group);
        if (key>0){
            return group.getGid();
        }
        return "FALSE";
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public String deleteGroup(String gid) {

        bGroupMapper.deleteGroup(gid);//将当前分组下的文章全部改到默认分组
        int key=gm.deleteGroup(gid);//删除分组
        if (key>0){
            return "TRUE";
        }
        return "FALSE";
    }

    @Override
    public List<Group> selectGroupList(String uId) {
        List<Group> list=gm.selectGroupList(uId);
        Collections.reverse(list);
        return list;
    }

    @Override
    public String selectGroupBybId(String bId) {
        return gm.selecGroupBybId(bId);
    }

    @Override
    public String updateGroupData(Group group) {
        int key=gm.updateByPrimaryKeySelective(group);
        if (key>0){
            return "TRUE";
        }
        return "FALSE";
    }

    @Override
    public List<GroupModel> selectGroupModel(String uId) {
        List<GroupModel> list=gm.selectGroupModel(uId);
        Collections.reverse(list);
        return list;
    }

    @Override
    public Group selectGroup(String gid) {
        return gm.selectByPrimaryKey(gid);
    }
}
