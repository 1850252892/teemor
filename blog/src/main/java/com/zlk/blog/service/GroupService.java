package com.zlk.blog.service;

import com.zlk.blog.entity.Group;
import com.zlk.blog.model.GroupModel;

import java.util.List;
import java.util.Map;


public interface GroupService {

    /**
     *新建分组
     * @param group 新建分组的分组信息
     * @return 创建结果
     */
     String addGroup(Group group);

    /**
     *删除分组
     * @param map 要删除的分组号以及用户帐号
     * @return
     */
     String deleteGroup(String gid);

    /**
     *
     * @param uId 查询分组信息的用户Id
     * @return
     */
    List<Group> selectGroupList(String uId);

    /**
     *
     * @param bId 查询分组名的文章号
     * @return 分组名字
     */
    String selectGroupBybId(String bId);

    /**
     *
     * @param group 需要修改的分组信息
     * @return
     */
    String updateGroupData(Group group);

    /**
     * 获取GroupModel
     * @param uId
     * @return
     */
    List<GroupModel> selectGroupModel(String uId);

    Group selectGroup(String gid);
}
