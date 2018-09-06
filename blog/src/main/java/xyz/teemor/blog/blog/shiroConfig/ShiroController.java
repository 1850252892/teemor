package xyz.teemor.blog.blog.shiroConfig;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/11.
 */
@Controller
public class ShiroController {

    /**
     * 登录方法
     * @param
     * @return
     */
    @RequestMapping(value = "/ajaxLogin")
    @ResponseBody
    public void ajaxLogin() {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("1008611", "123456",true);

        try {
            subject.login(token);
            System.out.println("登录成功");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误");
        } catch (LockedAccountException e) {
            System.out.println("登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            System.out.println("该用户不存在");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("is role:"+subject.hasRole("系统管理员"));


    }

    @RequestMapping("/test")
    public @ResponseBody String test(){
        return "test";
    }

    @RequestMapping("/403")
    public String noAuth(){
        return "403";
    }

    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * @return
     */
    @RequestMapping(value = "/unauth")
    @ResponseBody
    public Object unauth() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "1000000");
        map.put("msg", "未登录");
        return map;
    }

    @RequestMapping("/loginPage")
    public String login(){

        return "pages/login";
    }
}
