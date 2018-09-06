package com.zlk.blog.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.zlk.blog.emiltool.EmailService;
import com.zlk.blog.entity.User;
import com.zlk.blog.service.UserService;
import com.zlk.blog.service.impl.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@SessionAttributes({"email","code","username"})
public class UserController {
    @Autowired
    UserService us;
    @Autowired
    EmailService es;
    @Autowired
    RedisService redisService;

    /**
     * 向用户的注册邮箱发送验证码，并在发送前判断是否已注册
     * @param email 用户提交的邮箱地址
//     * @param model
     * @return 邮件发送是否成功
     */
    @GetMapping("/api/vercode")
    public String sendMailKey(String email){
        Map<String,String> m=new HashMap<String, String>();
        m.put("uid",email);
        if(us.selectUsers(m).size()>0){
            return "邮箱已注册";
        }
        int code= new Random().nextInt(8000)+1234;//生成一个随机码
        Map<String, Object> map = new HashMap<>();
        map.put("time", new Date());
        map.put("message",code);
        map.put("toUserName", email);
        String statue=es.sendHtmlMail(email, "注册：验证码", map,"model.ftl");
        if (statue.equals("F")){
            return "邮件发送失败，请检查邮箱地址是否正确";
        }
        redisService.setStr(email,""+code);
//        model.addAttribute("code",code);
        return "验证码已发送";
    }

    /**
     * 获取用户提交的验证码，判断验证码是否正确
//     * @param code 用户提交的验证码
//     * @param email
//     * @param model
     * @return
     */
    @PostMapping("/api/vercode")
    public String submitCode(@RequestBody Map<String,String> map){
       String email=map.get("email");
       String code=map.get("code");
       String oldcode=redisService.getStr(email);
       System.out.println("oldCode:"+oldcode);
        if (!code.equals(oldcode)){
            return "False";
        }
        redisService.del(email);
        return "True";
    }

    /**
     * 用户注册基本信息
     * @param u 将用户提及的json数据适配成的User对象
     * @param
     * @return 返回用户注册请求信息
     */
    @PostMapping("/api/user")
    public String addUser(@RequestBody User u){
        Map<String,String> map=new HashMap<String, String>();
//        map.put("uid",u.getUid());
        if(us.selectUsers(map).size()>0){
            return "此帐号已存在";
        }
        return us.addUser(u);
    }



    /**
     * 登录失败
     * @return
     */
    @RequestMapping("/loginFailure")
    public @ResponseBody String logFailure(){
        Map<String,String> m=new HashMap<String, String>();
        m.put("status","false");
        m.put("data","failure");
        return JSONUtils.toJSONString(m);
    }


    @GetMapping(value = "/user")
    public String apiGetUser(@RequestParam("username") String username){
        User user=us.selectUser(username);
        return JSON.toJSONString(user);
    }



}
