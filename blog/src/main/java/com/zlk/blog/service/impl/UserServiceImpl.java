package com.zlk.blog.service.impl;

import com.zlk.blog.entity.User;
import com.zlk.blog.mapper.UserMapper;
import com.zlk.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper um;
    @Override
    public String addUser(User u) {
        Map<String,String> m=new HashMap<String, String>();
//        //检测手机号是否注册
//        m.put("phone",u.getPhone());
//        if (um.selectByMap(m).size()>0)
//            return "phone exist";
//        m.clear();
//
//        //检测邮箱是否注册
//        m.put("email",u.getEmail());
//        if (um.selectByMap(m).size()>0)
//            return "email exist";

        //注册成功返回T失败返回F
        if(um.insert(u)>0)
            return "T";
        return "F";
    }

    @Override
    public String deleteUser(Map<String, String> map) {
        if (um.deleteByPrimaryKey(map.get("uid"))>0)
            return "T";
        else
            return "F";
    }

    @Override
    public User selectUser(String username) {

        return um.selectByPrimaryKey(username);
    }

    @Override
    public List<User> selectUsers(Map<String, String> map) {

        return  um.selectByMap(map);
    }

    @Override
    public String updateUser(User user) {
        if (um.updateByPrimaryKeySelective(user)>0)
            return "success";
        else
            return "false";
    }

    @Override
    public String login(Map<String, String> m) {
        Map<String,String> map=new HashMap<>();
        switch (m.get("type")){
            case "email":
                map.put("email",m.get("id"));
                break;
            case "id":
                map.put("id",m.get("id"));
                break;
            case "phone":
                map.put("phone",m.get("id"));
                break;
        }
        User u=um.selectByMap(map).get(0);
        if (u==null){
            return "该帐号不存在";
        }
        if (u.getPwd().equals(m.get("pwd"))){
            return "登录成功";
        }else {
            return "密码错误";
        }
    }
}
