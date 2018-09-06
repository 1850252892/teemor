package com.zlk.blog.service;

import com.zlk.blog.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     *
     * @param u user对象
     * @return 返回注册用户操作结果信息
     */
    String addUser(User u);

    /**
     *
     * @return 返回注销用户操作结果信息
     */
    String deleteUser(Map<String, String> map);

    /**
     *
     * @param map 查询条件
     * @return 单个用户对象
     */
    User selectUser(String username);

    /**
     *
     * @param map 查询条件
     * @return 用户列表
     */
    List<User> selectUsers(Map<String, String> map);

    /**
     *
     * @param map 要修改的信息
     * @return 操作结果
     */
    String updateUser(User user);

    /**
     *
     * @param map 用户帐号和密码
     * @return 用户帐号或迷茫是否正确
     */
    String login(Map<String, String> map);
}
